package fr.utbm.info.vi51.worldswar.environment.envobject;

import java.awt.Point;

/**
 * Represents a {@link StaticObject} that cannot be passed through
 *
 */
public class Wall extends StaticObject {

	/**
	 * @param position
	 */
	public Wall(Point position) {
		super(position);
	}

	@Override
	public boolean isTraversable() {
		return false;
	}

}
