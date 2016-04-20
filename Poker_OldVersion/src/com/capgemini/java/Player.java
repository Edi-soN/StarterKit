package com.capgemini.java;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Player object represents a single player with ability to pick a card, keep it
 * in hand and calculate the cards setup
 * 
 * @author ADDZIEDZ
 *
 */
public class Player {

	// field for cards keeping in hand
	private List<Card> handWithCards = new ArrayList<>();

	// each card have to be picked up separately
	/**
	 * Pick a single card and add it to hand
	 * 
	 * @param cardFigure
	 *            stands for figure of a card, e.g. Ace
	 * @param cardColor
	 *            stands for color of a card, e.g. Spades
	 * @throws UnsupportedOperationException
	 *             if player picks more than 5 cards
	 * @throws IllegalArgumentException
	 *             if player picks the same card twice
	 */
	public void pickCard(String cardFigure, String cardColor) {
		// player can keep in hand only five cards
		if (handWithCards.size() >= 5)
			throw new UnsupportedOperationException("Player can't have more than five cards at once");

		// can't have more than one card of a kind
		if (isAlreadyInHand(cardFigure, cardColor))
			throw new IllegalArgumentException("Can't pick the same card more than once");

		Card card = new Card(cardFigure, cardColor);
		handWithCards.add(card);
	}

	private boolean isAlreadyInHand(String cardFigure, String cardColor) {
		for (Card card : handWithCards) {
			if (card.getCardFigure() == cardFigure)
				if (card.getCardColor() == cardColor)
					return true;
		}
		return false;
	}

	// sorting cards descending by figures from Ace (14) to 2
	private void sortCardsInHand() {
		Collections.sort(handWithCards);
	}

	/**
	 * Shows all cards from hand as a single String
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (Card nextCard : handWithCards) {
			sb.append(nextCard.getCardFigure()).append(nextCard.getCardColor()).append(" ");
		}
		sb.append("\n");
		return sb.toString();
	}

	// Setups: 0 - High Card, 1 - One Pair, 2 - Two Pair, 3 - Three of a Kind, 4
	// - Straight
	// 5 - Flush, 6 - Full House, 7 - Four of a Kind, 8 - Straight Flush, 9 -
	// Royal Flush
	/**
	 * Calculates the proper setup of a cards in hand
	 * 
	 * @return value of a cards setup
	 * @throws UnsupportedOperationException
	 *             if less than five cards in hand
	 */
	public int getSetup() {
		if (handWithCards.size() < 5)
			throw new UnsupportedOperationException("Can't calculate setup with less than five cards");

		sortCardsInHand();

		if (isRoyalFlush())
			return 9;
		else if (isStraightFlush())
			return 8;
		else if (isFour())
			return 7;
		else if (isFullHouse())
			return 6;
		else if (isFlush())
			return 5;
		else if (isStraight())
			return 4;
		else if (isThree())
			return 3;
		else if (isTwoPair())
			return 2;
		else if (isOnePair())
			return 1;
		else
			return 0;
	}

	// check for One Pair
	private boolean isOnePair() {
		for (int i = 0; i < 4; i++)
			if (handWithCards.get(i).getCardFigure() == handWithCards.get(i + 1).getCardFigure())
				return true;

		return false;
	}

	// check for Two Pair
	private boolean isTwoPair() {
		int counter = 0;
		for (int i = 0; i < 4; i++)
			if (handWithCards.get(i).getCardFigure() == handWithCards.get(i + 1).getCardFigure())
				counter++;

		if (counter == 2)
			return true;
		else
			return false;
	}

	// check for Three
	private boolean isThree() {
		if (handWithCards.get(0).getCardFigure() == handWithCards.get(2).getCardFigure()
				|| handWithCards.get(2).getCardFigure() == handWithCards.get(4).getCardFigure())
			return true;
		else
			return false;
	}

	// check for Straight
	private boolean isStraight() {
		for (int i = 0; i < 4; i++)
			if (handWithCards.get(i).getCardValue() != handWithCards.get(i + 1).getCardValue() + 1)
				return false;

		return true;

	}

	// check for Flush
	private boolean isFlush() {
		for (int i = 0; i < 4; i++)
			if (handWithCards.get(i).getCardColor() != handWithCards.get(i + 1).getCardColor())
				return false;

		return true;
	}

	// check for Full House
	private boolean isFullHouse() {
		if (handWithCards.get(0).getCardFigure() == handWithCards.get(2).getCardFigure()
				&& handWithCards.get(3).getCardFigure() == handWithCards.get(4).getCardFigure())
			return true;
		else if (handWithCards.get(2).getCardFigure() == handWithCards.get(4).getCardFigure()
				&& handWithCards.get(0).getCardFigure() == handWithCards.get(1).getCardFigure())
			return true;
		else
			return false;
	}

	// check for Four of a Kind
	private boolean isFour() {
		if (handWithCards.get(0).getCardFigure() == handWithCards.get(3).getCardFigure()
				|| handWithCards.get(1).getCardFigure() == handWithCards.get(4).getCardFigure())
			return true;
		else
			return false;
	}

	// check for Straight Flush
	private boolean isStraightFlush() {
		if (isFlush() && isStraight())
			return true;
		else
			return false;
	}

	// check for Royal Flush
	private boolean isRoyalFlush() {
		if (isFlush() && handWithCards.get(0).getCardValue() == 14 && isStraight())
			return true;
		else
			return false;
	}

	/**
	 * Getter for list of cards (hand)
	 * 
	 * @return hand with cards
	 */
	public List<Card> getCardsList() {
		return handWithCards;
	}
}
