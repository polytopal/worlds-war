package fr.utbm.info.vi51.worldswar.environment.object;

import java.awt.Point;

/**
 * A physical object of the {@link Environment}
 *
 */
public abstract class EnvironmentObject {

	/**
	 * @return the global position of the object on the map
	 */
	public Point getPosition() {
		// TODO see if we store the position in the cell or not...
		return null;
	}

	/**
	 * @return whether this object can be placed on top of others or not.
	 */
	abstract public boolean isTraversable();

}
