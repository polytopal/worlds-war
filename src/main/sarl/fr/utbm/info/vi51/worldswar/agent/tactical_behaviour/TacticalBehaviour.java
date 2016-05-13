package fr.utbm.info.vi51.worldswar.agent.tactical_behaviour;

import java.util.HashMap;

import fr.utbm.info.vi51.worldswar.environment.PerceptionGrid;
import fr.utbm.info.vi51.worldswar.environment.influence.Influence;

public interface TacticalBehaviour {
	Influence computeInfluence(PerceptionGrid perceptionGrid, HashMap<String, Object> memory);
}
