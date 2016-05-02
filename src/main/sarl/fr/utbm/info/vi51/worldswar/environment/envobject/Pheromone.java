package fr.utbm.info.vi51.worldswar.environment.envobject;

import java.awt.Point;

import fr.utbm.info.vi51.worldswar.environment.Colony;
import fr.utbm.info.vi51.worldswar.environment.PheromoneType;

/**
 * Represents a pheromone unit placed on the map
 *
 */
public class Pheromone extends StaticObject {

	private float qty;
	private PheromoneType type;
	private Colony colony;

	/**
	 * Places a new unit of pheromone on the map
	 * 
	 * @param colony
	 *            the {@link Colony} of the ant that places the pheromone
	 * @param type
	 *            the {@link PheromoneType} to place
	 * @param qty
	 *            the quantity of pheromone to place
	 */
	public Pheromone(Point position, Colony colony, PheromoneType type, float qty) {
		super(position);
		this.colony = colony;
		this.type = type;
		this.qty = qty;
	}

	// TODO add methods to simulate the dissipation of pheromones (by reducing
	// the qty on each step)

	@Override
	public boolean isTraversable() {
		return true;
	}

	////////////////////////////////////////
	////////// GETTERS & SETTERS ///////////
	////////////////////////////////////////

	/**
	 * @return the qty
	 */
	public float getQty() {
		return this.qty;
	}

	/**
	 * @return the type
	 */
	public PheromoneType getType() {
		return this.type;
	}

	/**
	 * @return the colony
	 */
	public Colony getColony() {
		return this.colony;
	}

}
