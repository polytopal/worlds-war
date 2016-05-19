package fr.utbm.info.vi51.worldswar.perception.perceivable;

import fr.utbm.info.vi51.worldswar.environment.envobject.EnvironmentObject;

/**
 * Immutable class that permits giving agents a perception of an
 * {@link EnvironmentObject} without giving them write access.
 *
 */
public abstract class Perceivable {

	private final EnvironmentObject envObject;

	protected Perceivable(EnvironmentObject envObject) {
		this.envObject = envObject;
	}

	public boolean isTraversable() {
		return this.envObject.isTraversable();
	}

}
