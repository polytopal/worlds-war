package fr.utbm.info.vi51.worldswar.environment.envobject;

import java.awt.Point;
import java.util.Random;

import fr.utbm.info.vi51.worldswar.environment.Caste;
import fr.utbm.info.vi51.worldswar.environment.Colony;
import fr.utbm.info.vi51.worldswar.utils.Stock;
import io.sarl.lang.core.Agent;

/**
 * Represents an anthill on the map
 *
 */
public class AntHill extends StaticObject {

	/** Number of steps between the spawn of two ants in the anthill */
	public static final int SPAWN_COOLDOWN = 20;
	/** Food consumed by the spawn of a new ant */
	public static final int SPAWN_COST = 5;
	/** Food available in the ant hill at the beginning of the simulation **/
	private static final int INITIAL_FOOD_STOCK = 500;

	/** These numbers define the probability of each caste to be spawned **/
	private static final int GATHERER_FREQUENCY = 12;
	private static final int EXPLORER_FREQUENCY = 1;
	private static final int TOTAL_FREQUENCIES = GATHERER_FREQUENCY + EXPLORER_FREQUENCY;

	private final Stock foodStock;

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

	/**
	 * Determines which {@link Caste} the ant hill needs the most and wants to
	 * spawn. Currently, this behaviour is random, but it may be changed in the
	 * future to make a smarter decision, or even delegate this choice to a
	 * queen {@link Agent}.
	 * 
	 * @return the {@link Caste} that the ant hill wants to produce.
	 */
	@SuppressWarnings("static-method")
	public Caste casteToSpawn() {
		final int rand = new Random().nextInt(TOTAL_FREQUENCIES);
		assert rand < GATHERER_FREQUENCY + EXPLORER_FREQUENCY;
		if (rand < GATHERER_FREQUENCY) {
			return Caste.GATHERER;
		}
		if (rand < GATHERER_FREQUENCY + EXPLORER_FREQUENCY) {
			return Caste.EXPLORER;
		}
		throw new RuntimeException("Random number for caste choice was out of bound. rand = " + rand);
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
	 * @return the actual amount of food picked
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
	 * @return the available food in the ant hill
	 * @see fr.utbm.info.vi51.worldswar.utils.Stock#getAvailable()
	 */
	public int getAvailableFood() {
		return this.foodStock.getAvailable();
	}

	/**
	 * @return true if there is no food in the ant hill
	 * @see fr.utbm.info.vi51.worldswar.utils.Stock#isEmpty()
	 */
	public boolean isFoodEmpty() {
		return this.foodStock.isEmpty();
	}

}
