package fr.utbm.info.vi51.worldswar.environment;

import java.util.LinkedList;
import java.util.List;

import fr.utbm.info.vi51.worldswar.environment.object.EnvironmentObject;

/**
 * Stores the content of a tile in the environment grid.
 *
 */
public class Cell {
	private List<EnvironmentObject> objects;

	public Cell() {
		this.objects = new LinkedList<>();
	}

	public List<EnvironmentObject> getObjects() {
		return this.objects;
	}
}
