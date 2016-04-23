package fr.utbm.info.vi51.worldswar.environment.perceivable;

import fr.utbm.info.vi51.worldswar.environment.envobject.Wall;

/**
 * {@link Perceivable} representation of a {@link Wall}
 *
 */
public class PerceivableWall extends Perceivable {

	private Wall wall;

	public PerceivableWall(Wall wall) {
		super(wall);
		this.wall = wall;
	}

}
