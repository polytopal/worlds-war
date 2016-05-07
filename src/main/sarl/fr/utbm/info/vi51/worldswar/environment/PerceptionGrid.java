package fr.utbm.info.vi51.worldswar.environment;

import java.awt.Point;
import java.util.List;

import javax.naming.directory.InvalidAttributesException;

import fr.utbm.info.vi51.worldswar.environment.envobject.AgentBody;
import fr.utbm.info.vi51.worldswar.environment.envobject.EnvironmentObject;
import fr.utbm.info.vi51.worldswar.utils.Grid;

public class PerceptionGrid {

	private final Grid<PerceptionCell> perceptionCellGrid;

	public PerceptionGrid(Grid<PerceptionCell> perceptionCellGrid) {
		this.perceptionCellGrid = perceptionCellGrid;
	}

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
		final int range = AgentBody.PERCEPTION_RANGE;

		try {
			// coordinate system change
			final Grid<PerceptionCell> localCellGrid = this.perceptionCellGrid.getSubGrid(position, range);

			// System.out.println("localCellGrid : \n" + localCellGrid);

			// circle with Manhattan distance
			for (int x = localCellGrid.getXMin(); x <= localCellGrid.getXMax(); x++) {
				for (int y = localCellGrid.getYMin(); y <= localCellGrid.getYMax(); y++) {
					if (Math.abs(x + y) > range) {
						// TODO voir ici si on met clear la cellule ou si on la
						// met a null
						// localCellGrid.get(x, y).clear();
						localCellGrid.set(x, y, null);
					}
				}
			}

			// System.out.println("after manhattan : \n" + localCellGrid);

			return new PerceptionGrid(localCellGrid);
		} catch (final InvalidAttributesException e) {
			// TODO - use an other logger
			System.err.println("Error : the body is not in the grid : " + e); //$NON-NLS-1$
			return null;
		}
	}

	@Override
	public String toString() {
		return this.perceptionCellGrid.toString();
	}
}