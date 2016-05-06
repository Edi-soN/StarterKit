package com.capgemini.java;

import java.util.List;

/**
 * Poker game simulator.
 * 
 * @author ADDZIEDZ
 *
 */
public class Poker {
	private CardConverter convertedCardList = new CardConverter();

	/**
	 * Calculates number of times when first player wins during whole game.
	 * 
	 * @return quantity of first player wins
	 */
	public int calculateNumberOfPlayerOneWins() {
		
		return calculateAllDeals(convertedCardList.convertRawDataToCards()); //playerOneWinsCounter;
	}

	public int calculateNumberOfPlayerOneWins(List<Card> givenListOfCards) {
		return calculateAllDeals(givenListOfCards); //playerOneWinsCounter;
	}

	private int calculateAllDeals(List<Card> cardList) {
		int playerOneWinsCounter =0;
		for (int i = 0; i < cardList.size(); i += 10) {
			Hand firstPlayer = new Hand();
			Hand secondPlayer = new Hand();
			firstPlayer.addCardsToHand(cardList.subList(i, i + 5));
			secondPlayer.addCardsToHand(cardList.subList(i + 5, i + 10));
			if (firstPlayer.compareTo(secondPlayer) == 1) {
				playerOneWinsCounter ++;
			}
		}
		
		return playerOneWinsCounter;
	}
}
