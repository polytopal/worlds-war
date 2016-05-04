package fr.utbm.info.vi51.worldswar.environment;

import java.awt.Point;
import java.util.List;

import javax.naming.directory.InvalidAttributesException;

import fr.utbm.info.vi51.worldswar.environment.envobject.AgentBody;
import fr.utbm.info.vi51.worldswar.environment.envobject.EnvironmentObject;
import fr.utbm.info.vi51.worldswar.utils.Grid;

public class PerceptionGrid {

	private final Grid<PerceptionCell> perceptionCellGrid;

	// TODO - c'est dégeu, trouver solution
	public PerceptionGrid(Grid<PerceptionCell> perceptionCellGrid, Object blblblbJeSertARien) {
		this.perceptionCellGrid = perceptionCellGrid;
	}

	public PerceptionGrid(Grid<EnvObjectCell> envObjectCellGrid) {

		final int xMin = envObjectCellGrid.getXMin();
		final int xMax = envObjectCellGrid.getXMax();
		final int yMin = envObjectCellGrid.getYMin();
		final int yMax = envObjectCellGrid.getYMax();

		this.perceptionCellGrid = new Grid<PerceptionCell>(xMin, xMax, yMin, yMax);

		for (int x = xMin; x <= xMax; x++) {
			for (int y = yMin; y <= yMax; y++) {
				// foreach cell
				final List<EnvironmentObject> envObjects = envObjectCellGrid.get(x, y).getEnvObjects();
				final PerceptionCell perceptionCell = new PerceptionCell(envObjects);

				this.perceptionCellGrid.set(x, y, perceptionCell);
			}
		}
	}

	public PerceptionGrid(int xMin, int xMax, int yMin, int yMax) {
		this.perceptionCellGrid = new Grid<>(xMin, xMax, yMin, yMax);
	}

	public PerceptionGrid computeAgentPerception(AgentBody agentBody) {
		final Point position = agentBody.getPosition();
		final int range = agentBody.getVisionRange();

		try {
			// coordinate system change
			final Grid<PerceptionCell> localCellGrid = this.perceptionCellGrid.getSubGrid(position, range);

			// with manhattan distance
			for (int x = localCellGrid.getXMin(); x < localCellGrid.getXMax(); x++) {
				for (int y = localCellGrid.getYMin(); y < localCellGrid.getYMax(); y++) {
					if (Math.abs(x + y) > range) {
						localCellGrid.set(x, y, null);
					}
				}
			}

			return new PerceptionGrid(localCellGrid, 0);
		} catch (final InvalidAttributesException e) {
			// TODO - use an other logger
			System.err.println("Error : the body is not in the grid"); //$NON-NLS-1$
			return null;
		}
	}
}
