package fr.utbm.info.vi51.worldswar.environment.envobject;

import java.awt.Point;

/**
 * {@link EnvironmentObject} that will never be displaced on the map
 *
 */
public abstract class StaticObject extends EnvironmentObject {

	/**
	 * Constructor
	 * 
	 * @param position
	 *            the position of the object (in global coordinates)
	 */
	public StaticObject(Point position) {
		super(position);
	}
}
