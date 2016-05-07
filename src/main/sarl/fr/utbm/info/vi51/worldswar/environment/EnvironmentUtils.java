package fr.utbm.info.vi51.worldswar.environment;

import java.awt.Point;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import fr.utbm.info.vi51.worldswar.controller.SimulationParameters;
import fr.utbm.info.vi51.worldswar.environment.envobject.AntBody;
import fr.utbm.info.vi51.worldswar.environment.envobject.AntHill;
import fr.utbm.info.vi51.worldswar.environment.envobject.EnvironmentObject;
import fr.utbm.info.vi51.worldswar.environment.envobject.Food;
import fr.utbm.info.vi51.worldswar.environment.envobject.Pheromone;
import fr.utbm.info.vi51.worldswar.utils.Grid;

/**
 * Contains static utils methods to help on environment stuff, e.g. pheromones
 * dissipation, objects displacement, etc
 *
 */
public class EnvironmentUtils {
	/**
	 * Private empty constructor : this class is not meant to be instanciated
	 */
	private EnvironmentUtils() {
	}

	public static Grid<EnvCell> generateGround(SimulationParameters simulationParameters) {

		final int width = simulationParameters.getGridWidth();
		final int height = simulationParameters.getGridHeight();
		final List<Colony> coloniesList = simulationParameters.getColoniesList();

		final Grid<EnvCell> grid = new Grid<>(0, width - 1, 0, height - 1);
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				final EnvCell envCell = new EnvCell();
				grid.set(x, y, envCell);

				// TODO add envObjects here

				// --- used to test GUI ---
				if (x == 10 && y == 10) {
					envCell.addEnvObject(new AntHill(new Point(x, y), coloniesList.get(0)));
				}
				if (Math.random() > 0.9) {
					envCell.addEnvObject(new Food(new Point(x, y), (int) (Math.random() * 10) + 1));
				}
				if (Math.random() > 0.95) {
					Collections.shuffle(coloniesList);
					final List<Caste> castes = Arrays.asList(Caste.values());
					Collections.shuffle(castes);
					envCell.addEnvObject(
							new AntBody(new Point(x, y), new UUID(0, 0), coloniesList.get(0), castes.get(0)));
				}
				// ------------------------
			}
		}
		return grid;
	}

	/**
	 * Applies one step of {@link Pheromone} dissipation. If the pheromone
	 * quantity reaches zero, the corresponding {@link EnvironmentObject} is
	 * removed from the map.
	 * 
	 * @param grid
	 *            the environment grid
	 * 
	 * 
	 * @see Pheromone#dissipate()
	 */
	public static void applyPheromoneDissipation(Grid<EnvCell> grid) {

		for (final EnvCell c : grid) {
			final List<EnvironmentObject> toRemove = new LinkedList<>();
			for (final EnvironmentObject envObj : c.getEnvObjects()) {
				if (envObj instanceof Pheromone) {
					final Pheromone p = (Pheromone) envObj;
					p.dissipate();
					if (p.getQty() <= 0) {
						toRemove.add(p);
					}
				}
			}
			c.removeAllEnvObjects(toRemove);
		}

	}

}
