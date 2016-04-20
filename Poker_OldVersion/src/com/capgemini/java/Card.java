package com.capgemini.java;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Colors: Spades (S), Hearts (H), Diamonds (D), Clubs (C)
// Figures: Ace (A), King (K), Queen (Q), Jack (J), 10, 9, 8, 7, 6, 5, 4, 3, 2

/**
 * Card object represents a single card for poker game. Card has its color,
 * figure and value, e.g. "AS" stands for Ace Spades with value of 14
 * 
 * @author ADDZIEDZ
 */
public class Card implements Comparable<Card> {

	private String cardColor;
	private String cardFigure;
	private int cardValue;
	private List<String> colors = new ArrayList<>(Arrays.asList("S", "H", "D", "C"));
	private List<String> figures = new ArrayList<>(
			Arrays.asList("A", "K", "Q", "J", "10", "9", "8", "7", "6", "5", "4", "3", "2"));

	/**
	 * Constructor for creating a card object with two parameters
	 * 
	 * @param figure
	 *            stands for card figure, e.g. Ace
	 * @param color
	 *            stands for card color, e.g. Spades
	 * @throws IllegalArgumentException
	 *             if specific card doesn't exist
	 */
	public Card(String figure, String color) {
		if (!validateCard(figure, color))
			throw new IllegalArgumentException();
		cardFigure = figure;
		cardColor = color;
		cardValue = getCardValue();
	}

	private boolean validateCard(String figure, String color) {
		if (colors.contains(color) && figures.contains(figure))
			return true;
		else
			return false;
	}

	/**
	 * Calculates value of a card
	 * 
	 * @return the value of a single card (figure), e.g. 14 for Ace
	 */
	public int getCardValue() {
		int value = 0;
		switch (this.cardFigure) {
		case "A":
			value = 14;
			break;
		case "K":
			value = 13;
			break;
		case "Q":
			value = 12;
			break;
		case "J":
			value = 11;
			break;
		default:
			value = Integer.parseInt(this.cardFigure);
			break;
		}
		return value;
	}

	/**
	 * Method from Comparable interface
	 */
	@Override
	public int compareTo(Card card) {
		if (card.equals(null))
			throw new NullPointerException();
		else if (this.cardValue < card.cardValue)
			return 1;
		else if (this.cardValue == card.cardValue)
			return 0;
		else
			return -1;
	}

	/**
	 * Getter for card color
	 * 
	 * @return a color of a single card, e.g. Spades as a String
	 */
	public String getCardColor() {
		return cardColor;
	}

	/**
	 * Getter for card figure
	 * 
	 * @return
	 */
	public String getCardFigure() {
		return cardFigure;
	}

}
