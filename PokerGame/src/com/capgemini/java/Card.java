package com.capgemini.java;

/**
 * Container for a single poker card.
 * 
 * @author ADDZIEDZ
 *
 */
public class Card {
	private CardValue cardValue;
	private String cardColor = "";

	public Card(CardValue cardValue, String cardColor) {
		this.cardValue = cardValue;
		this.cardColor = cardColor;
	}

	public CardValue getCardValue() {
		return this.cardValue;
	}

	public String getCardColor() {
		return this.cardColor;
	}
}
