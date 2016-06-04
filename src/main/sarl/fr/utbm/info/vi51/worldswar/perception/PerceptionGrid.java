package fr.utbm.info.vi51.worldswar.perception;

import java.awt.Point;
import java.util.List;

import javax.naming.directory.InvalidAttributesException;

import fr.utbm.info.vi51.worldswar.environment.EnvCell;
import fr.utbm.info.vi51.worldswar.environment.envobject.AgentBody;
import fr.utbm.info.vi51.worldswar.environment.envobject.EnvironmentObject;
import fr.utbm.info.vi51.worldswar.utils.Grid;

public class PerceptionGrid {

	public static final Point MY_POSITION = new Point(0, 0);

	private final Grid<PerceptionCell> perceptionCellGrid;

	/**
	 * Constructor
	 * 
	 * @param perceptionCellGrid
	 *            {@link Grid}<{@link PerceptionCell}>
	 */
	public PerceptionGrid(Grid<PerceptionCell> perceptionCellGrid) {
		this.perceptionCellGrid = perceptionCellGrid;
	}

	/**
	 * Builds a new PerceptionGrid built based on a grid of EnvCell
	 * 
	 * @param envObjectCellGrid
	 *            {@link Grid}<{@link EnvCell}>
	 * @return new PerceptionGrid
	 */
	public static PerceptionGrid buildFromEnvObjectGrid(Grid<EnvCell> envObjectCellGrid) {
		final int xMin = envObjectCellGrid.getXMin();
		final int xMax = envObjectCellGrid.getXMax();
		final int yMin = envObjectCellGrid.getYMin();
		final int yMax = envObjectCellGrid.getYMax();

		final Grid<PerceptionCell> perceptionCellGrid = new Grid<>(xMin, xMax, yMin, yMax);

		for (int x = xMin; x <= xMax; x++) {
			for (int y = yMin; y <= yMax; y++) {
				// foreach cell
				final List<EnvironmentObject> envObjects = envObjectCellGrid.get(x, y).getEnvObjects();
				final PerceptionCell perceptionCell = new PerceptionCell(envObjects);

				perceptionCellGrid.set(x, y, perceptionCell);
			}
		}
		return new PerceptionGrid(perceptionCellGrid);
	}

	public PerceptionGrid computeAgentPerception(AgentBody agentBody) {
		final Point position = agentBody.getPosition();
		final int range = agentBody.getPerceptionRange();

		try {
			// coordinate system change
			final Grid<PerceptionCell> localCellGrid = this.perceptionCellGrid.getSubGrid(position, range);

			/*
			 * Remove this if we need to compute the manhattan distance
			 */
			// manhattanDistanceCircle(range, localCellGrid);
			return new PerceptionGrid(localCellGrid);
		} catch (final InvalidAttributesException e) {
			System.err.println("Error : the body is not in the grid : " + e); //$NON-NLS-1$
			return null;
		}

	}

	@SuppressWarnings("unused")
	private static void manhattanDistanceCircle(final int range, final Grid<PerceptionCell> localCellGrid) {
		// circle with Manhattan distance
		for (int x = localCellGrid.getXMin(); x <= localCellGrid.getXMax(); x++) {
			for (int y = localCellGrid.getYMin(); y <= localCellGrid.getYMax(); y++) {
				if (Math.abs(x + y) > range) {
					localCellGrid.set(x, y, null);
				}
			}
		}
	}

	/**
	 * @return the width of the grid
	 */
	public int getWidth() {
		return this.perceptionCellGrid.getWidth();
	}

	/**
	 * @return the height of the grid
	 */
	public int getHeight() {
		return this.perceptionCellGrid.getHeight();
	}

	/**
	 * @param x
	 * @param y
	 * @return the {@link PerceptionCell} in position (x,y)
	 */
	public PerceptionCell getCell(int x, int y) {
		return this.perceptionCellGrid.get(x, y);
	}

	/**
	 * @param position
	 * @return the {@link PerceptionCell} in the given position
	 */
	public PerceptionCell getCell(Point position) {
		return this.getCell(position.x, position.y);
	}

	@Override
	public String toString() {
		return this.perceptionCellGrid.toString();
	}

	/**
	 * @return
	 * @see fr.utbm.info.vi51.worldswar.utils.Grid#getXMin()
	 */
	public int getXMin() {
		return this.perceptionCellGrid.getXMin();
	}

	/**
	 * @return
	 * @see fr.utbm.info.vi51.worldswar.utils.Grid#getYMin()
	 */
	public int getYMin() {
		return this.perceptionCellGrid.getYMin();
	}

	/**
	 * @return
	 * @see fr.utbm.info.vi51.worldswar.utils.Grid#getXMax()
	 */
	public int getXMax() {
		return this.perceptionCellGrid.getXMax();
	}

	/**
	 * @return
	 * @see fr.utbm.info.vi51.worldswar.utils.Grid#getYMax()
	 */
	public int getYMax() {
		return this.perceptionCellGrid.getYMax();
	}

	/**
	 * @param position
	 * @return whether the specified position is in the bounds of the grid
	 */
	public boolean containsPosition(Point position) {
		return this.perceptionCellGrid.containsPosition(position);
	}

}
