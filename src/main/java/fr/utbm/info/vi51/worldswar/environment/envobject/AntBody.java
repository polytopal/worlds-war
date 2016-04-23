package fr.utbm.info.vi51.worldswar.environment.envobject;

import fr.utbm.info.vi51.worldswar.environment.Caste;
import fr.utbm.info.vi51.worldswar.environment.Colony;

/**
 * Physical body of an {@link Ant}
 *
 */
public class AntBody extends AgentBody {

	/**
	 * Number of food units the ant is physically able to carry
	 */
	private int capacity;

	/**
	 * Number of food units currently carried by the ant
	 */
	private int foodCarried;

	/**
	 * Current health points
	 */
	private int health;

	private boolean burrowed;

	/**
	 * Number of remaining steps before the ant dies of old age
	 */
	private int remainingLifeTime;

	private Caste caste;

	private Colony colony;

	/** {@inheritDoc} **/
	@Override
	public boolean isTraversable() {
		return this.burrowed;
	}

	/**
	 * @return the timeToLive
	 */
	public int getRemainingLifeTime() {
		return this.remainingLifeTime;
	}

	/**
	 * @param remainingLifeTime
	 *            the remainingLifeTime to set
	 */
	public void setRemainingLifeTime(int remainingLifeTime) {
		this.remainingLifeTime = remainingLifeTime;
	}

	/**
	 * @return the foodCarried
	 */
	public int getFoodCarried() {
		return this.foodCarried;
	}

	/**
	 * @return the health
	 */
	public int getHealth() {
		return this.health;
	}

	/**
	 * @return the caste
	 */
	public Caste getCaste() {
		return this.caste;
	}

	/**
	 * @return the colony
	 */
	public Colony getColony() {
		return this.colony;
	}

	/**
	 * @return the burrowed
	 */
	public boolean isBurrowed() {
		return this.burrowed;
	}

	/**
	 * @param burrowed
	 *            the burrowed to set
	 */
	public void setBurrowed(boolean burrowed) {
		this.burrowed = burrowed;
	}
}
