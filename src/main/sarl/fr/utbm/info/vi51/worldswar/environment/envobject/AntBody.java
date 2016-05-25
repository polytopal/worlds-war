package fr.utbm.info.vi51.worldswar.environment.envobject;

import java.awt.Point;
import java.util.UUID;

import fr.utbm.info.vi51.worldswar.agent.AntAgent;
import fr.utbm.info.vi51.worldswar.environment.Breed;
import fr.utbm.info.vi51.worldswar.environment.Caste;
import fr.utbm.info.vi51.worldswar.environment.Colony;
import fr.utbm.info.vi51.worldswar.utils.Stock;

/**
 * Physical body of an {@link AntAgent}
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
	private final Stock foodCarried;

	/**
	 * Current health points
	 */
	private int health;

	/**
	 * The damages that will be done to the target in fight
	 */
	private final int attackDamage;

	private boolean burrowed;

	/**
	 * Number of remaining steps before the ant dies of old age
	 */
	private int remainingLifeTime;

	private final int perceptionRange;

	private final Caste caste;

	private final Colony colony;

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

		final Breed breed = colony.getBreed();
		this.health = computeStatistic(caste.getMaxHealth(), breed.getHealthMultiplier());
		this.attackDamage = computeStatistic(caste.getAttackDamage(), breed.getAttackDamageMultiplier());
		this.capacity = computeStatistic(caste.getCapacity(), breed.getCapacityMultiplier());
		this.remainingLifeTime = computeStatistic(caste.getLifeTime(), breed.getLifeTimeMultiplier());
		this.perceptionRange = computeStatistic(caste.getPerceptionRange(), breed.getPerceptionRangeMultiplier());

		this.foodCarried = new Stock(0);
		this.burrowed = false;
	}

	private static int computeStatistic(int baseStat, float multiplier) {
		final float stat = baseStat * multiplier;
		return Math.round(stat);
	}

	/**
	 * Performs an attack on the ant body, reducing its health
	 * 
	 * @param damage
	 */
	public void attack(int damage) {
		this.health = Math.max(this.health - damage, 0);
	}

	////////////////////////////////////////
	//////////// GETTERS/SETTERS ///////////
	////////////////////////////////////////

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
	 * @return the health
	 */
	public int getHealth() {
		return this.health;
	}

	/**
	 * 
	 * @return the attack damage
	 */
	public int getAttackDamage() {
		return this.attackDamage;
	}

	/**
	 * @return the capacity
	 */
	public int getCapacity() {
		return this.capacity;
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

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getPerceptionRange() {
		return this.perceptionRange;
	}

	////////////////////////////////////////
	/////////// FOOD STOCK METHODS /////////
	////////////////////////////////////////

	/**
	 * Picks food from the ant
	 * 
	 * @param qty
	 * @return the amount of food actually picked
	 * @see fr.utbm.info.vi51.worldswar.utils.Stock#pick(int)
	 */
	public int pickFood(int qty) {
		return this.foodCarried.pick(qty);
	}

	/**
	 * @param qty
	 * @see fr.utbm.info.vi51.worldswar.utils.Stock#drop(int)
	 */
	public void giveFood(int qty) {
		this.foodCarried.drop(qty);
	}

	/**
	 * @return the amount of food carried
	 * @see fr.utbm.info.vi51.worldswar.utils.Stock#getAvailable()
	 */
	public int getFoodCarried() {
		return this.foodCarried.getAvailable();
	}

	/**
	 * @return true if the ant is currently carrying food
	 * @see fr.utbm.info.vi51.worldswar.utils.Stock#isEmpty()
	 */
	public boolean carriesFood() {
		return !(this.foodCarried.isEmpty());
	}

	@Override
	public String toString() {
		return "AntBody [capacity=" + this.capacity + ", foodCarried=" + this.foodCarried + ", health=" + this.health //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				+ ", attackDamage=" + this.attackDamage + ", burrowed=" + this.burrowed + ", remainingLifeTime=" //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				+ this.remainingLifeTime + ", caste=" + this.caste + ", colony=" + this.colony + ", perceptionRange=" //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				+ this.perceptionRange + ", position=" + this.position + "]"; //$NON-NLS-1$ //$NON-NLS-2$
	}

}
