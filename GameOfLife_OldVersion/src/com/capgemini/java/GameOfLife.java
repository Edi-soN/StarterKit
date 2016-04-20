package com.capgemini.java;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * GameOfLife object represents a grid of Cell objects with specified width and
 * length. Can populate grid with random cells (dead or alive) and calculate
 * next generation of cells.
 * 
 * @author ADDZIEDZ
 *
 */
public class GameOfLife {
	private List<Cell> currentCellGeneration = new ArrayList<>();
	private List<Cell> nextCellGeneration = new ArrayList<>();
	private Random deadOrAlive = new Random();

	/**
	 * Creates a GameOfLife object with specified width and length of a cells
	 * grid
	 * 
	 * @param verticalGridLength
	 *            stands for width of a grid
	 * @param horizontalGridLength
	 *            stands for length of a grid
	 */
	public GameOfLife(int verticalGridLength, int horizontalGridLength) {

		for (int i = 0; i < verticalGridLength; i++) {
			for (int j = 0; j < horizontalGridLength; j++) {
				currentCellGeneration.add(new Cell(i, j));
			}
		}

		randomizeCellLife();

		// for testing
		for (int i = 0; i < currentCellGeneration.size(); i++) {
			System.out.println(currentCellGeneration.get(i).getAlive() + " "
					+ currentCellGeneration.get(i).getPositionX() + " " + currentCellGeneration.get(i).getPositionY()
					+ " " + countNeighbours(currentCellGeneration.get(i), currentCellGeneration));
		}

		System.out.println();
		nextGeneration();

		for (int i = 0; i < currentCellGeneration.size(); i++) {
			System.out.println(currentCellGeneration.get(i).getAlive() + " ");
		}

	}

	/**
	 * Creates a GameOfLife object with given list of cells
	 * 
	 * @param inputCellList
	 *            stands for specified list of cells
	 */
	public GameOfLife(List<Cell> inputCellList) {

		for (Cell cell : inputCellList) {
			currentCellGeneration.add(cell);
		}

		// for testing
		for (int i = 0; i < currentCellGeneration.size(); i++) {
			System.out.println(currentCellGeneration.get(i).getAlive() + " "
					+ currentCellGeneration.get(i).getPositionX() + " " + currentCellGeneration.get(i).getPositionY()
					+ " " + countNeighbours(currentCellGeneration.get(i), currentCellGeneration));
		}

		System.out.println();
		nextGeneration();

		for (int i = 0; i < currentCellGeneration.size(); i++) {
			System.out.println(currentCellGeneration.get(i).getAlive() + " ");
		}

	}

	private void nextGeneration() {

		for (int i = 0; i < currentCellGeneration.size(); i++) {

			int oldX = currentCellGeneration.get(i).getPositionX();
			int oldY = currentCellGeneration.get(i).getPositionY();

			if (shouldLive(currentCellGeneration.get(i), currentCellGeneration)) {
				nextCellGeneration.add(new Cell(oldX, oldY, true));
			} else {
				nextCellGeneration.add(new Cell(oldX, oldY, false));
			}
		}

		currentCellGeneration = nextCellGeneration;
		nextCellGeneration = null;
	}

	private void randomizeCellLife() {

		for (Cell cell : currentCellGeneration)
			cell.setAlive(deadOrAlive.nextBoolean());
	}

	private boolean shouldLive(Cell cell, List<Cell> cellGrid) {

		int neighboursCounter = countNeighbours(cell, cellGrid);

		if ((!cell.getAlive() && neighboursCounter == 3) || (cell.getAlive() && neighboursCounter == 2)
				|| (cell.getAlive() && neighboursCounter == 3)) {
			return true;
		} else {
			return false;
		}
	}

	private int countNeighbours(Cell cell, List<Cell> cellGrid) {
		// neighbours: 0 (x,y), 1 (x-1,y-1), 2 (x,y-1), 3 (x+1,y-1), 4 (x-1,y),
		// 5 (x+1,y), 6 (x-1,y+1), 7 (x, y+1), 8 (x+1,y+1)

		int counter = 0;
		int x = cell.getPositionX();
		int y = cell.getPositionY();

		for (Cell cell1 : cellGrid) {
			int cX = cell1.getPositionX();
			int cY = cell1.getPositionY();
			boolean cA = cell1.getAlive();

			if ((x == cX && y - 1 == cY && cA) || (x == cX && y + 1 == cY && cA)) {
				counter++;
			} else if ((x - 1 == cX && y - 1 == cY && cA) || (x - 1 == cX && y == cY && cA)
					|| (x - 1 == cX && y + 1 == cY && cA)) {
				counter++;
			} else if ((x + 1 == cX && y - 1 == cY && cA) || (x + 1 == cX && y == cY && cA)
					|| ((x + 1 == cX && y + 1 == cY && cA))) {
				counter++;
			}
		}
		return counter;

	}

	/**
	 * Getter for list of cells
	 * 
	 * @return current list of cells
	 */
	public List<Cell> getCellList() {
		return currentCellGeneration;
	}

	/**
	 * Checks the state of each cell in the list
	 * 
	 * @return 0 if cell is dead, otherwise 1
	 */
	public int[] getCellState() {

		int[] result = new int[currentCellGeneration.size()];
		int i = 0;

		for (Cell cell : currentCellGeneration) {
			if (cell.getAlive()) {
				result[i++] = 1;
			} else {
				result[i++] = 0;
			}

		}

		return result;
	}

}
