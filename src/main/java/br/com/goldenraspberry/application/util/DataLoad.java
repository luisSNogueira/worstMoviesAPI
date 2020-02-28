package br.com.goldenraspberry.application.util;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.opencsv.CSVReader;

@Component
public class DataLoad {
	
	private static final Logger logger = LoggerFactory.getLogger(DataLoad.class);
	
	@Value("${csv.file.path}")
	private String CSV_FILE_PATH;
	
	@PostConstruct
	private void init() {
		logger.info("código rodando na inicialização");
		readCSVFile();
	}
	
	private List<List<String>> readCSVFile() {
		List<List<String>> rows = new ArrayList<List<String>>();
		try  {
			CSVReader reader = new CSVReader(new FileReader(CSV_FILE_PATH));
			String[] values = null;
			while((values = reader.readNext()) != null) {
				rows.add(Arrays.asList(values));
			}
			reader.close();
		} catch(Exception e) {
			logger.error("[Error] - " + e.getMessage());
		}
		return rows;
	}

}
