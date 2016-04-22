package com.capgemini.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.capgemini.java.BowlingException;
import com.capgemini.java.Game;

public class BowlingTest {

	@Test
	public void shouldReturnScoreZeroForZeroRolls() {
		// given
		Game game = new Game();
		// when
		int result = game.score();
		// then
		assertEquals(0, result);
	}

	@Test
	public void shouldReturnFalseBeforeFirstRoll() {
		// given
		Game game = new Game();
		// when
		boolean result = game.isFinished();
		// then
		assertFalse(result);
	}

	@Test
	public void shouldReturnTrueAfterLastRoll() throws BowlingException {
		// given
		Game game = new Game();
		for (int i = 0; i < 20; i++) {
			game.roll(1);
		}
		// when
		boolean result = game.isFinished();
		// then
		assertTrue(result);
	}

	@Test
	public void shouldReturnScoreZeroForZerosOnly() throws BowlingException {
		// given
		Game game = new Game();
		for (int i = 0; i < 12; i++) {
			game.roll(0);
		}
		// when
		int result = game.score();
		// then
		assertEquals(0, result);
	}

	@Test(expected = BowlingException.class)
	public void shouldReturnExceptionForNegativeNumberOfPins() throws BowlingException {
		// given
		Game game = new Game();
		game.roll(-1);
	}

	@Test(expected = BowlingException.class)
	public void shouldReturnExceptionForNumberOfPinsOverTenInSingleRoll() throws BowlingException {
		// given
		Game game = new Game();
		game.roll(11);
	}

	@Test(expected = BowlingException.class)
	public void shouldReturnExceptionForNumberOfPinsOverTenInTwoRollsInARow() throws BowlingException {
		// given
		Game game = new Game();
		game.roll(5);
		game.roll(6);
	}

	@Test
	public void shouldReturnMaxScore() throws BowlingException {
		// given
		Game game = new Game();
		for (int i = 0; i < 12; i++) {
			game.roll(10);
		}
		// when
		int result = game.score();
		// then
		assertEquals(300, result);
	}

	@Test
	public void shouldReturnTenForOnesOnly() throws BowlingException {
		// given
		Game game = new Game();
		for (int i = 0; i < 20; i++) {
			game.roll(1);
		}
		// when
		int result = game.score();
		// then
		assertEquals(20, result);
	}

	@Test
	public void shouldReturnTwenty() throws BowlingException {
		// given
		Game game = new Game();
		game.roll(10);
		game.roll(5);
		// when
		int result = game.score();
		// then
		assertEquals(20, result);
	}

	@Test
	public void shouldReturnTen() throws BowlingException {
		// given
		Game game = new Game();
		game.roll(10);
		// when
		int result = game.score();
		// then
		assertEquals(10, result);
	}

	@Test
	public void shouldReturnFive() throws BowlingException {
		// given
		Game game = new Game();
		game.roll(5);
		// when
		int result = game.score();
		// then
		assertEquals(5, result);
	}

	@Test
	public void shouldReturnTwelve() throws BowlingException {
		// given
		Game game = new Game();
		game.roll(2);
		game.roll(8);
		game.roll(1);
		// when
		int result = game.score();
		// then
		assertEquals(12, result);
	}

	@Test
	public void shouldReturnEleven() throws BowlingException {
		// given
		Game game = new Game();
		game.roll(5);
		game.roll(0);
		game.roll(6);
		// when
		int result = game.score();
		// then
		assertEquals(11, result);
	}

	@Test
	public void shouldReturnThirty() throws BowlingException {
		// given
		Game game = new Game();
		game.roll(10);
		game.roll(10);
		// when
		int result = game.score();
		// then
		assertEquals(30, result);
	}

	@Test
	public void shouldReturnThirtyThree() throws BowlingException {
		// given
		Game game = new Game();
		game.roll(10);
		game.roll(10);
		game.roll(1);
		// when
		int result = game.score();
		// then
		assertEquals(33, result);
	}

	@Test
	public void shouldReturnThirtyTwo() throws BowlingException {
		// given
		Game game = new Game();
		game.roll(10);
		game.roll(3);
		game.roll(7);
		game.roll(1);
		// when
		int result = game.score();
		// then
		assertEquals(32, result);
	}

	@Test
	public void shouldReturnOneHundredEightySeven() throws BowlingException {
		// given
		int[] numberOfPins = new int[] { 10, 9, 1, 5, 5, 7, 2, 10, 10, 10, 9, 0, 8, 2, 9, 1, 10 };
		Game game = new Game();
		for (int i = 0; i < numberOfPins.length; i++) {
			game.roll(numberOfPins[i]);
		}
		// when
		int result = game.score();
		// then
		assertEquals(187, result);
	}
}
