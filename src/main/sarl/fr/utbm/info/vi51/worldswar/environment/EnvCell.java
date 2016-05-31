package fr.utbm.info.vi51.worldswar.environment;

import java.awt.Point;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import fr.utbm.info.vi51.worldswar.environment.envobject.AntHill;
import fr.utbm.info.vi51.worldswar.environment.envobject.EnvironmentObject;

/**
 * Stores the content of a tile in the environment grid.
 *
 */
public class EnvCell {
	private final List<EnvironmentObject> envObjects;
	private Point position;

	public EnvCell(Point position) {
		this.envObjects = new LinkedList<>();
		this.position = position;
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

	/**
	 * @return whether the cell only contains traversable objects or not
	 */
	public boolean isTraversable() {
		boolean traversable = true;
		for (final EnvironmentObject o : this.envObjects) {
			if (!o.isTraversable()) {
				traversable = false;
			}
		}
		return traversable;
	}

	/**
	 * @param colony
	 *            The colony of the AntHill
	 * @return true if the cell contains the {@link AntHill} of the given
	 *         {@link Colony}
	 */
	public boolean isAntHillOf(Colony colony) {
		for (final EnvironmentObject o : this.envObjects) {
			if (o instanceof AntHill && ((AntHill) o).getColony() == (colony)) {
				return true;
			}
		}
		return false;
	}
	
	public Point getPosition() {
		return this.position;
	}
}
