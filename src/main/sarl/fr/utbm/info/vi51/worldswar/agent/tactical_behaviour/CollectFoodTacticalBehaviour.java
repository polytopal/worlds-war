package fr.utbm.info.vi51.worldswar.agent.tactical_behaviour;

import java.util.HashMap;

import fr.utbm.info.vi51.worldswar.agent.operational_behaviour.MoveToTargetOperationalBehaviour;
import fr.utbm.info.vi51.worldswar.environment.influence.Influence;
import fr.utbm.info.vi51.worldswar.perception.AntPerception;

public class CollectFoodTacticalBehaviour implements AntTacticalBehaviour {

	MoveToTargetOperationalBehaviour moveToTarget;

	public CollectFoodTacticalBehaviour(MoveToTargetOperationalBehaviour moveToTargetOperationalBehaviour) {
		this.moveToTarget = moveToTargetOperationalBehaviour;
	}

	@Override
	public Influence computeInfluence(AntPerception perception, HashMap<String, Object> memory) {
		// TODO Auto-generated method stub
		return null;
	}

}
