package fr.utbm.info.vi51.worldswar.agent.behaviour.strategic;

import java.util.HashMap;

import fr.utbm.info.vi51.worldswar.agent.behaviour.tactical.AntTacticalBehaviour;
import fr.utbm.info.vi51.worldswar.environment.Caste;
import fr.utbm.info.vi51.worldswar.environment.influence.Influence;
import fr.utbm.info.vi51.worldswar.perception.AntPerception;

/**
 * Defines the behaviour of {@link Caste#EXPLORER} ants. They will wander
 * without following food pheromones to find food in new, unexplored areas.
 *
 */
public class ExplorerStrategicBehaviour implements AntStrategicBehaviour {

	private final AntTacticalBehaviour tacticalBehaviour;

	/**
	 * @param tacticalBehaviour
	 */
	public ExplorerStrategicBehaviour(AntTacticalBehaviour tacticalBehaviour) {
		this.tacticalBehaviour = tacticalBehaviour;
	}

	@Override
	public Influence computeInfluence(AntPerception perception, HashMap<String, Object> memory) {
		if (perception.getMyBody().getFoodCarried() > 0) {
			return this.tacticalBehaviour.bringFoodHome(perception, memory);
		}
		return this.tacticalBehaviour.collectFood(perception, memory);
	}

}
