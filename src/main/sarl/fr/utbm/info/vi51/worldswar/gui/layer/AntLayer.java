package fr.utbm.info.vi51.worldswar.gui.layer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import fr.utbm.info.vi51.worldswar.environment.Breed;
import fr.utbm.info.vi51.worldswar.perception.PerceptionCell;
import fr.utbm.info.vi51.worldswar.perception.PerceptionGrid;
import fr.utbm.info.vi51.worldswar.perception.perceivable.PerceivableAnt;

/**
 * This layer show the ants. Each ant is represented by a colored square
 */
public class AntLayer implements GuiLayer {

	private static final Color DARK_ANT_COLOR = new Color(51, 25, 0);
	private static final Color RED_ANT_COLOR = new Color(153, 0, 0);

	private static final float[] TRIANGLE_SHAPE_X = { 0.5f, 1f, 0f };
	private static final float[] TRIANGLE_SHAPE_Y = { 0f, 1f, 1f };

	private static final float[] DIAMOND_SHAPE_X = { 0.5f, 1f, 0.5f, 0f };
	private static final float[] DIAMOND_SHAPE_Y = { 0f, 0.5f, 1f, 0.5f };

	private int width;
	private int height;
	private final List<List<Color>> colorTable;
	private final List<List<Shape>> shapeTable;
	private boolean enabled;

	public AntLayer() {
		this.colorTable = new ArrayList<>(0);
		this.shapeTable = new ArrayList<>(0);
		this.width = 0;
		this.height = 0;
		this.enabled = true;
	}

	@Override
	public void setEnabled(boolean b) {
		this.enabled = b;
	}

	@Override
	public boolean isEnabled() {
		return this.enabled;
	}

	@Override
	public void paintLayer(Graphics g, int cellSize) {
		final Graphics2D g2d = (Graphics2D) g;

		for (int x = 0; x < this.width; x++) {
			for (int y = 0; y < this.height; y++) {
				final Color color = this.colorTable.get(x).get(y);
				final Shape shape = this.shapeTable.get(x).get(y);
				if (color != null && shape != null) {
					g2d.setColor(color);

					if (shape == Shape.OVALE) {
						g.fillOval(x * cellSize, y * cellSize, cellSize, cellSize);
					} else {
						final int nbPoints = shape.getNbPoints();
						final int[] xPoints = new int[nbPoints];
						final int[] yPoints = new int[nbPoints];
						for (int i = 0; i < nbPoints; i++) {
							xPoints[i] = x * cellSize + (int) (shape.getxPoints()[i] * cellSize);
							yPoints[i] = y * cellSize + (int) (shape.getyPoints()[i] * cellSize);
						}
						g2d.fillPolygon(xPoints, yPoints, nbPoints);
					}

				}
			}
		}
	}

	private static Color computeCellColor(PerceptionCell cell) {
		Color c = null;
		final PerceivableAnt ant = cell.getAnt();
		if (ant != null && !ant.isBurrowed()) {
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
		}
		return c;
	}

	private static Shape computeCellShape(PerceptionCell cell) {
		return Shape.DIAMOND;
	}

	@Override
	public void update(PerceptionGrid perceptionGrid) {
		if (this.width != perceptionGrid.getWidth() || this.height != perceptionGrid.getHeight()) {
			resizeGrid(perceptionGrid.getWidth(), perceptionGrid.getHeight());
		}
		for (int x = 0; x < this.width; x++) {
			for (int y = 0; y < this.height; y++) {
				final PerceptionCell cell = perceptionGrid.getCell(x, y);
				final Color cellColor = computeCellColor(cell);
				final Shape cellShape = computeCellShape(cell);
				this.colorTable.get(x).set(y, cellColor);
				this.shapeTable.get(x).set(y, cellShape);
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
		this.colorTable.clear();
		this.shapeTable.clear();
		for (int x = 0; x < this.width; x++) {
			final ArrayList<Color> colorColumn = new ArrayList<>(h);
			final ArrayList<Shape> shapeColumn = new ArrayList<>(h);
			for (int y = 0; y < this.height; y++) {
				colorColumn.add(null);
				shapeColumn.add(null);
			}
			this.colorTable.add(colorColumn);
			this.shapeTable.add(shapeColumn);
		}
	}

	private enum Shape {

		OVALE, //
		TRIANGLE(3, TRIANGLE_SHAPE_X, TRIANGLE_SHAPE_Y), //
		DIAMOND(4, DIAMOND_SHAPE_X, DIAMOND_SHAPE_Y);

		private final int nbPoints;
		private final float[] xPoints;
		private final float[] yPoints;

		private Shape() {
			this.nbPoints = 0;
			this.xPoints = new float[0];
			this.yPoints = new float[0];
		}

		private Shape(int nbPoints, float[] xPoints, float[] yPoints) {
			this.nbPoints = nbPoints;
			this.xPoints = xPoints;
			this.yPoints = yPoints;
		}

		public int getNbPoints() {
			return this.nbPoints;
		}

		public float[] getxPoints() {
			return this.xPoints;
		}

		public float[] getyPoints() {
			return this.yPoints;
		}

	}

}
