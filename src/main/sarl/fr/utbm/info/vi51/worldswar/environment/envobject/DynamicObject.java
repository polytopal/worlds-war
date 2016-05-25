package fr.utbm.info.vi51.worldswar.environment.envobject;

import java.awt.Point;

/**
 * {@link EnvironmentObject} that may be displaced on the map
 *
 */
public abstract class DynamicObject extends EnvironmentObject {

	/**
	 * Constructor
	 * 
	 * @param position
	 *            the initial position of the object (global coordinates)
	 */
	public DynamicObject(Point position) {
		super(position);
	}

	/**
	 * @param position
	 *            the new position (global coordinates)
	 */
	public void setPosition(Point position) {
		this.position = position;
	}

}
