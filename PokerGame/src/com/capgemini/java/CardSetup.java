package com.capgemini.java;

/**
 * Enumeration of possible cards setups with their values.
 * 
 * @author ADDZIEDZ
 *
 */
public enum CardSetup {
	
	HIGHCARD("11111", false, false, 0), 
	ONEPAIR("2111", false, false, 1), 
	TWOPAIR("221", false, false,
			2), 
	THREEOFAKIND("311", false, false, 3), STRAIGHT("11111", false, true, 4), FLSUH("11111", true, false,
					5), FULLHOUSE("32", false, false, 6), FOUROFAKIND("41", false, false, 7), STRAIGHTFLUSH("11111",
							true, true, 8), ROYALFLUSH("11111", true, true, 9);

	private final String signature;
	private final int setpaValue;
	private final boolean isOneColor;
	private final boolean isDifferenceFour;

	CardSetup(String signature, boolean isOneColor, boolean isDifferenceFour, int valsetupValue) {
		this.signature = signature;
		this.setpaValue = valsetupValue;
		this.isOneColor = isOneColor;
		this.isDifferenceFour = isDifferenceFour;

	}

	public int getSetupValue() {
		return this.setpaValue;
	}

	public String getSignature() {
		return this.signature;
	}

	public boolean getIsOneColor() {
		return this.isOneColor;
	}

	public boolean getIsDifferenceFour() {
		return this.isDifferenceFour;
	}

	/**
	 * Converts setup signature to CardSetup object
	 * 
	 * @param signature
	 *            representation of cards in a given setup
	 * @return CardSetup object that represents given signature
	 */
	public static CardSetup getEnum(String signature, boolean isColor, boolean isDiffFour) {
		for (CardSetup enumCardSetup : values()) {
			if (enumCardSetup.getSignature().equalsIgnoreCase(signature) && enumCardSetup.getIsOneColor() == isColor
					&& enumCardSetup.getIsDifferenceFour() == isDiffFour) {
				return enumCardSetup;
			}
		}

		throw new IllegalArgumentException();
	}
}
