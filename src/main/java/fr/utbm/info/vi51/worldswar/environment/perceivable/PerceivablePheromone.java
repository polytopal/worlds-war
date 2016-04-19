package fr.utbm.info.vi51.worldswar.environment.perceivable;

import fr.utbm.info.vi51.worldswar.environment.Colony;
import fr.utbm.info.vi51.worldswar.environment.PheromoneType;
import fr.utbm.info.vi51.worldswar.environment.object.Pheromone;

/**
 * {@link Perceivable} representation of a {@link Pheromone}
 */
public class PerceivablePheromone extends Perceivable {

	private Pheromone pheromone;

	public PerceivablePheromone(Pheromone pheromone) {
		super(pheromone);
		this.pheromone = pheromone;
	}

	/**
	 * @return
	 * @see fr.utbm.info.vi51.worldswar.environment.object.Pheromone#getQty()
	 */
	public float getQty() {
		return this.pheromone.getQty();
	}

	/**
	 * @return
	 * @see fr.utbm.info.vi51.worldswar.environment.object.Pheromone#getType()
	 */
	public PheromoneType getType() {
		return this.pheromone.getType();
	}

	/**
	 * @return
	 * @see fr.utbm.info.vi51.worldswar.environment.object.Pheromone#getColony()
	 */
	public Colony getColony() {
		return this.pheromone.getColony();
	}

}