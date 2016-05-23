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

		return c;
	}

}
