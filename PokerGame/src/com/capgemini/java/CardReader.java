package com.capgemini.java;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CardReader {
	private final static Logger logger = LogManager.getLogger(CardConverter.class);
	private final static String FILE_NAME = "C:\\Users\\ADDZIEDZ\\workspace\\PokerGame\\poker.txt";
	private List<String> readLines = new ArrayList<>();

	/**
	 * Loads data (set of cards in text format) from file
	 * 
	 * @return list of Card objects
	 */
	public List<String> readAllLinesFromFile() {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME));
			String line = reader.readLine();
			while (line != null) {
				readLines.add(line);
				line = reader.readLine();
			}
			reader.close();
		} catch (IOException e) {
			logger.error("Error reading file");
		}
		return readLines;
	}
}
