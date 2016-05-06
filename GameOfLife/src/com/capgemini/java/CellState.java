package com.capgemini.java;

/**
 * Describes the cell state - dead or alive
 * 
 * @author ADDZIEDZ
 *
 */
public enum CellState {
	// bez wartosci
	ALIVE(true), DEAD(false);

	private boolean value = false;

	CellState(boolean value) {
		this.value = value;
	}

	public boolean getValue() {
		return this.value;
	}
}
