package fr.utbm.info.vi51.worldswar.agent.tactical_behaviour;

import java.util.HashMap;

import fr.utbm.info.vi51.worldswar.agent.operational_behaviour.MoveToTargetOperationalBehaviour;
import fr.utbm.info.vi51.worldswar.environment.PerceptionGrid;
import fr.utbm.info.vi51.worldswar.environment.influence.Influence;

public class GoHomeTacticalBehaviour implements TacticalBehaviour {

	MoveToTargetOperationalBehaviour moveToTarget;

	public GoHomeTacticalBehaviour(MoveToTargetOperationalBehaviour moveToTargetOperationalBehaviour) {
		this.moveToTarget = moveToTargetOperationalBehaviour;
	}

	@Override
	public Influence computeInfluence(PerceptionGrid perceptionGrid, HashMap<String, Object> memory) {
		// TODO - implement function here

		return this.moveToTarget.computeInfluence(perceptionGrid, memory, null);
	}

}
