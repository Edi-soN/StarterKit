package com.capgemini.java;

import java.util.ArrayList;
import java.util.List;

/**
 * Creates single frame for bowling game.
 * 
 * @author ADDZIEDZ
 *
 */
public class Round {
	private List<Roll> rolls = new ArrayList<>();
	private Round nextRound;
	private boolean isStrike = false;
	private boolean isSpare = false;

	/**
	 * Assign number of pins from single roll to the round.
	 * 
	 * @param numberOfPins
	 *            number of knocked down pins
	 * @throws BowlingException
	 */
	public void addRoll(int numberOfPins) throws BowlingException {
		if (!(this instanceof LastRound) && !rolls.isEmpty() && rolls.get(0).getScore() + numberOfPins > 10) {
			throw new BowlingException("Illegal number of pins");
		}
		
		//validate();
		rolls.add(new Roll(numberOfPins));
	}
	
	//protected validateInput(num) 

	/**
	 * Calculate total number of knocked pins in a single round.
	 * 
	 * @return sum of points for knocked down pins and bonus
	 */
	public int calculateScore() {
		int score = 0;
		for (Roll singleRoll : rolls) {
			score += singleRoll.getScore();
		}
		return score + calculateBonusPoints();
	}

	/**
	 * Makes a reference to the next round
	 * 
	 * @param nextRound
	 *            reference to the next Round object
	 */
	public void assignNextRound(Round nextRound) {
		this.nextRound = nextRound;
	}

	/**
	 * Checks the status of current round
	 * 
	 * @return true if current round is finished, otherwise false
	 */
	public boolean isFinished() {
		return !rolls.isEmpty() && (rolls.size() == 2 || rolls.get(0).getScore() == 10);
	}

	protected List<Roll> getRolls() {
		return this.rolls;
	}

	private int calculateBonusPoints() {
		updateBonus();
		if (nextRound != null) {
			if (isStrike) {
				return this.nextRound.getTwoRolls();
			}
			if (isSpare) {
				return this.nextRound.getOneRoll();
			}
		}
		return 0;
	}

	private void updateBonus() {
		if (rolls.get(0).getScore() == 10) {
			this.isStrike = true;
		}
		if (rolls.size() == 2 && rolls.get(0).getScore() + rolls.get(1).getScore() == 10) {
			this.isSpare = true;
		}
	}

	private int getOneRoll() {
		return rolls.get(0).getScore();
	}

	private int getTwoRolls() {
		int score = rolls.get(0).getScore();
		if (rolls.size() < 2 && nextRound != null) {
			 score += nextRound.getOneRoll();
		}
		if (rolls.size() == 2) {
		    score += rolls.get(1).getScore();
		}
		return score;
	}
}
