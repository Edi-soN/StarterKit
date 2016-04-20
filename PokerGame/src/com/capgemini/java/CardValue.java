package com.capgemini.java;

/**
 * Enumeration of all cards figures with their values.
 * 
 * @author ADDZIEDZ
 *
 */
public enum CardValue {
	ACE("A", 14), KING("K", 13), QUEEN("Q", 12), JACK("J", 11), TEN("T", 10), NINE("9", 9), EIGHT("8", 8), SEVEN("7",
			7), SIX("6", 6), FIVE("5", 5), FOUR("4", 4), THREE("3", 3), TWO("2", 2);

	private final String symbol;
	private final int value;

	CardValue(String symbol, int value) {
		this.symbol = symbol;
		this.value = value;
	}

	public int getValue() {
		return this.value;
	}

	public String getSymbol() {
		return this.symbol;
	}

	/**
	 * Converts card symbol to its value.
	 * 
	 * @param symbol
	 *            visual image of a card
	 * @return value of a given card
	 */
	public static CardValue getEnum(String symbol) {
		for (CardValue enumCardValue : values()) {
			if (enumCardValue.getSymbol().equalsIgnoreCase(symbol)) {
				return enumCardValue;
			}
		}

		throw new IllegalArgumentException();
	}

}
