package fr.utbm.info.vi51.worldswar.perception;

import static fr.utbm.info.vi51.worldswar.perception.PerceptionGrid.MY_POSITION;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import fr.utbm.info.vi51.worldswar.environment.Colony;
import fr.utbm.info.vi51.worldswar.environment.PheromoneType;
import fr.utbm.info.vi51.worldswar.environment.envobject.AntBody;
import fr.utbm.info.vi51.worldswar.perception.perceivable.PerceivableAnt;
import fr.utbm.info.vi51.worldswar.perception.perceivable.PerceivableAntHill;
import fr.utbm.info.vi51.worldswar.perception.perceivable.PerceivableFood;

/**
 * Contains all the perceptions of an {@link AntBody} and provides utility
 * methods to make them digest.
 * 
 */
public class AntPerception {

	private final PerceivableAnt myBody;
	private final PerceptionGrid grid;

	/**
	 * 
	 * @param myBody
	 *            the body of the ant
	 * @param grid
	 *            the PerceptionGrid containing the perceivables perceived by
	 *            the ant
	 */
	public AntPerception(PerceivableAnt myBody, PerceptionGrid grid) {
		this.grid = grid;
		this.myBody = myBody;
	}

	/**
	 * @return the {@link PerceivableAnt} of the ant's own body
	 */
	public PerceivableAnt getMyBody() {
		return this.myBody;
	}

	/**
	 * @param type
	 * @param colony
	 * @return the position (in local coordinates) where there is the most
	 *         pheromone of the given {@link PheromoneType} and {@link Colony},
	 *         or {@code null} if no relevant pheromone is perceived
	 */
	public Point getHighestPheromonePos(PheromoneType type, Colony colony) {
		float highestValue = 0;
		Point highestPos = null;

		float currentValue = 0;
		for (int x = this.grid.getXMin(); x <= this.grid.getXMax(); x++) {
			for (int y = this.grid.getYMin(); y <= this.grid.getYMax(); y++) {
				if (this.grid.getCell(x, y) != null) {
					currentValue = this.grid.getCell(x, y).getPheromoneQuantity(type, colony);
					if (currentValue > highestValue) {
						highestValue = currentValue;
						highestPos = new Point(x, y);
					}
				}
			}
		}
		return highestPos;
	}

	/**
	 * 
	 * @param type
	 * @return the position (in local coordinates) where there is the most
	 *         pheromone of the given {@link PheromoneType} and the ant's own
	 *         colony, or {@code null} if no relevant pheromone is perceived
	 * @see AntPerception#getHighestPheromonePos(PheromoneType, Colony)
	 */
	public Point getHighestPheromonePos(PheromoneType type) {
		return this.getHighestPheromonePos(type, this.myBody.getColony());
	}

	/**
	 * Size of the list used in
	 * {@link AntPerception#getClosestAvailableFoodPos()} to store the closest
	 * positions containing food. Should be as small as possible, but big enough
	 * to prevent a reallocation
	 * 
	 * @see AntPerception#getClosestAvailableFoodPos()
	 */
	private static final int CLOSEST_FOOD_LIST_SIZE = 8;

	/**
	 * @return the position (in local coordinates) of the nearest source of food
	 *         on the ground, or {@code null} if no food is perceived. If more
	 *         than one source of food share the same distance, return a random
	 *         one among the closest. Can not return a position that is not
	 *         traversable (for instance if there is an ant on it). Does not
	 *         take into account the food that is stored in an {@ink AntHill}.
	 */
	public Point getClosestAvailableFoodPos() {
		// TODO rewrite this if we allow diagonal movements. In this case, it
		// could be optimized by iterating on the distance from the ant rather
		// than on the grid

		// TODO this looks like a good candidate to implement a cache system :
		// this should not be computed more than once for each ant perception.
		// Think about it after we have made the first simulations.

		final List<Point> closestPositions = new ArrayList<>(CLOSEST_FOOD_LIST_SIZE);
		int minDistance = Integer.MAX_VALUE;

		int distance;
		for (int x = this.grid.getXMin(); x <= this.grid.getXMax(); x++) {
			for (int y = this.grid.getYMin(); y <= this.grid.getYMax(); y++) {
				if (this.grid.getCell(x, y) != null && this.grid.getCell(x, y).getFood() != null
						&& this.isTraversable(x, y)) {
					distance = Math.abs(x) + Math.abs(y);
					if (distance < minDistance) {
						minDistance = distance;
						closestPositions.clear();
						closestPositions.add(new Point(x, y));
					} else if (distance == minDistance) {
						closestPositions.add(new Point(x, y));
					}
				}
			}
		}
		if (closestPositions.isEmpty()) {
			return null;
		}
		return closestPositions.get(new Random().nextInt(closestPositions.size()));
	}

	/**
	 * @return {@code true} if there is available food in the ant's field of
	 *         perception
	 * @see AntPerception#getClosestAvailableFoodPos
	 */
	public boolean isAvailableFoodInSight() {
		return this.getClosestAvailableFoodPos() != null;
	}

	/**
	 * @return the total number of food units in sight.
	 */
	public int countFoodInSight() {
		int totalFood = 0;
		PerceivableFood food = null;

		for (int x = this.grid.getXMin(); x <= this.grid.getXMax(); x++) {
			for (int y = this.grid.getYMin(); y <= this.grid.getYMax(); y++) {
				if (this.grid.getCell(x, y) != null) {
					food = this.grid.getCell(x, y).getFood();
					if (food != null) {
						totalFood += food.getAvailable();
					}
				}
			}
		}
		return totalFood;
	}

	/**
	 * @return {@code true} if the ant's anthill is in its field of perception
	 */
	public boolean isHomeInSight() {
		return this.getHomePos() != null;
	}

	/**
	 * @return the position (in local coordinates) of the ant's anthill, or
	 *         {@code null} if it is not perceived by the ant
	 */
	public Point getHomePos() {
		PerceivableAntHill antHill;
		for (int x = this.grid.getXMin(); x <= this.grid.getXMax(); x++) {
			for (int y = this.grid.getYMin(); y <= this.grid.getYMax(); y++) {
				if (this.grid.getCell(x, y) != null) {
					antHill = this.grid.getCell(x, y).getAntHill();
					if (antHill != null && antHill.getColony() == this.myBody.getColony()) {
						return new Point(x, y);
					}
				}
			}
		}
		return null;
	}

	private static final int CLOSEST_ENEMIES_LIST_SIZE = 8;

	private Point closestEnemyPosCache = null;
	private boolean closestEnemyPosCalculated = false;

	/**
	 * Serches in the perceptions the closest enemy without any caste
	 * distinction, only the distance counts
	 * 
	 * @return the Point corresponding to the position (in the local
	 *         coordinates) of one of the priority targets found
	 */
	public Point getClosestEnemyPos() {
		if (this.closestEnemyPosCalculated) {
			return this.closestEnemyPosCache;
		}
		this.closestEnemyPosCalculated = true;


		// TODO Doesn't manage the enemy anthill yet, which would be considered
		// here as an unique ant
		// -> if the hill is the closest target to the ant, it will become a
		// priority target, and this might cause some kamikaze attacks on the
		// hill #PearlHarbor:/

		final List<Point> closestPositions = new ArrayList<>(CLOSEST_ENEMIES_LIST_SIZE);
		int minDistance = Integer.MAX_VALUE;


		int distance;
		// searching in the perception grid in local coordinates
		for (int x = this.grid.getXMin(); x <= this.grid.getXMax(); x++) {
			for (int y = this.grid.getYMin(); y <= this.grid.getYMax(); y++) {
				// if there is a cell defined by those x and y coordinates
				if (this.grid.getCell(x, y) != null)  {
					PerceivableAnt ant = this.grid.getCell(x, y).getAnt();
					// if there is an ant of another colony on this cell
					if (ant != null && ant.getColony() != this.myBody.getColony()) {
						distance = Math.abs(x) + Math.abs(y);
						if (distance < minDistance) {
							minDistance = distance;
							closestPositions.clear();
							closestPositions.add(new Point(x, y));
						} else if (distance == minDistance) {
							// if several ants are at the same distance,
							// their positions are all added to the list
							closestPositions.add(new Point(x, y));
						}
					}
				}
			}
		}
		if (closestPositions.isEmpty()) {
			return null;
		}
		Point result = closestPositions.get(new Random().nextInt(closestPositions.size()));
		this.closestEnemyPosCache = result;
		return result;
	}

	/**
	 * @return {@code true} if there is an ant of an enemy colony in the field
	 *         of perceptions
	 * @see AntPerception#getClosestEnemyPos()
	 */
	public boolean isEnemyInSight() {
		return this.getClosestEnemyPos() != null;
	}

	/**
	 * 
	 * @return {code true} if there is either an enemy in sight, or some danger
	 *         pheromones in the field of perceptions
	 */
	public boolean isDangerNearby(){
		return (this.isEnemyInSight()
				|| this.getHighestPheromonePos(PheromoneType.DANGER, this.myBody.getColony()) != null);
	}

	/**
	 * 
	 * @return the number of enemies perceived, or maybe compute a danger ratio
	 *         based on the number and the caste of each enemy nearby
	 */
	public int countEnemiesInSight() {
		// TODO si on décide de modifier la qté de phéro DANGER déposées en
		// fonction de la qté d'ennemis perçus, pr gérer notamment la
		// fourmilière ennemie
		return 0;
	}

	/**
	 * @param position
	 *            the position in local coordinates to check
	 * @return true if the position is free, i.e. there is no wall, no ant body,
	 *         and no other blocking object on it.
	 */
	public boolean isTraversable(Point position) {
		return this.grid.containsPosition(position) && this.grid.getCell(position).isTraversable();
	}

	/**
	 * @param x
	 * @param y
	 * @return true if the position is free, i.e. there is no wall, no ant body,
	 *         and no other blocking object on it.
	 */
	public boolean isTraversable(int x, int y) {
		return this.isTraversable(new Point(x, y));
	}

	/**
	 * @param position
	 *            the position in local coordinates to check
	 * @return the quantity of food on the ground. Does not take into account
	 *         the food stored in an anthill.
	 */
	public int getFoodAt(Point position) {
		final PerceivableFood food = this.grid.getCell(position).getFood();
		if (food == null) {
			return 0;
		}
		return food.getAvailable();
	}

	/**
	 * @param position
	 * @param type
	 * @param colony
	 * @return the quantity of specified pheromone at the given location
	 */
	public float getPheromoneQtyAt(Point position, PheromoneType type, Colony colony) {
		return this.grid.getCell(position).getPheromoneQuantity(type, colony);
	}

	/**
	 * @return {@code true} if the ant is at home
	 */
	public boolean isAtHome() {
		final Point homePos = this.getHomePos();
		return (homePos != null && homePos.equals(MY_POSITION));
	}

	/**
	 * Checks whether there are any enemies in melee range or not
	 * 
	 * @return {code true} if there is at least one enemy within the attack
	 *         range
	 */
	public boolean isEnemyInMeleeRange() {
		if (!this.isEnemyInSight()){
			return false;
		}

		int range = this.myBody.getCaste().getMeleeRange();
		assert range <= this.myBody.getCaste().getPerceptionRange();
		/*
		 * Comparing the closest enemy pos (fast thanks to the cache system) to
		 * the attack range of the ants
		 */
		if (Math.abs(this.getClosestEnemyPos().getX()) <= range
				&& Math.abs(this.getClosestEnemyPos().getY()) <= range) {
			return true;
		}

		return false;
	}
}
