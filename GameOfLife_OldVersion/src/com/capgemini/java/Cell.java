package com.capgemini.java;

/**
 * Cell object represents a single field on a grid with given coordinates (x,y)
 * and state (dead or alive)
 * 
 * @author ADDZIEDZ
 *
 */
public class Cell {

	private boolean alive;
	private int positionX;
	private int positionY;

	/**
	 * Creates a Cell object with specified positions (x,y)
	 * 
	 * @param positionX
	 *            stands for x position
	 * @param positionY
	 *            stands for y position
	 */
	public Cell(int positionX, int positionY) {
		this.positionX = positionX;
		this.positionY = positionY;
	}

	/**
	 * Creates a Cell object with specified positions (x,y) and state (dead or
	 * alive)
	 * 
	 * @param positionX
	 *            stands for x position
	 * @param positionY
	 *            stands for y position
	 * @param alive
	 *            stands for state of cell (dead or alive)
	 */
	public Cell(int positionX, int positionY, boolean alive) {
		this.positionX = positionX;
		this.positionY = positionY;
		this.alive = alive;
	}

	/**
	 * Getter for position x
	 * 
	 * @return position x
	 */
	public int getPositionX() {
		return positionX;
	}

	/**
	 * Getter for y position
	 * 
	 * @return y position
	 */
	public int getPositionY() {
		return positionY;
	}

	/**
	 * Checks if cell is dead or alive
	 * 
	 * @return true if cell is alive, otherwise false
	 */
	public boolean getAlive() {
		return alive;
	}

	/**
	 * Setter for cell state (dead or alive)
	 * 
	 * @param alive
	 *            true for alive cell, otherwise false
	 */
	public void setAlive(boolean alive) {
		this.alive = alive;
	}

}
