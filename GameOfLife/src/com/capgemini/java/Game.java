package com.capgemini.java;

import java.awt.Point;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

/**
 * Game of life simulator.
 * 
 * @author ADDZIEDZ
 *
 */
public class Game {
	private Map<Point, Cell> cellMap = new HashMap<>();
	private Random deadOrAlive = new Random();

	/**
	 * User defined first generation of cells.
	 * 
	 * @param cellList
	 *            given by user field of cells
	 */
	public Game(List<Cell> cellList) {
		for (Cell cell : cellList) {
			cellMap.put(cell.getCellPosition(), cell);
		}
		addNeighboursPositions();
	}

	/**
	 * Random first generation of cells.
	 * 
	 * @param cellBoardWidth
	 *            width of cells field
	 * @param cellBoardHeight
	 *            height of the cells field
	 */
	public Game(int cellBoardWidth, int cellBoardHeight) {
		for (int i = 0; i < cellBoardWidth; i++) {
			for (int j = 0; j < cellBoardHeight; j++) {
				cellMap.put(new Point(i, j), new Cell(i, j, deadOrAlive.nextBoolean()));
			}
		}
		addNeighboursPositions();
	}

	private void addNeighboursPositions() {
		int[][] cellOffsets = new int[][] { { -1, -1 }, { -1, 0 }, { -1, 1 }, { 0, -1 }, { 0, 1 }, { 1, -1 }, { 1, 0 },
				{ 1, 1 } };
		for (Entry<Point, Cell> entry : cellMap.entrySet()) {
			for (int[] offset : cellOffsets) {
				int positionX = entry.getValue().getCellPosition().x + offset[0];
				int positionY = entry.getValue().getCellPosition().y + offset[1];
				Point point = new Point(positionX, positionY);
				if (cellMap.containsKey(point)) {
					entry.getValue().setNeighborsPositionsList(point);
				}
			}
		}
	}

	private void countAliveNeighbors() {
		for (Entry<Point, Cell> entry : cellMap.entrySet()) {
			int aliveNeighborsCounter = 0;
			for (Point point : entry.getValue().getNeighborsPositionsList()) {
				if (cellMap.get(point).getCellState() == CellState.ALIVE.getValue()) {
					entry.getValue().setNumberOfAliveNeighbors(++aliveNeighborsCounter);
				}
			}
		}
	}

	/**
	 * Calculates next cell generation.
	 */
	public void calculateNextGeneration() {
		countAliveNeighbors();
		for (Entry<Point, Cell> entry : cellMap.entrySet()) {
			int numberOfAliveNeigbors = entry.getValue().getNumberOfAliveNeighbors();
			if (numberOfAliveNeigbors == 3) {
				entry.getValue().setCellState(CellState.ALIVE.getValue());
			}
			if (numberOfAliveNeigbors < 2 || numberOfAliveNeigbors > 3) {
				entry.getValue().setCellState(CellState.DEAD.getValue());
			}
		}
	}

	/**
	 * Output for the View component
	 * 
	 * @return map of cells
	 */
	public Map<Point, Cell> getCellMap() {
		return this.cellMap;
	}

}
