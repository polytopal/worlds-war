package fr.utbm.info.vi51.worldswar.environment.object;

/**
 * Represents a {@link StaticObject} that cannot be passed through
 *
 */
public class Wall extends EnvironmentObject {

	public Wall() {
		super();
	}

	@Override
	public boolean isTraversable() {
		return false;
	}

}
