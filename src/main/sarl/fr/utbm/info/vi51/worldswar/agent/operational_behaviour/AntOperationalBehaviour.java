package fr.utbm.info.vi51.worldswar.agent.operational_behaviour;

import java.awt.Point;
import java.util.HashMap;

import fr.utbm.info.vi51.worldswar.environment.influence.Influence;
import fr.utbm.info.vi51.worldswar.perception.AntPerception;

public interface AntOperationalBehaviour {
	Influence computeInfluence(AntPerception perception, HashMap<String, Object> memory, Point Target);
}
