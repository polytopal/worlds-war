package fr.utbm.info.vi51.worldswar.environment.envobject;

import java.awt.Point;

/**
 * {@link EnvironmentObject} that may be displaced on the map
 *
 */
public abstract class DynamicObject extends EnvironmentObject {

	public DynamicObject(Point position) {
		super(position);
	}

	public void setPosition(Point position) {
		this.position = position;
	}

}
