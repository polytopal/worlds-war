package fr.utbm.info.vi51.worldswar.environment;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import fr.utbm.info.vi51.worldswar.environment.envobject.EnvironmentObject;

/**
 * Stores the content of a tile in the environment grid.
 *
 */
public class Cell {
	private final List<EnvironmentObject> envObjects;

	public Cell() {
		this.envObjects = new LinkedList<>();
	}

	public List<EnvironmentObject> getEnvObjects() {
		return this.envObjects;
	}

	/**
	 * @param envObj
	 *            the environmentObject to add in the cell
	 */
	public void addEnvObject(EnvironmentObject envObj) {
		this.envObjects.add(envObj);
	}

	/**
	 * @param envObj
	 *            the environmentObject to remove
	 * @return true if the object was found and removed, false otherwise
	 */
	public boolean removeEnvObject(EnvironmentObject envObj) {
		return this.envObjects.remove(envObj);
	}

	/**
	 * @param c
	 *            the collection of objects to remove from the cell
	 * @return true if something was removed
	 * @see java.util.List#removeAll(java.util.Collection)
	 */
	public boolean removeAllEnvObjects(Collection<? extends EnvironmentObject> c) {
		return this.envObjects.removeAll(c);
	}
}
