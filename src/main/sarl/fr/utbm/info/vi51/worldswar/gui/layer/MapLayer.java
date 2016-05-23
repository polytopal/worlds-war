package fr.utbm.info.vi51.worldswar.gui.layer;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import fr.utbm.info.vi51.worldswar.environment.Breed;
import fr.utbm.info.vi51.worldswar.perception.PerceptionCell;
import fr.utbm.info.vi51.worldswar.perception.PerceptionGrid;
import fr.utbm.info.vi51.worldswar.perception.perceivable.PerceivableAnt;
import fr.utbm.info.vi51.worldswar.perception.perceivable.PerceivableFood;

public class MapLayer implements GuiLayer {

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

	private int width;
	private int height;
	private final List<List<Color>> panelTable;

	public MapLayer() {
		this.panelTable = new ArrayList<>(0);
		this.width = 0;
		this.height = 0;
	}

	@Override
	public void setEnabled(boolean b) {
		// DO NOTHING : this layer is always enabled
	}

	@Override
	public void update(PerceptionGrid perceptionGrid) {
		if (this.width != perceptionGrid.getWidth() || this.height != perceptionGrid.getHeight()) {
			resizeGrid(perceptionGrid.getWidth(), perceptionGrid.getHeight());
		}
		for (int x = 0; x < this.width; x++) {
			for (int y = 0; y < this.height; y++) {
				final Color cellColor = computeCellColor(perceptionGrid.getCell(x, y));
				this.panelTable.get(x).set(y, cellColor);
			}
		}

	}

	/**
	 * Methods used only when a new environment with a different size of the
	 * older environment
	 */
	private void resizeGrid(int w, int h) {
		this.width = w;
		this.height = h;
		this.panelTable.clear();
		for (int x = 0; x < this.width; x++) {
			final ArrayList<Color> column = new ArrayList<>(h);
			for (int y = 0; y < this.height; y++) {
				column.add(Color.WHITE);
			}
			this.panelTable.add(column);
		}
	}

	@Override
	public void paintLayer(Graphics g, int cellSize) {

		for (int x = 0; x < this.width; x++) {
			for (int y = 0; y < this.height; y++) {
				g.setColor(this.panelTable.get(x).get(y));
				g.fillRect(x * cellSize, y * cellSize, cellSize, cellSize);
			}
		}

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
	public static Color computeCellColor(PerceptionCell cell) {

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
