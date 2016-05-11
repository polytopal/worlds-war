package fr.utbm.info.vi51.worldswar.agent.strategic_behaviour;

import java.util.HashMap;

import fr.utbm.info.vi51.worldswar.environment.PerceptionGrid;
import fr.utbm.info.vi51.worldswar.environment.influence.Influence;

public interface StrategicBehaviour {

	Influence computeInfluence(PerceptionGrid perceptionGrid, HashMap<String, Object> memory);
}
