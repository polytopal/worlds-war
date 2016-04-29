package fr.utbm.info.vi51.worldswar.environment.envobject;

import java.awt.Point;
import java.util.UUID;

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
	private final int capacity;

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

	/**
	 * @param position
	 *            the global position of the body on the grid
	 * @param uuid
	 *            the UUID of the agent that owns the body
	 * @param colony
	 *            the colony of the ant
	 * @param caste
	 *            the caste of the ant
	 */
	public AntBody(Point position, UUID uuid, Colony colony, Caste caste) {
		super(position, uuid);
		this.colony = colony;
		this.caste = caste;

		// TODO placeholder constructor, stats need to be calculated
		this.capacity = 42;
	}

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
	 * Decrements the remaining life time of the ant by 1 step.
	 */
	public void decrementRemainingLifeTime() {
		this.remainingLifeTime--;
	}

	/**
	 * @return the foodCarried
	 */
	public int getFoodCarried() {
		return this.foodCarried;
	}

	/**
	 * @param foodCarried
	 *            the foodCarried to set
	 */
	public void setFoodCarried(int foodCarried) {
		this.foodCarried = foodCarried;
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
