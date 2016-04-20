package com.capgemini.java;

/**
 * Poker objects represents a poker game for two players with ability to draw
 * cards and calculate setups
 * 
 * @author ADDZIEDZ
 *
 */
public class Poker {

	/**
	 * Player object stands for single player with ability to pick a card, keep
	 * it in hand and calculate the cards setup
	 */
	public Player firstPlayer = new Player();
	public Player secondPlayer = new Player();

	/**
	 * Calculates scores for players setups and gets the winner of a single game
	 * 
	 * @return number of a winner, e.g. 1 - first player wins
	 */
	public int getWinner() {
		if (firstPlayer.getSetup() > secondPlayer.getSetup())
			return 1;
		else if (firstPlayer.getSetup() < secondPlayer.getSetup())
			return 2;
		else if (whenDraw() == 1)
			return 1;
		else if (whenDraw() == 2)
			return 2;
		else
			return 0;
	}

	// if draw check if the values of cards are equal, e.g. Pair of Aces vs Pair
	// of Kings
	// Setups: 0 - High Card, 1 - One Pair, 2 - Two Pair, 3 - Three of a Kind, 4
	// - Straight
	// 5 - Flush, 6 - Full House, 7 - Four of a Kind, 8 - Straight Flush, 9 -
	// Royal Flush
	// isDraw: 1 - first player wins, 2 - second player wins, 0 - true tie
	private int whenDraw() {
		int setupToCompare = firstPlayer.getSetup();

		if (isIdentical())
			return 0;

		switch (setupToCompare) {
		case 0:
			return whenDrawHighCard();
		case 1:
			return whenDrawOnePair();
		case 2:
			return whenDrawTwoPair();
		case 3:
			return whenDrawThree();
		case 4:
			return whenDrawStraight();
		case 5:
			return whenDrawFlush();
		case 6:
			return whenDrawFullHouse();
		case 7:
			return whenDrawFour();
		case 8:
			return whenDrawStraightFlush();
		case 9:
			return whenDrawRoyalFlush();
		}

		return 0;
	}

	private boolean isIdentical() {
		if (whenDrawHighCard() == 0)
			return true;
		else
			return false;
	}

	private int whenDrawRoyalFlush() {
		return 0;
	}

	private int whenDrawStraightFlush() {
		if (firstPlayer.getCardsList().get(1).getCardValue() > secondPlayer.getCardsList().get(1).getCardValue())
			return 1;
		else if (firstPlayer.getCardsList().get(1).getCardValue() < secondPlayer.getCardsList().get(1).getCardValue())
			return 2;
		else
			return 0;
	}

	private int whenDrawFour() {
		int firstPlayerFourPosition = getPositionForSetupFour(firstPlayer);
		int secondPlayerFourPosition = getPositionForSetupFour(secondPlayer);

		if (firstPlayer.getCardsList().get(firstPlayerFourPosition).getCardValue() > secondPlayer.getCardsList()
				.get(secondPlayerFourPosition).getCardValue())
			return 1;
		else
			return 2;

	}

	private int getPositionForSetupFour(Player player) {

		if (player.getCardsList().get(0).getCardValue() == player.getCardsList().get(3).getCardValue())
			return 0;
		else
			return 1;
	}

	private int whenDrawFullHouse() {
		return whenDrawThree();
	}

	private int getPositionForSetupFull(Player player) {

		if (player.getCardsList().get(0).getCardValue() == player.getCardsList().get(2).getCardValue())
			return 0;
		else
			return 3;
	}

	private int whenDrawFlush() {
		for (int i = 0; i < 4; i++) {
			if (firstPlayer.getCardsList().get(i).getCardValue() > secondPlayer.getCardsList().get(i).getCardValue())
				return 1;
			else if (firstPlayer.getCardsList().get(i).getCardValue() < secondPlayer.getCardsList().get(i)
					.getCardValue())
				return 2;
		}
		return 0;
	}

	private int whenDrawStraight() {
		return whenDrawStraightFlush();
	}

	private int whenDrawThree() {
		int firstPlayerFullPosition = getPositionForSetupFull(firstPlayer);
		int secondPlayerFullPosition = getPositionForSetupFull(secondPlayer);

		if (firstPlayer.getCardsList().get(firstPlayerFullPosition).getCardValue() > secondPlayer.getCardsList()
				.get(secondPlayerFullPosition).getCardValue())
			return 1;
		else
			return 2;
	}

	private int whenDrawTwoPair() {
		int[] firstPlayerTwoPairsPosition = getPositionsForSetupTwoPair(firstPlayer);
		int[] secondPlayerTwoPairsPosition = getPositionsForSetupTwoPair(secondPlayer);

		for (int i = 0; i < 2; i++)
			if (firstPlayer.getCardsList().get(firstPlayerTwoPairsPosition[i]).getCardValue() > secondPlayer
					.getCardsList().get(secondPlayerTwoPairsPosition[i]).getCardValue())
				return 1;
			else if (firstPlayer.getCardsList().get(firstPlayerTwoPairsPosition[i]).getCardValue() < secondPlayer
					.getCardsList().get(secondPlayerTwoPairsPosition[i]).getCardValue())
				return 2;

		if (firstPlayer.getCardsList().get(firstPlayerTwoPairsPosition[2]).getCardValue() > secondPlayer.getCardsList()
				.get(secondPlayerTwoPairsPosition[2]).getCardValue())
			return 1;
		else if (firstPlayer.getCardsList().get(firstPlayerTwoPairsPosition[2]).getCardValue() < secondPlayer
				.getCardsList().get(secondPlayerTwoPairsPosition[2]).getCardValue())
			return 2;
		else
			return 0;
	}

	private int[] getPositionsForSetupTwoPair(Player player) {
		int[] positions = new int[3];
		int j = 0, k = 0;

		positions[2] = 4;

		for (int i = 0; i < 4; i++)
			if (player.getCardsList().get(i).getCardValue() == player.getCardsList().get(i + 1).getCardValue()) {
				positions[j] = i;
				k = i + 1;
				j++;
			} else if (i != k)
				positions[2] = i;

		if (player.getCardsList().get(positions[0]).getCardValue() < player.getCardsList().get(positions[1])
				.getCardValue()) {
			int temp = positions[0];
			positions[0] = positions[1];
			positions[1] = temp;
		}

		return positions;
	}

	private int whenDrawOnePair() {
		int firstPlayerPairPosition = getPositionForSetupOnePair(firstPlayer);
		int secondPlayerPairPosition = getPositionForSetupOnePair(secondPlayer);
		int[] firstPlayerRestFromPair = getRestFromOnePair(firstPlayer, firstPlayerPairPosition);
		int[] secondPlayerRestFromPair = getRestFromOnePair(secondPlayer, secondPlayerPairPosition);

		if (firstPlayer.getCardsList().get(firstPlayerPairPosition).getCardValue() > secondPlayer.getCardsList()
				.get(secondPlayerPairPosition).getCardValue())
			return 1;
		else if (firstPlayer.getCardsList().get(firstPlayerPairPosition).getCardValue() < secondPlayer.getCardsList()
				.get(secondPlayerPairPosition).getCardValue())
			return 2;
		else
			for (int i = 0; i < 3; i++)
				if (firstPlayerRestFromPair[i] < secondPlayerRestFromPair[i])
					return 1;
				else if (firstPlayerRestFromPair[i] > secondPlayerRestFromPair[i])
					return 2;

		return 0;

	}

	private int[] getRestFromOnePair(Player player, int onePairPosition) {
		int[] restCards = new int[3];
		int j = 0;

		for (int i = 0; i < 5; i++) {
			if (i != onePairPosition && i != onePairPosition + 1) {
				restCards[j] = player.getCardsList().get(i).getCardValue();
				j++;
			}
		}

		return restCards;
	}

	private int getPositionForSetupOnePair(Player player) {

		for (int i = 0; i < 4; i++)
			if (player.getCardsList().get(i).getCardValue() == player.getCardsList().get(i + 1).getCardValue())
				return i;

		return 0;
	}

	// check the equality of all cards
	private int whenDrawHighCard() {
		for (int i = 0; i < 5; i++)
			if (firstPlayer.getCardsList().get(i).getCardValue() > secondPlayer.getCardsList().get(i).getCardValue())
				return 1;
			else if (firstPlayer.getCardsList().get(i).getCardValue() < secondPlayer.getCardsList().get(i)
					.getCardValue())
				return 2;

		return 0;
	}

}
