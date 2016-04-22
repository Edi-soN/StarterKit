package com.capgemini.java;

import java.util.ArrayList;
import java.util.List;

/**
 * Validates if given list of cards from file is properly formatted.
 * 
 * @author ADDZIEDZ
 *
 */
public class CardParser {
	private static final int NUMBER_OF_SIGNS_IN_LINE = 20;
	private String lineOfCards = "";
	private List<String[]> cardList = new ArrayList<>();

	private void clearLineFromWhiteSpaces(String line) {
		this.lineOfCards = line.replaceAll("\\s+", "");
	}

	private boolean isValidCard(String card) {
		String[] cardFigureAndColor = new String[2];
		cardFigureAndColor[0] = card.substring(0, 1);
		cardFigureAndColor[1] = card.substring(1, 2);
		if (cardFigureAndColor[0].matches("[AKQJT98765432]") && cardFigureAndColor[1].matches("[SHDC]")
				&& !cardList.contains(cardFigureAndColor)) {
			cardList.add(cardFigureAndColor);
			return true;
		}
		return false;
	}

	private boolean isCorrectNumberOfSignsInLine() {
		return lineOfCards.length() == NUMBER_OF_SIGNS_IN_LINE;
	}

	/**
	 * Validates single line from file that represents one deal (ten cards).
	 * 
	 * @param line
	 *            single poker deal (ten cards)
	 * @return validated set of cards
	 */
	public List<String[]> getValidatedCards(String line) {
		clearLineFromWhiteSpaces(line);
		if (!isCorrectNumberOfSignsInLine()) {
			throw new IllegalArgumentException("Incorrect number of cards for a single game");
		}
		for (int i = 0; i < lineOfCards.length(); i += 2) {
			String singleCard = lineOfCards.substring(i, i + 2);
			if (!isValidCard(singleCard)) {
				throw new IllegalArgumentException("Incorrect card signature");
			}
		}
		return cardList;
	}
}
