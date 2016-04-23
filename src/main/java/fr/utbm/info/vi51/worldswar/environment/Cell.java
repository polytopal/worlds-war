package fr.utbm.info.vi51.worldswar.environment;

import java.util.LinkedList;
import java.util.List;

import fr.utbm.info.vi51.worldswar.environment.envobject.EnvironmentObject;

/**
 * Stores the content of a tile in the environment grid.
 *
 */
public class Cell {
	private List<EnvironmentObject> envObjects;

	public Cell() {
		this.envObjects = new LinkedList<>();
	}

	public List<EnvironmentObject> getEnvObjects() {
		return this.envObjects;
	}
}
