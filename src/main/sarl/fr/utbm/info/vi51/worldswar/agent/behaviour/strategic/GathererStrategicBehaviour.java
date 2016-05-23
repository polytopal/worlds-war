package fr.utbm.info.vi51.worldswar.agent.behaviour.strategic;

import java.util.HashMap;

import fr.utbm.info.vi51.worldswar.agent.behaviour.tactical.AntTacticalBehaviour;
import fr.utbm.info.vi51.worldswar.environment.influence.Influence;
import fr.utbm.info.vi51.worldswar.perception.AntPerception;

/**
 * Implementation of a {@link AntStrategicBehaviour} for gatherers
 *
 */
public class GathererStrategicBehaviour implements AntStrategicBehaviour {

	private final AntTacticalBehaviour tacticalBehaviour;

	/**
	 * @param tacticalBehaviour
	 */
	public GathererStrategicBehaviour(AntTacticalBehaviour tacticalBehaviour) {
		this.tacticalBehaviour = tacticalBehaviour;
	}

	@Override
	public Influence computeInfluence(AntPerception perception, HashMap<String, Object> memory) {
		if (perception.getMyBody().getFoodCarried() > 0) {
			return this.tacticalBehaviour.BringFoodHome(perception, memory);
		}
		return this.tacticalBehaviour.collectFood(perception, memory);
	}
}
