package com.capgemini.java;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Converts list of cards in String representation to list of Card objects.
 * 
 * @author ADDZIEDZ
 *
 */
public class Converter {

	private final static Logger logger = LogManager.getLogger(Converter.class);
	private final static String FILE_NAME = "C:\\Users\\ADDZIEDZ\\workspace\\PokerGame\\poker.txt";
	private List<Card> cardList = new ArrayList<>();

	/**
	 * Loads data (set of cards in text format) from file and converts it to
	 * list of Card objects.
	 * 
	 * @return list of Card objects
	 */
	public List<Card> convertRawDataToCards() {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME));
			String line = reader.readLine();
			while (line != null) {
				convertSingleLine(line);
				line = reader.readLine();
			}
			reader.close();
		} catch (IOException e) {
			logger.error("Error reading file");
		}
		return cardList;
	}

	private void convertSingleLine(String line) {
		CardValidator cv = new CardValidator();
		List<String[]> validatedCards = cv.getValidatedCards(line);
		for (String[] card : validatedCards) {
			CardValue cardValue = checkCardValue(card[0]);
			String cardColor = card[1];
			cardList.add(new Card(cardValue, cardColor));
		}
	}

	private CardValue checkCardValue(String cardValue) {
		return CardValue.getEnum(cardValue);
	}

}