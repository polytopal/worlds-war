package fr.utbm.info.vi51.worldswar.environment.influence;

import fr.utbm.info.vi51.worldswar.environment.Direction;
import fr.utbm.info.vi51.worldswar.environment.PheromoneType;

/**
 * Places pheromone on the current location, then move in the specified
 * {@link Direction}. {@see Influence}
 *
 */
public class PheromoneAndMoveInfluence implements Influence {
	private Direction direction;

	private float pheromoneQty;
	private PheromoneType pheromoneType;

	/**
	 * 
	 * @param type
	 *            the type of pheromone to leave
	 * @param qty
	 *            the quantity of pheromone
	 * @param d
	 *            the direction where to move
	 */
	public PheromoneAndMoveInfluence(PheromoneType type, float qty, Direction d) {
		this.pheromoneType = type;
		this.pheromoneQty = qty;
		this.direction = d;
	}

	public float getPheromoneQty() {
		return this.pheromoneQty;
	}

	public PheromoneType getPheromoneType() {
		return this.pheromoneType;
	}

	public Direction getDirection() {
		return this.direction;
	}
}
