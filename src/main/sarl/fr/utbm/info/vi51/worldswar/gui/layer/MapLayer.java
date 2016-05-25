package fr.utbm.info.vi51.worldswar.gui.layer;

import java.awt.Color;
import java.awt.Graphics;

import fr.utbm.info.vi51.worldswar.perception.PerceptionCell;
import fr.utbm.info.vi51.worldswar.perception.perceivable.PerceivableFood;
import fr.utbm.info.vi51.worldswar.perception.perceivable.PerceivableWall;

/**
 * This layer show the antHills and the food
 *
 */
public class MapLayer extends DefaultColorLayer {

	private static Color ANT_HILL_COLOR = new Color(255, 0, 0);

	private static Color ROCK_COLOR = new Color(100, 100, 100);

	private static Color FOOD_LITTLE_QTY_COLOR = new Color(128, 255, 128);
	private static Color FOOD_BIG_QTY_COLOR = new Color(102, 204, 102);
	private static int FOOD_BIG_QTY_LIMIT = 15;

	private static Color EMPTY_CELL_COLOR = Color.WHITE;

	public MapLayer() {
		super();
		this.enabled = true;
	}

	@Override
	public void paintLayer(Graphics g, int cellSize) {

		g.setColor(EMPTY_CELL_COLOR);
		g.fillRect(0, 0, this.width * cellSize, this.height * cellSize);

		for (int x = 0; x < this.width; x++) {
			for (int y = 0; y < this.height; y++) {
				final Color color = this.panelTable.get(x).get(y);
				if (color != null) {
					g.setColor(color);
					g.fillRect(x * cellSize, y * cellSize, cellSize, cellSize);
				}
			}
		}
	}

	@Override
	protected Color computeCellColor(PerceptionCell cell) {

		Color c = null;
		if (cell.getAntHill() != null) {
			c = ANT_HILL_COLOR;
		} else { // no ant hill
			final PerceivableWall wall = cell.getWall();
			if (wall != null) {
				c = ROCK_COLOR;
			} else {
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
					c = null;
				}
			}
		}

		return c;
	}

}
