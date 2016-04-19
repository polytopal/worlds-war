package fr.utbm.info.vi51.worldswar.environment.object;

import fr.utbm.info.vi51.worldswar.environment.Caste;
import fr.utbm.info.vi51.worldswar.environment.Colony;

/**
 * Physical body of an {@link Ant}
 *
 */
public class AntBody extends AgentBody {

	private int capacity;
	private int foodCarried;
	private int health;

	private boolean burrowed;

	private int timeToLive;

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
	public int getTimeToLive() {
		return this.timeToLive;
	}

	/**
	 * @param timeToLive
	 *            the timeToLive to set
	 */
	public void setTimeToLive(int timeToLive) {
		this.timeToLive = timeToLive;
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
