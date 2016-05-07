package fr.utbm.info.vi51.worldswar.gui;

import java.awt.Color;

import fr.utbm.info.vi51.worldswar.environment.Breed;
import fr.utbm.info.vi51.worldswar.environment.PerceptionCell;
import fr.utbm.info.vi51.worldswar.environment.PheromoneType;
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

	private static int PHEROMONE_VISIBILITY_COEFFICIENT = 2;
	private static Color PHEROMONE_COLOR = new Color(127, 0, 255);

	/**
	 * This class contains only static methods
	 */
	private GUIUtils()

	{
	}

	/**
	 * Compute the color of a cell
	 * 
	 * @param cell
	 * @param pheromoneFilter
	 *            a filter to show a specific pheromone, doesn't show any
	 *            pheromone if = null
	 * @return the color of the cell
	 */
	public static Color computeCellColor(PerceptionCell cell, PheromoneType pheromoneFilter) {

		Color c = null;
		if (cell.getAntHill() != null) {
			c = ANT_HILL_COLOR;
		} else { // no ant hill
			final PerceivableAnt ant = cell.getAnt();
			if (ant != null) {
				final Breed breed = ant.getColony().getBreed();
				switch (breed) {
				case DARK_ANTS:
					c = DARK_ANT_COLOR;
					break;
				case RED_ANTS:
					c = RED_ANT_COLOR;
					break;
				default:
					c = Color.BLACK;
					break;
				}
			} else { // no ant
				final PerceivableFood food = cell.getFood();
				if (food != null) {
					// the color is darker when there is more food
					final int qty = food.getAvailable();
					if (qty > FOOD_BIG_QTY_LIMIT) {
						c = FOOD_BIG_QTY_COLOR;
					} else {
						c = FOOD_LITTLE_QTY_COLOR;
					}
				} else { // no food
					c = EMPTY_CELL_COLOR;
				}
			}
		}

		if (pheromoneFilter != null) {
			// TODO doit dépendre d'une colonie
			final int qty = (int) (cell.getTotalPheromoneQuantity(pheromoneFilter) * PHEROMONE_VISIBILITY_COEFFICIENT);
			if (qty > 0) {
				c = new Color((c.getRed() + PHEROMONE_COLOR.getRed() * qty) / (qty + 1),
						(c.getGreen() + PHEROMONE_COLOR.getGreen() * qty) / (qty + 1),
						(c.getBlue() + PHEROMONE_COLOR.getBlue() * qty) / (qty + 1));
			}
		}

		return c;
	}

}
