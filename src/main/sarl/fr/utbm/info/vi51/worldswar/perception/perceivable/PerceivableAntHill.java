package fr.utbm.info.vi51.worldswar.perception.perceivable;

import fr.utbm.info.vi51.worldswar.environment.Colony;
import fr.utbm.info.vi51.worldswar.environment.envobject.AntHill;

/**
 * {@link Perceivable} representation of an {@link AntHill}
 */
public class PerceivableAntHill extends Perceivable {

	private final AntHill antHill;

	/**
	 * Builds the perceivable version of an {@link AntHill}
	 * 
	 * @param antHill
	 */
	public PerceivableAntHill(AntHill antHill) {
		super(antHill);
		this.antHill = antHill;
	}

	/**
	 * @return the colony of the ant hill
	 * @see fr.utbm.info.vi51.worldswar.environment.envobject.AntHill#getColony()
	 */
	public Colony getColony() {
		return this.antHill.getColony();
	}

	/**
	 * @return the food currently stored in the ant hill
	 * @see fr.utbm.info.vi51.worldswar.environment.envobject.AntHill#getAvailableFood()
	 */
	public int getAvailableFood() {
		return this.antHill.getAvailableFood();
	}

	/**
	 * @return true if there is no food in the ant hill
	 * @see fr.utbm.info.vi51.worldswar.environment.envobject.AntHill#isFoodEmpty()
	 */
	public boolean isFoodEmpty() {
		return this.antHill.isFoodEmpty();
	}

}
