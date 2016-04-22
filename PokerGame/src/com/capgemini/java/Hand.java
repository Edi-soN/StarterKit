package com.capgemini.java;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Set of five cards for a single player.
 * 
 * @author ADDZIEDZ
 *
 */
public class Hand implements Comparable<Hand> {
	private boolean isColor;
	private int setupValue = 0;
	private List<Map.Entry<Integer, Integer>> sortedCards;
	private Map<Integer, Integer> hand = new TreeMap<>(Collections.reverseOrder());

	/**
	 * Add all cards from given list to local map.
	 * 
	 * @param cardList
	 *            given list of cards
	 */
	public void addCardsToHand(List<Card> cardList) {
		checkCardsColor(cardList);
		for (Card card : cardList) {
			int cardValueCounter = 0;
			if (hand.get(card.getCardValue().getValue()) != null) {
				cardValueCounter = hand.get(card.getCardValue().getValue());
			}
			hand.put(card.getCardValue().getValue(), 1 + cardValueCounter);
		}
		sortCards();
		calculateSetupValue();
	}

	/**
	 * Overridden method from Comparable interface defines the rules for
	 * comparing two sets of cards.
	 * 
	 * @return one if first set wins, -1 if second set wins, 0 if draw
	 */
	@Override
	public int compareTo(Hand secondHand) {
		if (this.setupValue > secondHand.setupValue) {
			return 1;
		}
		if (this.setupValue < secondHand.setupValue) {
			return -1;
		}
		for (int i = 0; i < this.sortedCards.size(); i++) {
			int winner = this.sortedCards.get(i).getKey().compareTo(secondHand.sortedCards.get(i).getKey());
			if (winner != 0) {
				return winner;
			}
		}
		return 0;
	}

	private void checkCardsColor(List<Card> cardList) {
		boolean allCardsOneColor = true;
		String firstCardColor = cardList.get(0).getCardColor();
		for (Card card : cardList) {
			if (card.getCardColor() != firstCardColor) {
				allCardsOneColor = false;
			}
		}
		this.isColor = allCardsOneColor;
	}

	private void calculateSetupValue() {
		boolean isFiveCards = (sortedCards.size() == 5);
		boolean isDifferenceFourAndFiveCards = (((sortedCards.get(0).getKey()
				- sortedCards.get(sortedCards.size() - 1).getKey()) == 4) && isFiveCards);
		boolean isColorAndFiveCards = (isColor && isFiveCards);
		String cardSetupPattern = "";
		for (Map.Entry<Integer, Integer> card : sortedCards) {
			cardSetupPattern += card.getValue().toString();
		}
		this.setupValue = CardSetup.getEnum(cardSetupPattern, isColorAndFiveCards, isDifferenceFourAndFiveCards)
				.getSetupValue();
	}

	private void sortCards() {
		sortedCards = new ArrayList<Map.Entry<Integer, Integer>>(hand.entrySet());
		Collections.sort(sortedCards, new sortByCardQuantityThenByCardValue<Integer, Integer>());
	}

	// Comparator that sorts Map.Entry objects with Comparable keys and values
	// Source:
	// http://stackoverflow.com/questions/3074154/sorting-a-hashmap-based-on-value-then-key
	private class sortByCardQuantityThenByCardValue<K extends Comparable<? super K>, V extends Comparable<? super V>>
			implements Comparator<Map.Entry<K, V>> {

		public int compare(Map.Entry<K, V> firstCard, Map.Entry<K, V> secondCard) {
			int cardQuantity = secondCard.getValue().compareTo(firstCard.getValue());
			if (cardQuantity != 0) {
				return cardQuantity;
			} else {
				return secondCard.getKey().compareTo(firstCard.getKey());
			}
		}
	}
}
