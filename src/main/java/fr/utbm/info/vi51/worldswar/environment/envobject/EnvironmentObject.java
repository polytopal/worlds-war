package fr.utbm.info.vi51.worldswar.environment.envobject;

import java.awt.Point;

/**
 * A physical object of the {@link Environment}
 *
 */
public abstract class EnvironmentObject {

	protected Point position;

	public EnvironmentObject(Point position) {
		this.position = position;
	}

	/**
	 * @return whether this object can be placed on top of others or not.
	 */
	abstract public boolean isTraversable();

	/**
	 * @return the position
	 */
	public Point getPosition() {
		return this.position;
	}

}
