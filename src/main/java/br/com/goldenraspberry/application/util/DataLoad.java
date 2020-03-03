package br.com.goldenraspberry.application.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.opencsv.CSVParser;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import br.com.goldenraspberry.application.DAO.MovieDAO;
import br.com.goldenraspberry.application.model.Movie;

@Component
public class DataLoad {

	private static final Logger logger = LoggerFactory.getLogger(DataLoad.class);
	

	@Autowired
	private MovieDAO movieDAO;

	@Value("${csv.file.path}")
	private String CSV_FILE_PATH;

	@PostConstruct
	private void init() {
		logger.info("código rodando na inicialização");
		//persistInputData();
		readCSVBuffer();
	}
	
	private List<List<String>> readCSVBuffer() {
		List<List<String>> rows = new ArrayList<List<String>>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(CSV_FILE_PATH));
			String line;
			while((line = br.readLine()) != null) {
				String[] values = line.split(";");
				rows.add(Arrays.asList(values));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rows;
	}

	private List<List<String>> readCSVFile() {
		List<List<String>> rows = new ArrayList<List<String>>();
		try {
			CSVParser parser = new CSVParser('\r');
			CSVReader reader = new CSVReaderBuilder(new FileReader(CSV_FILE_PATH))
					.withCSVParser(parser).build();
			String[] values = null;
			while ((values = reader.readNext()) != null) {
				rows.add(Arrays.asList(values));
			}
			reader.close();
		} catch (Exception e) {
			logger.error("[Error] - " + e.getMessage());
		}
		return rows;
	}

	private List<String> parseMultipleEntries(String input) {
		String[] values = input.split("\\s*,\\s*|\\s*and\\s*");
		return Arrays.asList(values);
	}

	private void persistInputData() {
		List<List<String>> rows = readCSVFile();
		for (int i = 0; i < rows.size(); i++) {
			List<String> cols = Arrays.asList(rows.get(i).get(0).split(";"));
			Movie movie = new Movie();
			movie.setYear(cols.get(0));
			movie.setTitle(cols.get(1));
			movie.setStudios(parseMultipleEntries(cols.get(2)));
			movie.setProducers(parseMultipleEntries(cols.get(3)));
			movie.setWinner(cols.size() > 4 ? true : false);
			try {
				movieDAO.insert(movie);
			} catch(Exception e) {
				logger.error("[Error] - " + e.getMessage());
			}
		}
	}

}
