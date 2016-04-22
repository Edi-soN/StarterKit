package com.capgemini.java;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class CellPoint extends Point {
	private int[][] cellOffsets = new int[][] { { -1, -1 }, { -1, 0 }, { -1, 1 }, { 0, -1 }, { 0, 1 }, { 1, -1 },
			{ 1, 0 }, { 1, 1 } };

	public CellPoint(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public List<CellPoint> getCellNeighboursPoints() {
		List<CellPoint> cellNeighborsPoints = new ArrayList<>();
		for (int[] neighborPosition : cellOffsets) {
			cellNeighborsPoints.add(new CellPoint(this.x + neighborPosition[0], this.y + neighborPosition[1]));
		}
		return cellNeighborsPoints;
	}
}
