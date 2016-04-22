package com.capgemini.java;

import java.util.List;

/**
 * Creates single frame for bowling game last round.
 * 
 * @author ADDZIEDZ
 *
 */
public class LastRound extends Round {

	private List<Roll> rolls = getRolls();

	/**
	 * @return true if a game is over, otherwise false
	 */
	@Override
	public boolean isFinished() {
		return (rolls.size() == 2 && (rolls.get(0).getScore() + rolls.get(1).getScore()) < 10) || rolls.size() == 3;
	}
}
