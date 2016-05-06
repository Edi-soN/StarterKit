package com.capgemini.java;

import java.util.ArrayList;
import java.util.List;

/**
 * Converts list of cards in String representation to list of Card objects.
 * 
 * @author ADDZIEDZ
 *
 */
public class CardConverter {
	//private List<Card> cardList = new ArrayList<>();

	/**
	 * Converts raw data to Card objects
	 * 
	 * @return list of Card objects
	 */
	public List<Card> convertRawDataToCards() {
		CardReader readLines = new CardReader();
		for (String line : readLines.readAllLinesFromFile()) {
			convertSingleLine(line);
		}
		return cardList;
	}

	private void convertSingleLine(String line) {
		CardParser cv = new CardParser();
		List<String[]> validatedCards = cv.getValidatedCards(line);
		List<Card> cardList.,. ;
		for (String[] card : validatedCards) {
			CardValue cardValue = checkCardValue(card[0]);
			String cardColor = card[1];
			cardList.add(new Card(cardValue, cardColor));
		}
		return cardList();
	}

	private CardValue checkCardValue(String cardValue) {
		return CardValue.getEnum(cardValue);
	}

}