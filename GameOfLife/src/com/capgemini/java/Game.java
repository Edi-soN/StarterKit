package com.capgemini.java;

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
	private Map<CellPoint, Cell> cellMap = new HashMap<>();
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

		addNeighbourCells();
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
				cellMap.put(new CellPoint(i, j), new Cell(i, j, deadOrAlive.nextBoolean()));
			}
		}
		addNeighbourCells();
	}

	/**
	 * Calculates next cell generation.
	 */
	public void calculateNextGeneration() {
		countAliveNeighbors();
		for (Entry<CellPoint, Cell> entry : cellMap.entrySet()) {
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
	public Map<CellPoint, Cell> getCellMap() {
		return this.cellMap;
	}

	private void addNeighbourCells() {
		for (Entry<CellPoint, Cell> cell : cellMap.entrySet()) {
			List<CellPoint> cellNeighborsPositions = cell.getKey().getCellNeighboursPoints();
			for (CellPoint neighborPoint : cellNeighborsPositions) {
				if (cellMap.containsKey(neighborPoint)) {
					cell.getValue().setNeighborCellsList(cellMap.get(neighborPoint));
				}
			}
		}
	}

	private void countAliveNeighbors() {
		for (Entry<CellPoint, Cell> cell : cellMap.entrySet()) {
			int aliveNeighborsCounter = 0;
			for (Cell neighborCell : cell.getValue().getNeighborCellsList()) {
				if (neighborCell.getCellState() == CellState.ALIVE.getValue()) {
					cell.getValue().setNumberOfAliveNeighbors(++aliveNeighborsCounter);
				}
			}
		}
	}
}
