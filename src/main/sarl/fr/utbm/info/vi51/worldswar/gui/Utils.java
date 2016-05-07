package fr.utbm.info.vi51.worldswar.gui;

import java.awt.Color;

import fr.utbm.info.vi51.worldswar.environment.Breed;
import fr.utbm.info.vi51.worldswar.environment.PerceptionCell;
import fr.utbm.info.vi51.worldswar.environment.perceivable.PerceivableAnt;

public class Utils {

	private static Color ANT_HILL_COLOR = Color.YELLOW;
	private static Color DARK_ANT_COLOR = new Color((float) 1.0, (float) 0.8, (float) 0.8);
	private static Color RED_ANT_COLOR = new Color((float) 1.0, (float) 0.5, (float) 0.5);
	private static Color FOOD_COLOR = new Color((float) 0.5, (float) 1.0, (float) 0.5);
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
		final PerceivableAnt ant = cell.getAnt();
		if (ant != null) {
			final Breed breed = ant.getColony().getBreed();
			switch (breed) {
			case DARK_ANTS:
				return DARK_ANT_COLOR;
			case RED_ANTS:
				return RED_ANT_COLOR;
			default:
				return Color.BLACK;
			}
		}
		if (cell.getFood() != null) {
			return FOOD_COLOR;
		}
		return EMPTY_CELL_COLOR;

	}

}
