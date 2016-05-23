package fr.utbm.info.vi51.worldswar.gui.layer;

import java.awt.Color;

import fr.utbm.info.vi51.worldswar.perception.PerceptionCell;
import fr.utbm.info.vi51.worldswar.perception.perceivable.PerceivableFood;

public class MapLayer extends DefaultColorLayer {

	private static Color ANT_HILL_COLOR = new Color(255, 0, 0);

	private static Color FOOD_LITTLE_QTY_COLOR = new Color(128, 255, 128);
	private static Color FOOD_BIG_QTY_COLOR = new Color(102, 204, 102);
	private static int FOOD_BIG_QTY_LIMIT = 15;

	private static Color EMPTY_CELL_COLOR = Color.WHITE;

	private static float PHEROMONE_VISIBILITY_COEFFICIENT = 1.8f;
	private static Color PHEROMONE_DANGER_COLOR = new Color(180, 80, 255);
	private static Color PHEROMONE_FOOD_COLOR = new Color(153, 255, 255);
	private static Color PHEROMONE_HOME_COLOR = new Color(255, 204, 153);

	public MapLayer() {
		super();
		this.enabled = true;
	}

	@Override
	public void setEnabled(boolean b) {
		// DO NOTHING : this layer is always enabled
	}

	@Override
	protected Color computeCellColor(PerceptionCell cell) {

		Color c = null;
		if (cell.getAntHill() != null) {
			c = ANT_HILL_COLOR;
		} else { // no ant hill
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

		// TODO - remove
		// if (pheromoneFilter != null) {
		// // TODO doit dépendre d'une colonie
		// Color pheromoneColor;
		// switch (pheromoneFilter) {
		// case DANGER:
		// pheromoneColor = PHEROMONE_DANGER_COLOR;
		// break;
		// case FOOD:
		// pheromoneColor = PHEROMONE_FOOD_COLOR;
		// break;
		// case HOME:
		// pheromoneColor = PHEROMONE_HOME_COLOR;
		// break;
		// default:
		// pheromoneColor = Color.BLACK;
		// break;
		// }
		//
		// final float qty = cell.getTotalPheromoneQuantity(pheromoneFilter) *
		// PHEROMONE_VISIBILITY_COEFFICIENT;
		// if (qty > 0) {
		// // for each color component (rgb), we calculate the average
		// // between the previously computed color and the pheromone
		// // color. The avarage is balanced by the quantity of the
		// // pheromone
		// c = new Color((int) ((c.getRed() + pheromoneColor.getRed() * qty) /
		// (qty + 1)),
		// (int) ((c.getGreen() + pheromoneColor.getGreen() * qty) / (qty + 1)),
		// (int) ((c.getBlue() + pheromoneColor.getBlue() * qty) / (qty + 1)));
		// }
		// }

		return c;
	}

}
