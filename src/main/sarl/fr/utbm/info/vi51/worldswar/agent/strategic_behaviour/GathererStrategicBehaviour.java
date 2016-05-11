package fr.utbm.info.vi51.worldswar.agent.strategic_behaviour;

import java.util.HashMap;

import fr.utbm.info.vi51.worldswar.agent.tactical_behaviour.CollectFoodTacticalBehaviour;
import fr.utbm.info.vi51.worldswar.agent.tactical_behaviour.GoHomeTacticalBehaviour;
import fr.utbm.info.vi51.worldswar.environment.PerceptionGrid;
import fr.utbm.info.vi51.worldswar.environment.influence.Influence;

public class GathererStrategicBehaviour implements StrategicBehaviour {

	private final CollectFoodTacticalBehaviour collectFood;
	private final GoHomeTacticalBehaviour goHome;

	public GathererStrategicBehaviour(CollectFoodTacticalBehaviour collectFoodTacticalBehaviour,
			GoHomeTacticalBehaviour goHomeTacticalBehaviour) {
		this.collectFood = collectFoodTacticalBehaviour;
		this.goHome = goHomeTacticalBehaviour;
		// TODO Auto-generated constructor stub
	}

	@Override
	public Influence computeInfluence(PerceptionGrid perceptionGrid, HashMap<String, Object> memory) {
		// TODO Auto-generated method stub
		return goHome.computeInfluence(perceptionGrid, memory);
	}

}
