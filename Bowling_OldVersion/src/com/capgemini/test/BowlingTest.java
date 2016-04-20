package com.capgemini.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.capgemini.java.Bowling;

public class BowlingTest {

	Bowling bowl;

	@Before
	public void doBefore() {
		bowl = new Bowling();
	}

	@Test
	public void shouldReturnZeroBeforeFirstRoll() {
		// given

		// when
		int result = bowl.score();

		// then
		assertEquals(0, result);
	}

	@Test
	public void shouldReturnNotFinishedBeforeFirstRoll() {
		// given

		// when
		boolean result = bowl.isFinished();

		// then
		assertEquals(false, result);
	}

	@Test
	public void shouldReturnScoreTenAfterStrikeInFirstRoll() {
		// given
		bowl.roll(10);

		// when
		int result = bowl.score();

		// then
		assertEquals(10, result);
	}

	@Test
	public void shouldReturnScoreZeroAfterZeroInFirstRoll() {
		// given
		bowl.roll(0);

		// when
		int result = bowl.score();

		// then
		assertEquals(0, result);
	}

	@Test
	public void shouldReturnScoreTenAfterOneAndNineInTwoFirstRoll() {
		// given
		bowl.roll(1);
		bowl.roll(9);

		// when
		int result = bowl.score();

		// then
		assertEquals(10, result);
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldReturnExceptionAfterNegativeRollValue() {
		// given

		// when
		bowl.roll(-1);

		// then

	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldReturnExceptionWhenPinsOverTen() {
		// given

		// when
		bowl.roll(11);

		// then

	}

	@Test
	public void shouldReturnTwelveAfterRollsTenAndOne() {
		// given
		bowl.roll(10);
		bowl.roll(1);

		// when
		int result = bowl.score();

		// then
		assertEquals(12, result);
	}

	@Test
	public void shouldReturnTwelveAfterRollsOneNineOne() {
		// given
		bowl.roll(1);
		bowl.roll(9);
		bowl.roll(1);

		// when
		int result = bowl.score();

		// then
		assertEquals(12, result);
	}

	@Test
	public void shouldReturnZeroAfterAllRollsEqualZero() {
		// given
		for (int i = 0; i < 20; i++)
			bowl.roll(0);

		// when
		int result = bowl.score();

		// then
		assertEquals(0, result);
	}

	@Test
	public void shouldReturnTwentyAfterAllRollsEqualOne() {
		// given
		for (int i = 0; i < 20; i++)
			bowl.roll(1);

		// when
		int result = bowl.score();

		// then
		assertEquals(20, result);
	}

	@Test
	public void shouldReturnMaxAfterAllRollsEqualTen() {
		// given
		for (int i = 0; i < 12; i++)
			bowl.roll(10);

		// when
		int result = bowl.score();

		// then
		assertEquals(300, result);
	}

	@Test
	public void shouldReturnThirtyAfterTwoTens() {
		// given
		bowl.roll(10);
		bowl.roll(10);

		// when
		int result = bowl.score();

		// then
		assertEquals(30, result);
	}

	@Test
	public void shouldReturnThirtyThreeAfterTwoTensAndOne() {
		// given
		bowl.roll(10);
		bowl.roll(10);
		bowl.roll(1);

		// when
		int result = bowl.score();

		// then
		assertEquals(33, result);
	}

	@Test
	public void shouldReturnElevenAfterFiveZeroSix() {
		// given
		bowl.roll(5);
		bowl.roll(0);
		bowl.roll(6);

		// when
		int result = bowl.score();

		// then
		assertEquals(11, result);
	}

	@Test
	public void shouldReturnFinishedAfterTwelveTens() {
		// given
		for (int i = 0; i < 12; i++)
			bowl.roll(10);

		// when
		boolean result = bowl.isFinished();

		// then
		assertTrue(result);
	}

	@Test
	public void shouldReturnFinishedAfterTwentyOnes() {
		// given
		for (int i = 0; i < 20; i++)
			bowl.roll(1);

		// when
		boolean result = bowl.isFinished();

		// then
		assertTrue(result);
	}

	@Test
	public void shouldReturnFinishedAfterEighteenOnesAndThreeTens() {
		// given
		for (int i = 0; i < 18; i++)
			bowl.roll(1);

		bowl.roll(10);
		bowl.roll(10);
		bowl.roll(10);

		// when
		boolean result = bowl.isFinished();

		// then
		assertTrue(result);
	}

	@Test
	public void shouldReturnNotFinishedAfterEighteenOnesAndOneTen() {
		// given
		for (int i = 0; i < 18; i++)
			bowl.roll(1);

		bowl.roll(10);

		// when
		boolean result = bowl.isFinished();

		// then
		assertFalse(result);
	}

	@Test
	public void shouldReturnNotFinishedAfterEighteenOnesAndTwoFives() {
		// given
		for (int i = 0; i < 18; i++)
			bowl.roll(1);

		bowl.roll(5);
		bowl.roll(5);

		// when
		boolean result = bowl.isFinished();

		// then
		assertFalse(result);
	}

	@Test
	public void shouldReturnFinishedAfterEighteenOnesAndThreeFives() {
		// given
		for (int i = 0; i < 18; i++)
			bowl.roll(1);

		bowl.roll(5);
		bowl.roll(5);
		bowl.roll(5);

		// when
		boolean result = bowl.isFinished();

		// then
		assertTrue(result);
	}

	@Test(expected = UnsupportedOperationException.class)
	public void shouldReturnExceptionWhenRollAfterLastRound() {
		// given

		// when
		for (int i = 0; i < 30; i++)
			bowl.roll(1);

		// then
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldReturnExceptionWhenRollFiveAndSixInTheSameRound() {
		// given

		// when
		bowl.roll(5);
		bowl.roll(6);

		// then
	}
}
