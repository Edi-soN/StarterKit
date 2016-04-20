package com.capgemini.java;

/**
 * Bowling object represents a single bowling game (10 rounds) with ability to
 * roll a bowl, calculate score and monitor the end of a game
 * 
 * @author ADDZIEDZ
 *
 */
public class Bowling implements BowlingGameResultCalculator {

	private int rollsCounter = 0;
	private int[] pointsPerRoll = new int[21];
	private double rounds = 0;
	// those fields below can be replaced by a container with frames,
	// e.g. two dimensional array [rounds][rolls]
	// but then different logic needed to be applied
	private int lastRoundPoints = 0;
	private int lastRoundRolls = 0;
	private int previousRoundPoints = 0;
	private int previousRoundRolls = 0;

	/**
	 * Roll a bowling ball that hits given number of pins
	 * 
	 * @param numberOfPins
	 *            knocked over
	 * @throws UnsupportedOperationException
	 *             if roll is when after the game has finished
	 * @throws IllegalArgumentException
	 *             if number of pins in a single row is less than zero or more
	 *             than ten
	 */
	public void roll(int numberOfPins) {

		// checking if game has ended
		if (isFinished())
			throw new UnsupportedOperationException("Current game has finished");
		// checking if number of pins is proper
		if (numberOfPins < 0 || numberOfPins > 10) {
			throw new IllegalArgumentException("Invalid number of pins");
		}
		// checking if second roll in the same round is higher than first roll,
		// then number of pins is over ten
		if (numberOfPins < 10 && numberOfPins + previousRoundPoints > 10)
			throw new IllegalArgumentException("Invalid number of pins (over 10)");
		else if (numberOfPins != 10) {
			previousRoundPoints += numberOfPins;
			previousRoundRolls++;
		}
		if (previousRoundRolls == 2) {
			previousRoundPoints = 0;
			previousRoundRolls = 0;
		}

		pointsPerRoll[rollsCounter] = numberOfPins;

		// adding rounds: strike is +1, normal roll is +0.5 (as there are two
		// rolls per round)
		if (numberOfPins == 10)
			rounds += 1.0;
		else
			rounds += 0.5;

		// setting values for isFinished check
		if (rounds >= 9.5) {
			lastRoundRolls++;
			lastRoundPoints += numberOfPins;
		}

		rollsCounter++;
	}

	/**
	 * Calculates current score
	 * 
	 * @return number of points scored by a player
	 */
	public int score() {
		int totalPoints = 0;
		int i = 0;
		// calculating points
		for (int round = 0; round < 10; round++) {
			// strike
			if (pointsPerRoll[i] == 10) {
				totalPoints += pointsPerRoll[i] + pointsPerRoll[i + 1] + pointsPerRoll[i + 2];
				i++;
				// spare
			} else if (pointsPerRoll[i] + pointsPerRoll[i + 1] == 10) {
				totalPoints += pointsPerRoll[i] + pointsPerRoll[i + 1] + pointsPerRoll[i + 2];
				i += 2;
				// normal
			} else {
				totalPoints += pointsPerRoll[i] + pointsPerRoll[i + 1];
				i += 2;
			}
		}
		return totalPoints;
	}

	/**
	 * Checks the state of a game (running/finished)
	 * 
	 * @return true if a game is over, otherwise false
	 */
	public boolean isFinished() {
		// has to be 10th round
		if (rounds > 9.0) {
			// player has at least two rolls in last round
			if (lastRoundRolls < 2)
				return false;
			// if in first roll of last round player scores 10 points
			// or after second roll of last round score is at least 10
			else if (lastRoundRolls == 2 && lastRoundPoints >= 10)
				return false;
			else
				return true;
		} else
			return false;
	}

}
