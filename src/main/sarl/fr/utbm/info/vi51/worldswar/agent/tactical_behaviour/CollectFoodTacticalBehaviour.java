package fr.utbm.info.vi51.worldswar.agent.tactical_behaviour;

import java.util.HashMap;

import fr.utbm.info.vi51.worldswar.agent.operational_behaviour.MoveToTargetOperationalBehaviour;
import fr.utbm.info.vi51.worldswar.environment.PerceptionGrid;
import fr.utbm.info.vi51.worldswar.environment.influence.Influence;

public class CollectFoodTacticalBehaviour implements TacticalBehaviour {

	MoveToTargetOperationalBehaviour moveToTarget;

	public CollectFoodTacticalBehaviour(MoveToTargetOperationalBehaviour moveToTargetOperationalBehaviour) {
		this.moveToTarget = moveToTargetOperationalBehaviour;
	}

	@Override
	public Influence computeInfluence(PerceptionGrid perceptionGrid, HashMap<String, Object> memory) {
		// TODO Auto-generated method stub
		return null;
	}

}
