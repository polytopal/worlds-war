package fr.utbm.info.vi51.worldswar.environment.envobject;

import java.awt.Point;

import fr.utbm.info.vi51.worldswar.environment.Colony;
import fr.utbm.info.vi51.worldswar.environment.PheromoneType;

/**
 * Represents a pheromone unit placed on the map
 *
 */
public class Pheromone extends StaticObject {

	/**
	 * Flat amount of qty lost each step
	 */
	public static final float FLAT_DISSIPATION = 0.03f;

	/**
	 * Ratio of the qty lost each step
	 */
	public static final float SCALING_DISSIPATION = 0.001f;

	private float qty;
	private PheromoneType type;
	private Colony colony;

	/**
	 * Places a new unit of pheromone on the map
	 * 
	 * @param position
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

	/**
	 * Lowers the quantity of this pheromone to simulate its dissipation over
	 * time
	 */
	public void dissipate() {
		this.qty -= FLAT_DISSIPATION + SCALING_DISSIPATION * this.qty;
		if (this.qty < 0) {
			this.qty = 0;
		}
	}

	@Override
	public boolean isTraversable() {
		return true;
	}

	/**
	 * Adds the specified qty of pheromone
	 * 
	 * @param addedQty
	 */
	public void addQty(float addedQty) {
		this.qty = Math.max(addedQty, this.qty);
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
