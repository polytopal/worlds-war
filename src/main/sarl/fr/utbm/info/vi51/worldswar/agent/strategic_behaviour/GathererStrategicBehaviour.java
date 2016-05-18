package fr.utbm.info.vi51.worldswar.agent.strategic_behaviour;

import java.util.HashMap;

import fr.utbm.info.vi51.worldswar.agent.tactical_behaviour.AntTacticalBehaviour;
import fr.utbm.info.vi51.worldswar.environment.influence.Influence;
import fr.utbm.info.vi51.worldswar.perception.AntPerception;

public class GathererStrategicBehaviour implements AntStrategicBehaviour {

	private final AntTacticalBehaviour tacticalBehaviour;

	public GathererStrategicBehaviour(AntTacticalBehaviour tacticalBehaviour) {
		this.tacticalBehaviour = tacticalBehaviour;
	}

	@Override
	public Influence computeInfluence(AntPerception perception, HashMap<String, Object> memory) {
		if (perception.getMyBody().getFoodCarried() >= perception.getMyBody().getCapacity()) {
			return this.tacticalBehaviour.BringFoodHome(perception, memory);
		}
		return this.tacticalBehaviour.collectFood(perception, memory);
	}
}
