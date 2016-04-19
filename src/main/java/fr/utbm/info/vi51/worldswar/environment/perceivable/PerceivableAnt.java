package fr.utbm.info.vi51.worldswar.environment.perceivable;

import fr.utbm.info.vi51.worldswar.environment.Caste;
import fr.utbm.info.vi51.worldswar.environment.Colony;
import fr.utbm.info.vi51.worldswar.environment.object.AntBody;

/**
 * {@link Perceivable} representation of an {@link AntBody}
 *
 */
public class PerceivableAnt extends Perceivable {

	private AntBody ant;

	public PerceivableAnt(AntBody ant) {
		super(ant);
	}

	/**
	 * @return
	 * @see fr.utbm.info.vi51.worldswar.environment.object.AntBody#getFoodCarried()
	 */
	public int getFoodCarried() {
		return this.ant.getFoodCarried();
	}

	/**
	 * @return
	 * @see fr.utbm.info.vi51.worldswar.environment.object.AntBody#getHealth()
	 */
	public int getHealth() {
		return this.ant.getHealth();
	}

	/**
	 * @return
	 * @see fr.utbm.info.vi51.worldswar.environment.object.AntBody#getCaste()
	 */
	public Caste getCaste() {
		return this.ant.getCaste();
	}

	/**
	 * @return
	 * @see fr.utbm.info.vi51.worldswar.environment.object.AntBody#getColony()
	 */
	public Colony getColony() {
		return this.ant.getColony();
	}

}
