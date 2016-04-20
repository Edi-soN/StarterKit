package com.capgemini.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.capgemini.java.Card;
import com.capgemini.java.Player;
import com.capgemini.java.Poker;

public class PokerTest {

	Poker poker;
	Player player;
	Card card;

	@Before
	public void doBefore() {
		poker = new Poker();
		player = new Player();
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldReturnExceptionForIllegalCard() {
		// given
		card = new Card("777", "S");

		// when

		// then
	}

	@Test
	public void shouldReturnValueFourteenForAce() {
		// given
		card = new Card("A", "S");

		// when
		int result = card.getCardValue();

		// then
		assertEquals(14, result);
	}

	@Test
	public void shouldReturnPickedCard() {
		// given
		player.pickCard("A", "S");

		// when
		String result = player.toString();

		// then
		assertEquals("AS \n", result);
	}

	@Test(expected = UnsupportedOperationException.class)
	public void shouldReturnExceptionWhenPickingMoreThanFiveCards() {
		// given
		player.pickCard("A", "S");
		player.pickCard("K", "S");
		player.pickCard("Q", "S");
		player.pickCard("J", "S");
		player.pickCard("10", "S");
		player.pickCard("9", "S");

		// when

		// then
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldReturnExceptionWhenPickingTheSameCardTwice() {
		// given
		player.pickCard("A", "S");
		player.pickCard("A", "S");

		// when

		// then
	}

	@Test(expected = UnsupportedOperationException.class)
	public void shouldReturnExceptionWhenCallingSetupWithLessThanFiveCards() {
		// given
		player.pickCard("A", "S");
		player.getSetup();

		// when

		// then
	}

	@Test
	public void shouldReturnZeroForHighCard() {
		// given
		player.pickCard("A", "S");
		player.pickCard("K", "S");
		player.pickCard("Q", "S");
		player.pickCard("4", "S");
		player.pickCard("3", "C");

		// when
		int result = player.getSetup();

		// then
		assertEquals(0, result);
	}

	@Test
	public void shouldReturnOneForOnePair() {
		// given
		player.pickCard("A", "S");
		player.pickCard("A", "D");
		player.pickCard("Q", "S");
		player.pickCard("4", "S");
		player.pickCard("3", "S");

		// when
		int result = player.getSetup();

		// then
		assertEquals(1, result);
	}

	@Test
	public void shouldReturnTwoForTwoPair() {
		// given
		player.pickCard("A", "S");
		player.pickCard("A", "D");
		player.pickCard("Q", "S");
		player.pickCard("Q", "D");
		player.pickCard("3", "S");

		// when
		int result = player.getSetup();

		// then
		assertEquals(2, result);
	}

	@Test
	public void shouldReturnThreeForThree() {
		// given
		player.pickCard("A", "S");
		player.pickCard("A", "D");
		player.pickCard("A", "C");
		player.pickCard("Q", "D");
		player.pickCard("3", "S");

		// when
		int result = player.getSetup();

		// then
		assertEquals(3, result);
	}

	@Test
	public void shouldReturnFourForStraight() {
		// given
		player.pickCard("3", "S");
		player.pickCard("4", "D");
		player.pickCard("5", "C");
		player.pickCard("6", "D");
		player.pickCard("7", "S");

		// when
		int result = player.getSetup();

		// then
		assertEquals(4, result);
	}

	@Test
	public void shouldReturnFiveForFlush() {
		// given
		player.pickCard("3", "S");
		player.pickCard("A", "S");
		player.pickCard("J", "S");
		player.pickCard("Q", "S");
		player.pickCard("7", "S");

		// when
		int result = player.getSetup();

		// then
		assertEquals(5, result);
	}

	@Test
	public void shouldReturnSixForFullHouse() {
		// given
		player.pickCard("6", "D");
		player.pickCard("6", "S");
		player.pickCard("6", "C");
		player.pickCard("3", "S");
		player.pickCard("3", "D");

		// when
		int result = player.getSetup();

		// then
		assertEquals(6, result);
	}

	@Test
	public void shouldReturnSevenForFour() {
		// given
		player.pickCard("6", "D");
		player.pickCard("6", "S");
		player.pickCard("6", "C");
		player.pickCard("6", "H");
		player.pickCard("3", "D");

		// when
		int result = player.getSetup();

		// then
		assertEquals(7, result);
	}
	
	@Test
	public void shouldReturnEightForStraightFlush(){
		// given
		player.pickCard("7", "S");
		player.pickCard("8", "S");
		player.pickCard("9", "S");
		player.pickCard("10", "S");
		player.pickCard("J", "S");
		
		// when
		int result = player.getSetup();
		
		// then
		assertEquals(8, result);
	}
	
	@Test
	public void shouldReturnNineForRoyalFlush(){
		// given
		player.pickCard("A", "S");
		player.pickCard("K", "S");
		player.pickCard("Q", "S");
		player.pickCard("J", "S");
		player.pickCard("10", "S");
		
		// when
		int result = player.getSetup();
		
		// then
		assertEquals(9, result);
	}
	
	@Test
	public void shouldReturnTieForTheSamePlayersHands(){
		// given
		poker.firstPlayer.pickCard("A", "S");
		poker.firstPlayer.pickCard("K", "S");
		poker.firstPlayer.pickCard("Q", "S");
		poker.firstPlayer.pickCard("J", "S");
		poker.firstPlayer.pickCard("10", "S");
		
		poker.secondPlayer.pickCard("A", "S");
		poker.secondPlayer.pickCard("K", "S");
		poker.secondPlayer.pickCard("Q", "S");
		poker.secondPlayer.pickCard("J", "S");
		poker.secondPlayer.pickCard("10", "S");
		
		// when
		int result = poker.getWinner();
		
		// then
		assertEquals(0, result);
	}
	
	@Test
	public void shouldReturnWinForFirstPlayerWithBetterHand(){
		// given
		poker.firstPlayer.pickCard("A", "S");
		poker.firstPlayer.pickCard("K", "S");
		poker.firstPlayer.pickCard("Q", "S");
		poker.firstPlayer.pickCard("J", "S");
		poker.firstPlayer.pickCard("10", "S");
		
		poker.secondPlayer.pickCard("7", "S"); 
		poker.secondPlayer.pickCard("8", "S"); 
		poker.secondPlayer.pickCard("9", "S"); 
		poker.secondPlayer.pickCard("10", "S");
		poker.secondPlayer.pickCard("J", "S"); 
		
		// when
		int result = poker.getWinner();
		
		// then
		assertEquals(1, result);
	}
	
	@Test
	public void shouldReturnWinForPlayerWithHigherCardsValueForSameSetups(){
		// given
		poker.firstPlayer.pickCard("9", "S");
		poker.firstPlayer.pickCard("9", "D");
		poker.firstPlayer.pickCard("2", "S");
		poker.firstPlayer.pickCard("3", "S");
		poker.firstPlayer.pickCard("10", "S");
		
		poker.secondPlayer.pickCard("A", "S"); 
		poker.secondPlayer.pickCard("A", "D"); 
		poker.secondPlayer.pickCard("2", "S"); 
		poker.secondPlayer.pickCard("3", "S");
		poker.secondPlayer.pickCard("J", "S"); 
		
		// when
		int result = poker.getWinner();
		
		// then
		assertEquals(2, result);
	}
	
	@Test
	public void shouldReturnWinForPlayerWithHigherCardsValueForTwoFours(){
		// given
		poker.firstPlayer.pickCard("9", "S");
		poker.firstPlayer.pickCard("9", "D");
		poker.firstPlayer.pickCard("9", "C");
		poker.firstPlayer.pickCard("9", "H");
		poker.firstPlayer.pickCard("10", "S");
		
		poker.secondPlayer.pickCard("A", "S"); 
		poker.secondPlayer.pickCard("A", "D"); 
		poker.secondPlayer.pickCard("A", "H"); 
		poker.secondPlayer.pickCard("A", "C");
		poker.secondPlayer.pickCard("J", "S"); 
		
		// when
		int result = poker.getWinner();
		
		// then
		assertEquals(2, result);
	}
	
	@Test
	public void shouldReturnWinForPlayerWithHigherCardsValueForTwoFulls(){
		// given
		poker.firstPlayer.pickCard("9", "S");
		poker.firstPlayer.pickCard("9", "D");
		poker.firstPlayer.pickCard("9", "C");
		poker.firstPlayer.pickCard("9", "H");
		poker.firstPlayer.pickCard("10", "S");
		
		poker.secondPlayer.pickCard("A", "S"); 
		poker.secondPlayer.pickCard("A", "D"); 
		poker.secondPlayer.pickCard("A", "H"); 
		poker.secondPlayer.pickCard("A", "C");
		poker.secondPlayer.pickCard("J", "S"); 
		
		// when
		int result = poker.getWinner();
		
		// then
		assertEquals(2, result);
	}
	
	@Test
	public void shouldReturnWinForPlayerWithHigherCardsValueForHighCard(){
		// given
		poker.firstPlayer.pickCard("A", "S");
		poker.firstPlayer.pickCard("9", "D");
		poker.firstPlayer.pickCard("7", "C");
		poker.firstPlayer.pickCard("5", "H");
		poker.firstPlayer.pickCard("2", "S");
		
		poker.secondPlayer.pickCard("A", "S"); 
		poker.secondPlayer.pickCard("9", "D"); 
		poker.secondPlayer.pickCard("7", "H"); 
		poker.secondPlayer.pickCard("5", "C");
		poker.secondPlayer.pickCard("3", "S"); 
		
		// when
		int result = poker.getWinner();
		
		// then
		assertEquals(2, result);
	}
	
	@Test
	public void shouldReturnWinForPlayerWithHigherCardsValueForTwoPair(){
		// given
		poker.firstPlayer.pickCard("A", "S");
		poker.firstPlayer.pickCard("A", "D");
		poker.firstPlayer.pickCard("J", "C");
		poker.firstPlayer.pickCard("J", "H");
		poker.firstPlayer.pickCard("2", "S");
		
		poker.secondPlayer.pickCard("A", "S"); 
		poker.secondPlayer.pickCard("A", "D"); 
		poker.secondPlayer.pickCard("J", "H"); 
		poker.secondPlayer.pickCard("J", "C");
		poker.secondPlayer.pickCard("3", "S"); 
		
		// when
		int result = poker.getWinner();
		
		// then
		assertEquals(2, result);
	}

}
