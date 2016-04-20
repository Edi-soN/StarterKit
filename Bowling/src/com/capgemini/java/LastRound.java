package com.capgemini.java;

/**
 * Creates single frame for bowling game last round.
 * 
 * @author ADDZIEDZ
 *
 */
public class LastRound extends Round {

	/**
	 * @return true if a game is over, otherwise false
	 */
	@Override
	public boolean isFinished() {
		return (rolls.size() == 2 && (rolls.get(0).getScore() + rolls.get(1).getScore()) < 10) || rolls.size() == 3;
	}

	/**
	 * Assign number of pins from single roll to the round.
	 * 
	 * @param numberOfPins
	 *            number of knocked down pins
	 */
	@Override
	public void addRoll(int numberOfPins) {
		rolls.add(new Roll(numberOfPins));
	}

	/**
	 * Calculate total number of knocked pins in a single round.
	 * 
	 * @return sum of points for knocked down pins
	 */
	@Override
	public int calculateScore() {
		int score = 0;
		for (Roll singleRoll : rolls) {
			score += singleRoll.getScore();
		}
		return score;
	}
}
