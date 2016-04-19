package fr.utbm.info.vi51.worldswar.environment.perceivable;

import fr.utbm.info.vi51.worldswar.environment.Colony;
import fr.utbm.info.vi51.worldswar.environment.object.AntHill;

/**
 * {@link Perceivable} representation of an {@link AntHill}
 */
public class PerceivableAntHill extends Perceivable {

	private AntHill antHill;

	public PerceivableAntHill(AntHill antHill) {
		super(antHill);
		this.antHill = antHill;
	}

	/**
	 * @return
	 * @see fr.utbm.info.vi51.worldswar.environment.object.AntHill#getColony()
	 */
	public Colony getColony() {
		return this.antHill.getColony();
	}

	/**
	 * @return
	 * @see fr.utbm.info.vi51.worldswar.environment.object.AntHill#getAvailableFood()
	 */
	public int getAvailableFood() {
		return this.antHill.getAvailableFood();
	}

	/**
	 * @return
	 * @see fr.utbm.info.vi51.worldswar.environment.object.AntHill#isFoodEmpty()
	 */
	public boolean isFoodEmpty() {
		return this.antHill.isFoodEmpty();
	}

}
