package fr.utbm.info.vi51.worldswar.environment;

import java.util.LinkedList;
import java.util.List;

import fr.utbm.info.vi51.worldswar.environment.envobject.EnvironmentObject;
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

		for (EnvCell c : grid) {
			List<EnvironmentObject> toRemove = new LinkedList<>();
			for (EnvironmentObject envObj : c.getEnvObjects()) {
				if (envObj instanceof Pheromone) {
					Pheromone p = (Pheromone) envObj;
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
