package br.com.goldenraspberry.application.util;

import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import br.com.goldenraspberry.application.business.MovieBusiness;
import br.com.goldenraspberry.application.model.Movie;

@Component
public class DataLoad {

	private static final Logger logger = LoggerFactory.getLogger(DataLoad.class);
	

	@Autowired
	private MovieBusiness movieBusiness;

	@Value("${csv.file.path}")
	private String CSV_FILE_PATH;

	@PostConstruct
	private void init() {
		logger.info("código rodando na inicialização");
		persistInputData();
	}
	
	private List<Movie> readCSVFile() {
		List<Movie> movies = new ArrayList<Movie>();
		try {
			Reader in = new FileReader(CSV_FILE_PATH);
			CSVFormat csvFormat = CSVFormat.newFormat(';');
			Iterable<CSVRecord> records = csvFormat.withFirstRecordAsHeader().parse(in);
			for(CSVRecord record : records) {
				Movie movie = new Movie();
				movie.setYear(record.get("year"));
				movie.setTitle(record.get("title"));
				movie.setStudios(parseMultipleEntries(record.get("studios")));
				movie.setProducers(parseMultipleEntries(record.get("producers")));
				movie.setWinner((record.get("winner").equals("yes")));
				movies.add(movie);
			}
		}catch(Exception e) {
			logger.error("[Error] - " + e.getMessage());
		}
		return movies;
	}

	private List<String> parseMultipleEntries(String input) {
		String[] values = input.split("\\s*,\\s*|\\s*and\\s*");
		return Arrays.asList(values);
	}

	private void persistInputData() {
		List<Movie> movies = readCSVFile();
		try {
			for(Movie movie : movies) {
				movieBusiness.insertMovie(movie);
			}
		} catch (Exception e) {
			logger.error("[Error] - " + e.getMessage());
		}
	}

}
