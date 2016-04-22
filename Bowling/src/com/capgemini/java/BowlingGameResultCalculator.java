package com.capgemini.java;

public interface BowlingGameResultCalculator {
	/**
	 * Register a thrown a ball.
	 * 
	 * @param numberOfPins
	 *            number of knocked down pins
	 * @throws BowlingException 
	 */
	public void roll(int numberOfPins) throws BowlingException;

	/**
	 * @return current game score
	 */
	public int score();

	/**
	 * @return true if a game is over, otherwise false
	 */
	public boolean isFinished();
}
