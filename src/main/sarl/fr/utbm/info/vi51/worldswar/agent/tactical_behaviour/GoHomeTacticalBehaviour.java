package fr.utbm.info.vi51.worldswar.agent.tactical_behaviour;

import java.util.HashMap;

import fr.utbm.info.vi51.worldswar.agent.operational_behaviour.MoveToTargetOperationalBehaviour;
import fr.utbm.info.vi51.worldswar.environment.influence.Influence;
import fr.utbm.info.vi51.worldswar.perception.AntPerception;

public class GoHomeTacticalBehaviour implements AntTacticalBehaviour {

	MoveToTargetOperationalBehaviour moveToTarget;

	public GoHomeTacticalBehaviour(MoveToTargetOperationalBehaviour moveToTargetOperationalBehaviour) {
		this.moveToTarget = moveToTargetOperationalBehaviour;
	}

	@Override
	public Influence computeInfluence(AntPerception perception, HashMap<String, Object> memory) {
		// TODO - implement the algo here to find the best target

		return this.moveToTarget.computeInfluence(perception, memory, null);
	}

}
