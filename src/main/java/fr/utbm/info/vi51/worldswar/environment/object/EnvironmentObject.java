package fr.utbm.info.vi51.worldswar.environment.object;

/**
 * A physical object of the {@link Environment}
 *
 */
public abstract class EnvironmentObject {

	/**
	 * @return whether this object can be placed on top of others or not.
	 */
	abstract public boolean isTraversable();

}
