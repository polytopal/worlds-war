package fr.utbm.info.vi51.worldswar.agent.operational_behaviour;

import java.awt.Point;
import java.util.HashMap;

import fr.utbm.info.vi51.worldswar.environment.influence.Influence;
import fr.utbm.info.vi51.worldswar.perception.PerceptionGrid;

public interface OperationalBehaviour {
	Influence computeInfluence(PerceptionGrid perceptionGrid, HashMap<String, Object> memory, Point Target);
}
