package fr.utbm.info.vi51.worldswar.perception.perceivable;

import fr.utbm.info.vi51.worldswar.environment.Colony;
import fr.utbm.info.vi51.worldswar.environment.PheromoneType;
import fr.utbm.info.vi51.worldswar.environment.envobject.Pheromone;

/**
 * {@link Perceivable} representation of a {@link Pheromone}
 */
public class PerceivablePheromone extends Perceivable {

	private final Pheromone pheromone;

	/***
	 * Builds the perceivable version of a {@link Pheromone}
	 * 
	 * @param pheromone
	 */
	public PerceivablePheromone(Pheromone pheromone) {
		super(pheromone);
		this.pheromone = pheromone;
	}

	/**
	 * @return the quantity of pheromone
	 * @see fr.utbm.info.vi51.worldswar.environment.envobject.Pheromone#getQty()
	 */
	public float getQty() {
		return this.pheromone.getQty();
	}

	/**
	 * @return the type of pheromone
	 * @see fr.utbm.info.vi51.worldswar.environment.envobject.Pheromone#getType()
	 */
	public PheromoneType getType() {
		return this.pheromone.getType();
	}

	/**
	 * @return the colony that placed this pheromone
	 * @see fr.utbm.info.vi51.worldswar.environment.envobject.Pheromone#getColony()
	 */
	public Colony getColony() {
		return this.pheromone.getColony();
	}

}
