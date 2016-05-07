package fr.utbm.info.vi51.worldswar.environment.envobject;

import java.awt.Point;

import fr.utbm.info.vi51.worldswar.environment.Colony;
import fr.utbm.info.vi51.worldswar.utils.Stock;

/**
 * Represents an anthill on the map
 *
 */
public class AntHill extends StaticObject {

	/** Number of steps between the spawn of two ants in the anthill */
	public static final int SPAWN_COOLDOWN = 5;
	/** Food consumed by the spawn of a new ant */
	public static final int SPAWN_COST = 25;

	private static final int INITIAL_FOOD_STOCK = 100;

	private Stock foodStock;

	private Colony colony;

	/** Number of steps before a new ant will spawn out of the anthill */
	private int spawnCooldown;

	/**
	 * @param position
	 * @param colony
	 */
	public AntHill(Point position, Colony colony) {
		super(position);
		this.colony = colony;
		this.foodStock = new Stock(INITIAL_FOOD_STOCK);
	}

	////////////////////////////////////////
	////////// GETTERS & SETTERS ///////////
	////////////////////////////////////////

	/**
	 * @return the colony
	 */
	public Colony getColony() {
		return this.colony;
	}

	/**
	 * @param colony
	 *            the colony to set
	 */
	public void setColony(Colony colony) {
		this.colony = colony;
	}

	/**
	 * @return the spawnCooldown
	 */
	public int getSpawnCooldown() {
		return this.spawnCooldown;
	}

	/**
	 * @param spawnCooldown
	 *            the spawnCooldown to set
	 */
	public void setSpawnCooldown(int spawnCooldown) {
		this.spawnCooldown = spawnCooldown;
	}

	/**
	 * Reduce by 1 the spawn cooldown. It can't be decremented under 0
	 */
	public void decrementSpawnCooldown() {
		if (this.spawnCooldown > 0) {
			this.spawnCooldown--;
		}
	}

	////////////////////////////////////////
	/// INHERITED FROM ENVIRONMENTOBJECT ///
	////////////////////////////////////////

	/** {@inheritDoc} **/
	@Override
	public boolean isTraversable() {
		return true;
	}

	////////////////////////////////////////
	/////////// FOOD STOCK METHODS /////////
	////////////////////////////////////////

	/**
	 * @param qty
	 * @return
	 * @see fr.utbm.info.vi51.worldswar.utils.Stock#pick(int)
	 */
	public int pickFood(int qty) {
		return this.foodStock.pick(qty);
	}

	/**
	 * @param qty
	 * @see fr.utbm.info.vi51.worldswar.utils.Stock#drop(int)
	 */
	public void dropFood(int qty) {
		this.foodStock.drop(qty);
	}

	/**
	 * @return
	 * @see fr.utbm.info.vi51.worldswar.utils.Stock#getAvailable()
	 */
	public int getAvailableFood() {
		return this.foodStock.getAvailable();
	}

	/**
	 * @return
	 * @see fr.utbm.info.vi51.worldswar.utils.Stock#isEmpty()
	 */
	public boolean isFoodEmpty() {
		return this.foodStock.isEmpty();
	}

}
