package fr.utbm.info.vi51.worldswar.gui;

import java.awt.Color;

import fr.utbm.info.vi51.worldswar.environment.Breed;
import fr.utbm.info.vi51.worldswar.environment.PerceptionCell;
import fr.utbm.info.vi51.worldswar.environment.perceivable.PerceivableAnt;
import fr.utbm.info.vi51.worldswar.environment.perceivable.PerceivableFood;

public class GUIUtils {

	private static Color ANT_HILL_COLOR = Color.YELLOW;

	private static Color DARK_ANT_COLOR = new Color((float) 0.2, (float) 0.2, (float) 0.2);
	private static Color RED_ANT_COLOR = new Color((float) 0.7, (float) 0.2, (float) 0.2);

	private static Color FOOD_LITTLE_QTY_COLOR = new Color((float) 0.5, (float) 1.0, (float) 0.5);
	private static Color FOOD_BIG_QTY_COLOR = new Color((float) 0.4, (float) 0.8, (float) 0.4);
	private static int FOOD_BIG_QTY_LIMIT = 5;

	private static Color EMPTY_CELL_COLOR = Color.WHITE;

	/**
	 * This class contains only static methods
	 */
	private GUIUtils() {
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
		final PerceivableFood food = cell.getFood();
		if (food != null) {
			// the color is darker when there is more food
			final int qty = food.getAvailable();
			if (qty > FOOD_BIG_QTY_LIMIT) {
				return FOOD_BIG_QTY_COLOR;
			}
			return FOOD_LITTLE_QTY_COLOR;
		}
		return EMPTY_CELL_COLOR;

	}

}
