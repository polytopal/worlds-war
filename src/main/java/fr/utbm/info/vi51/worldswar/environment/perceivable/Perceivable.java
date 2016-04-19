package fr.utbm.info.vi51.worldswar.environment.perceivable;

import java.awt.Point;

import fr.utbm.info.vi51.worldswar.environment.object.EnvironmentObject;

/**
 * Immutable class that permits giving agents a perception of an
 * {@link EnvironmentObject} without giving them write access.
 *
 */
public abstract class Perceivable {

	private EnvironmentObject object;

	public Perceivable(EnvironmentObject object) {
		this.object = object;
	}

	public Point getPosition() {
		return this.object.getPosition();
	}

	public boolean isTraversable() {
		return this.object.isTraversable();
	}

}
