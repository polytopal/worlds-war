package fr.utbm.info.vi51.worldswar.environment.envobject;

import java.awt.Point;

/**
 * {@link EnvironmentObject} that will never be displaced on the map
 *
 */
public abstract class StaticObject extends EnvironmentObject {

	public StaticObject(Point position) {
		super(position);
	}
}
