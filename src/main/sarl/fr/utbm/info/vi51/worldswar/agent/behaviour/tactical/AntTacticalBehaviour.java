package fr.utbm.info.vi51.worldswar.agent.behaviour.tactical;

import static fr.utbm.info.vi51.worldswar.perception.PerceptionGrid.MY_POSITION;

import java.awt.Point;
import java.util.HashMap;

import fr.utbm.info.vi51.worldswar.agent.behaviour.operational.AntOperationalBehaviour;
import fr.utbm.info.vi51.worldswar.environment.PheromoneType;
import fr.utbm.info.vi51.worldswar.environment.influence.DoNothingInfluence;
import fr.utbm.info.vi51.worldswar.environment.influence.Influence;
import fr.utbm.info.vi51.worldswar.perception.AntPerception;
import fr.utbm.info.vi51.worldswar.utils.Direction;

/**
 * Define basic tactical behaviour for ants.
 *
 */
public class AntTacticalBehaviour {

	private final AntOperationalBehaviour operationalBehaviour;

	/**
	 * A food trail intensity depends on the quantity of food discovered. This
	 * number corresponds to the quantity of food needed to dispose a maximum
	 * intensity food trail.
	 */
	private static final float MAX_FOOD_TRAIL_QTY = 50;

	/**
	 * @param operationalBehaviour
	 */
	public AntTacticalBehaviour(AntOperationalBehaviour operationalBehaviour) {
		this.operationalBehaviour = operationalBehaviour;
	}

	/**
	 * Tries to find the best position to target to collect food, or picks the
	 * food that is on the ground.
	 * 
	 * @param perception
	 * @param memory
	 * @return the resulting influence
	 */
	public Influence collectFood(AntPerception perception, HashMap<String, Object> memory) {
		if (perception.isAtHome()) {
			this.operationalBehaviour.startPheromoneTrail(memory, PheromoneType.HOME);
		}
		if (perception.getFoodAt(MY_POSITION) > 0) {
			this.startFoodTrail(perception, memory);
			return this.operationalBehaviour.pickFood(perception);
		}
		if (perception.isAvailableFoodInSight()) {
			return this.operationalBehaviour.moveToTarget(perception, memory, perception.getClosestAvailableFoodPos());
		}
		final Point highestFoodPheromonePos = perception.getHighestPheromonePos(PheromoneType.FOOD);
		if (highestFoodPheromonePos != null) {
			return this.operationalBehaviour.moveToTarget(perception, memory, highestFoodPheromonePos);
		}
		return this.operationalBehaviour.wander(perception, memory);
	}

	/**
	 * Wanders randomly, trying to find food in unexplored areas
	 * 
	 * @param perception
	 * @param memory
	 * @return the resulting influence
	 */
	public Influence wanderForFood(AntPerception perception, HashMap<String, Object> memory) {
		if (perception.isAtHome()) {
			this.operationalBehaviour.startPheromoneTrail(memory, PheromoneType.HOME);
		}
		if (perception.getFoodAt(MY_POSITION) > 0) {
			this.startFoodTrail(perception, memory);
			return this.operationalBehaviour.pickFood(perception);
		}
		if (perception.isAvailableFoodInSight()) {
			return this.operationalBehaviour.moveToTarget(perception, memory, perception.getClosestAvailableFoodPos());
		}
		return this.operationalBehaviour.wander(perception, memory);
	}

	/**
	 * Tries to find the best position to target to go home, or drops the
	 * carried food if currently at home
	 * 
	 * @param perception
	 * @param memory
	 * @return the resulting influence
	 */
	public Influence bringFoodHome(AntPerception perception, HashMap<String, Object> memory) {
		if (perception.isAtHome()) {
			return this.operationalBehaviour.dropFood(perception);
		}
		return this.goHome(perception, memory);
	}

	/**
	 * Tries to find the best position to target to go home
	 * 
	 * @param perception
	 * @param memory
	 * @return the resulting influence
	 */
	public Influence goHome(AntPerception perception, HashMap<String, Object> memory) {
		if (perception.isHomeInSight()) {
			if (perception.isAtHome()) {
				return new DoNothingInfluence();
			}
			return this.operationalBehaviour.moveToTarget(perception, memory, perception.getHomePos());
		}

		final Point highestAntPheromonePos = perception.getHighestPheromonePos(PheromoneType.HOME);
		if (highestAntPheromonePos != null) {
			return this.operationalBehaviour.moveToTarget(perception, memory, highestAntPheromonePos);
		}
		return this.operationalBehaviour.wander(perception, memory);
	}

	/**
	 * Starts a food trail, auto determining its intensity given the quantity of
	 * food in sight
	 * 
	 * @param perception
	 * @param memory
	 */
	private void startFoodTrail(AntPerception perception, HashMap<String, Object> memory) {
		int foodInSight = perception.countFoodInSight() - perception.getMyBody().getCapacity();
		float trailCoeff = foodInSight / MAX_FOOD_TRAIL_QTY;
		trailCoeff = Math.min(trailCoeff, 1);
		trailCoeff = Math.max(trailCoeff, 0);
		this.operationalBehaviour.startPheromoneTrail(memory, PheromoneType.FOOD, trailCoeff);
	}

	/**
	 * Starts a danger trail. The quantity of pheromones dropped depends on the
	 * number/caste of enemies spotted
	 * 
	 * @param perception
	 * @param memory
	 */
	public void startDangerTrail(AntPerception perception, HashMap<String, Object> memory) {
		// TODO should take acocunt of the qty/caste of the ennemies
		this.operationalBehaviour.startPheromoneTrail(memory, PheromoneType.DANGER);
	}

	/**
	 * Patrols around the hill.
	 * 
	 * @param perception
	 * @param memory
	 * @return the resulting influence
	 */
	public Influence patrol(AntPerception perception, HashMap<String, Object> memory) {
		if (perception.isAtHome()) {
			this.operationalBehaviour.startPheromoneTrail(memory, PheromoneType.HOME);
		}
		// TODO implement a real patrolling behaviour, atm the warriors camp
		return new DoNothingInfluence();

	}

	/**
	 * Simple flee behaviour : the ant simply runs back home, without avoiding
	 * the enemies on its way
	 * 
	 * @param perception
	 * @param memory
	 * @return the resulting influence
	 */
	public Influence flee(AntPerception perception, HashMap<String, Object> memory) {
		return this.goHome(perception, memory);
	}

	/**
	 * The ant will pursue its enemies, using the danger trails left by the
	 * other ants to find them
	 * 
	 * @param perception
	 * @param memory
	 * @return the resulting influence
	 */
	public Influence chaseAndFight(AntPerception perception, HashMap<String, Object> memory) {
		if (perception.isAtHome()) {
			this.operationalBehaviour.startPheromoneTrail(memory, PheromoneType.HOME);
		}

		if (perception.isEnemyInMeleeRange()) {
			Direction direction = Direction.fromPoint(perception.getClosestEnemyPos());
			if (null == direction) {
				return this.operationalBehaviour.attackMeleeTarget(direction);
			}
		}
		if (perception.isEnemyInSight()) {

			return this.operationalBehaviour.moveToTarget(perception, memory, perception.getClosestEnemyPos());
		}
		final Point highestDangerPheromonePos = perception.getHighestPheromonePos(PheromoneType.DANGER);
		if (highestDangerPheromonePos != null) {
			return this.operationalBehaviour.moveToTarget(perception, memory, highestDangerPheromonePos);
		}
		return this.operationalBehaviour.wander(perception, memory);
	}
}
