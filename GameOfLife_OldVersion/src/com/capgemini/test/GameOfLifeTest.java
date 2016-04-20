package com.capgemini.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.capgemini.java.Cell;
import com.capgemini.java.GameOfLife;

public class GameOfLifeTest {

	Cell cell;

	@Before
	public void doBefore() {
		cell = new Cell(0, 0);
	}

	@Test
	public void shouldReturnDeadCell() {
		// given

		// when
		boolean result = cell.getAlive();

		// then
		assertFalse(result);
	}

	@Test
	public void shouldReturnZeroPositionXForCellWithZeroX() {
		// given

		// when
		int result = cell.getPositionX();

		// then
		assertEquals(0, result);
	}

	@Test
	public void shouldReturnZeroPositionYForCellWithZeroY() {
		// given

		// when
		int result = cell.getPositionY();

		// then
		assertEquals(0, result);
	}

	@Test
	public void shouldReturnGivenCellGeneration1() {
		// given
		List<Cell> cellList = new ArrayList<Cell>();
		cellList.add(new Cell(0, 0, false));
		cellList.add(new Cell(0, 1, false));
		cellList.add(new Cell(0, 2, false));
		cellList.add(new Cell(1, 0, false));
		cellList.add(new Cell(1, 1, true));
		cellList.add(new Cell(1, 2, true));
		cellList.add(new Cell(2, 0, false));
		cellList.add(new Cell(2, 1, false));
		cellList.add(new Cell(2, 2, false));

		GameOfLife game = new GameOfLife(cellList);

		// when
		int[] result = game.getCellState();

		// then
		int[] given = { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		for (int i = 0; i < result.length; i++)
			assertEquals(given[i], result[i]);

	}

	@Test
	public void shouldReturnGivenCellGeneration2() {
		// given
		List<Cell> cellList = new ArrayList<Cell>();
		cellList.add(new Cell(0, 0, false));
		cellList.add(new Cell(0, 1, false));
		cellList.add(new Cell(0, 2, true));
		cellList.add(new Cell(1, 0, false));
		cellList.add(new Cell(1, 1, true));
		cellList.add(new Cell(1, 2, true));
		cellList.add(new Cell(2, 0, false));
		cellList.add(new Cell(2, 1, false));
		cellList.add(new Cell(2, 2, true));

		GameOfLife game = new GameOfLife(cellList);

		// when
		int[] result = game.getCellState();

		// then
		int[] given = { 0, 1, 1, 0, 1, 1, 0, 1, 1 };
		for (int i = 0; i < result.length; i++)
			assertEquals(given[i], result[i]);

	}

	@Test
	public void shouldReturnGivenCellGeneration3() {
		// given
		List<Cell> cellList = new ArrayList<Cell>();
		cellList.add(new Cell(0, 0, true));
		cellList.add(new Cell(0, 1, true));
		cellList.add(new Cell(0, 2, true));
		cellList.add(new Cell(1, 0, true));
		cellList.add(new Cell(1, 1, true));
		cellList.add(new Cell(1, 2, true));
		cellList.add(new Cell(2, 0, true));
		cellList.add(new Cell(2, 1, true));
		cellList.add(new Cell(2, 2, true));

		GameOfLife game = new GameOfLife(cellList);

		// when
		int[] result = game.getCellState();

		// then
		int[] given = { 1, 0, 1, 0, 0, 0, 1, 0, 1 };
		for (int i = 0; i < result.length; i++)
			assertEquals(given[i], result[i]);

	}

	@Test
	public void shouldReturnGivenCellGeneration4() {
		// given
		List<Cell> cellList = new ArrayList<Cell>();
		cellList.add(new Cell(0, 0, false));
		cellList.add(new Cell(0, 1, false));
		cellList.add(new Cell(0, 2, true));
		cellList.add(new Cell(1, 0, false));
		cellList.add(new Cell(1, 1, false));
		cellList.add(new Cell(1, 2, true));
		cellList.add(new Cell(2, 0, false));
		cellList.add(new Cell(2, 1, false));
		cellList.add(new Cell(2, 2, true));

		GameOfLife game = new GameOfLife(cellList);

		// when
		int[] result = game.getCellState();

		// then
		int[] given = { 0, 0, 0, 0, 1, 1, 0, 0, 0 };
		for (int i = 0; i < result.length; i++)
			assertEquals(given[i], result[i]);

	}

	@Test
	public void shouldReturnGivenCellGeneration5() {
		// given
		List<Cell> cellList = new ArrayList<Cell>();
		cellList.add(new Cell(0, 0, false));
		cellList.add(new Cell(0, 1, false));
		cellList.add(new Cell(0, 2, false));
		cellList.add(new Cell(1, 0, false));
		cellList.add(new Cell(1, 1, false));
		cellList.add(new Cell(1, 2, false));
		cellList.add(new Cell(2, 0, false));
		cellList.add(new Cell(2, 1, false));
		cellList.add(new Cell(2, 2, false));

		GameOfLife game = new GameOfLife(cellList);

		// when
		int[] result = game.getCellState();

		// then
		int[] given = { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		for (int i = 0; i < result.length; i++)
			assertEquals(given[i], result[i]);

	}
}
