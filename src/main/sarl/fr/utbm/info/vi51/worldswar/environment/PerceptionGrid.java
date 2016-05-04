package fr.utbm.info.vi51.worldswar.environment;

import java.util.List;

import fr.utbm.info.vi51.worldswar.environment.envobject.EnvironmentObject;
import fr.utbm.info.vi51.worldswar.utils.Grid;

public class PerceptionGrid {

	private final Grid<PerceptionCell> perceptionCellGrid;

	public PerceptionGrid(Grid<EnvObjectCell> envObjectGrid) {

		final int xMin = envObjectGrid.getXMin();
		final int xMax = envObjectGrid.getXMax();
		final int yMin = envObjectGrid.getYMin();
		final int yMax = envObjectGrid.getYMax();

		this.perceptionCellGrid = new Grid<PerceptionCell>(xMin, xMax, yMin, yMax);

		for (int x = xMin; x <= xMax; x++) {
			for (int y = yMin; y <= yMax; y++) {
				// foreach cell
				final List<EnvironmentObject> envObjects = envObjectGrid.get(x, y).getEnvObjects();
				final PerceptionCell perceptionCell = new PerceptionCell(envObjects);

				this.perceptionCellGrid.set(x, y, perceptionCell);
			}
		}
	}
}
