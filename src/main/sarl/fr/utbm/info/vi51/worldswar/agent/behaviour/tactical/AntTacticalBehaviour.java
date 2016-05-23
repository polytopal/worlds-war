package fr.utbm.info.vi51.worldswar.agent.behaviour.tactical;

import static fr.utbm.info.vi51.worldswar.perception.PerceptionGrid.MY_POSITION;

import java.awt.Point;
import java.util.HashMap;

import fr.utbm.info.vi51.worldswar.agent.behaviour.operational.AntOperationalBehaviour;
import fr.utbm.info.vi51.worldswar.environment.PheromoneType;
import fr.utbm.info.vi51.worldswar.environment.influence.DoNothingInfluence;
import fr.utbm.info.vi51.worldswar.environment.influence.Influence;
import fr.utbm.info.vi51.worldswar.perception.AntPerception;

/**
 * Define basic tactical behaviour for ants.
 *
 */
public class AntTacticalBehaviour {

	private final AntOperationalBehaviour operationalBehaviour;

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
			memory.put("pheromoneType", PheromoneType.HOME);
			memory.put("pheromoneDistance", new Integer(0));
		}
		if (perception.isFoodInSight()) {
			if (perception.getFoodAt(MY_POSITION) > 0) {
				memory.put("pheromoneType", PheromoneType.FOOD);
				memory.put("pheromoneDistance", new Integer(0));
				return this.operationalBehaviour.pickFood(perception);
			}
			return this.operationalBehaviour.moveToTarget(perception, memory, perception.getClosestAvailableFoodPos());
		}
		Point highestFoodPheromonePos = perception.getHighestPheromonePos(PheromoneType.FOOD);
		if (highestFoodPheromonePos != null) {
			return this.operationalBehaviour.moveToTarget(perception, memory, highestFoodPheromonePos);
		}
		return this.operationalBehaviour.wander(memory);
	}

	/**
	 * Tries to find the best position to target to go home, or drops the
	 * carried food if currently at home
	 * 
	 * @param perception
	 * @param memory
	 * @return the resulting influence
	 */
	public Influence BringFoodHome(AntPerception perception, HashMap<String, Object> memory) {
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

		Point highestAntPheromonePos = perception.getHighestPheromonePos(PheromoneType.HOME);
		if (highestAntPheromonePos != null) {
			return this.operationalBehaviour.moveToTarget(perception, memory, highestAntPheromonePos);
		}
		return this.operationalBehaviour.wander(memory);
	}
}
