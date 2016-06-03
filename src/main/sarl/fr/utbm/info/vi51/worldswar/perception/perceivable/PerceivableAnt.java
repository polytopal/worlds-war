package fr.utbm.info.vi51.worldswar.perception.perceivable;

import fr.utbm.info.vi51.worldswar.environment.Caste;
import fr.utbm.info.vi51.worldswar.environment.Colony;
import fr.utbm.info.vi51.worldswar.environment.envobject.AntBody;

/**
 * {@link Perceivable} representation of an {@link AntBody}
 *
 */
public class PerceivableAnt extends Perceivable {

	private final AntBody ant;

	/**
	 * Builds the perceivable version of an {@link AntBody}
	 * 
	 * @param ant
	 */
	public PerceivableAnt(AntBody ant) {
		super(ant);
		this.ant = ant;
	}

	/**
	 * @return the food carried quantity
	 * @see fr.utbm.info.vi51.worldswar.environment.envobject.AntBody#getFoodCarried()
	 */
	public int getFoodCarried() {
		return this.ant.getFoodCarried();
	}

	/**
	 * @return the food capacity of the ant
	 * @see fr.utbm.info.vi51.worldswar.environment.envobject.AntBody#getCapacity()
	 */
	public int getCapacity() {
		return this.ant.getCapacity();
	}

	/**
	 * @return the health of the ant
	 * @see fr.utbm.info.vi51.worldswar.environment.envobject.AntBody#getHealth()
	 */
	public int getHealth() {
		return this.ant.getHealth();
	}

	/**
	 * @return the {@link Caste} of the ant
	 * @see fr.utbm.info.vi51.worldswar.environment.envobject.AntBody#getCaste()
	 */
	public Caste getCaste() {
		return this.ant.getCaste();
	}

	/**
	 * @return the {@link Colony} of the ant
	 * @see fr.utbm.info.vi51.worldswar.environment.envobject.AntBody#getColony()
	 */
	public Colony getColony() {
		return this.ant.getColony();
	}

	/**
	 * @return true if the ant is burrowed
	 */
	public boolean isBurrowed() {
		return this.ant.isBurrowed();
	}

	@Override
	public String toString() {
		return "ant"; //$NON-NLS-1$
	}

}
