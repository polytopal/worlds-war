package fr.utbm.info.vi51.worldswar.gui;

import java.awt.Color;

import fr.utbm.info.vi51.worldswar.environment.Breed;
import fr.utbm.info.vi51.worldswar.environment.PerceptionCell;
import fr.utbm.info.vi51.worldswar.environment.PheromoneType;
import fr.utbm.info.vi51.worldswar.environment.perceivable.PerceivableAnt;
import fr.utbm.info.vi51.worldswar.environment.perceivable.PerceivableFood;

public class GUIUtils {

	private static Color ANT_HILL_COLOR = new Color(255, 0, 0);

	private static Color DARK_ANT_COLOR = new Color(51, 25, 0);
	private static Color RED_ANT_COLOR = new Color(153, 0, 0);

	private static Color FOOD_LITTLE_QTY_COLOR = new Color(128, 255, 128);
	private static Color FOOD_BIG_QTY_COLOR = new Color(102, 204, 102);
	private static int FOOD_BIG_QTY_LIMIT = 15;

	private static Color EMPTY_CELL_COLOR = Color.WHITE;

	private static float PHEROMONE_VISIBILITY_COEFFICIENT = 1.8f;
	private static Color PHEROMONE_DANGER_COLOR = new Color(180, 80, 255);
	private static Color PHEROMONE_FOOD_COLOR = new Color(153, 255, 255);
	private static Color PHEROMONE_HOME_COLOR = new Color(255, 204, 153);

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
			Color pheromoneColor;
			switch (pheromoneFilter) {
			case DANGER:
				pheromoneColor = PHEROMONE_DANGER_COLOR;
				break;
			case FOOD:
				pheromoneColor = PHEROMONE_FOOD_COLOR;
				break;
			case HOME:
				pheromoneColor = PHEROMONE_HOME_COLOR;
				break;
			default:
				pheromoneColor = Color.BLACK;
				break;
			}

			final float qty = cell.getTotalPheromoneQuantity(pheromoneFilter) * PHEROMONE_VISIBILITY_COEFFICIENT;
			if (qty > 0) {
				// for each color component (rgb), we calculate the average
				// between the previously computed color and the pheromone
				// color. The avarage is balanced by the quantity of the
				// pheromone
				c = new Color((int) ((c.getRed() + pheromoneColor.getRed() * qty) / (qty + 1)),
						(int) ((c.getGreen() + pheromoneColor.getGreen() * qty) / (qty + 1)),
						(int) ((c.getBlue() + pheromoneColor.getBlue() * qty) / (qty + 1)));
			}
		}

		return c;
	}

}
