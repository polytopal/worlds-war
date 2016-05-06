package fr.utbm.info.vi51.worldswar.gui;

import java.awt.Color;

import fr.utbm.info.vi51.worldswar.environment.PerceptionCell;

public class Utils {

	private static Color ANT_HILL_COLOR = Color.RED;
	private static Color ANT_COLOR = Color.BLACK;
	private static Color FOOD_COLOR = Color.GREEN;
	private static Color EMPTY_CELL_COLOR = Color.WHITE;

	/**
	 * This class contains only static methods
	 */
	private Utils() {
	}

	public static Color computeCellColor(PerceptionCell cell) {

		if (cell.getAntHill() != null) {
			return ANT_HILL_COLOR;
		}
		if (cell.getAnt() != null) {
			return ANT_COLOR;
		}
		if (cell.getFood() != null) {
			return FOOD_COLOR;
		}
		return EMPTY_CELL_COLOR;

	}

}
