package fr.utbm.info.vi51.worldswar.agent.strategic_behaviour;

import java.util.HashMap;

import fr.utbm.info.vi51.worldswar.agent.tactical_behaviour.CollectFoodTacticalBehaviour;
import fr.utbm.info.vi51.worldswar.agent.tactical_behaviour.GoHomeTacticalBehaviour;
import fr.utbm.info.vi51.worldswar.environment.influence.Influence;
import fr.utbm.info.vi51.worldswar.perception.AntPerception;

public class GathererStrategicBehaviour implements AntStrategicBehaviour {

	private final CollectFoodTacticalBehaviour collectFood;
	private final GoHomeTacticalBehaviour goHome;

	public GathererStrategicBehaviour(CollectFoodTacticalBehaviour collectFoodTacticalBehaviour,
			GoHomeTacticalBehaviour goHomeTacticalBehaviour) {
		this.collectFood = collectFoodTacticalBehaviour;
		this.goHome = goHomeTacticalBehaviour;
	}

	@Override
	public Influence computeInfluence(AntPerception perception, HashMap<String, Object> memory) {
		// TODO - implement the algo here to choose between tactical behaviours

		return this.goHome.computeInfluence(perception, memory);
	}

}
