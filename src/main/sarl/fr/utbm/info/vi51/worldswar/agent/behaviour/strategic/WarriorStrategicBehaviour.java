package fr.utbm.info.vi51.worldswar.agent.behaviour.strategic;

import java.util.HashMap;

import fr.utbm.info.vi51.worldswar.environment.Caste;
import fr.utbm.info.vi51.worldswar.environment.influence.Influence;
import fr.utbm.info.vi51.worldswar.perception.AntPerception;

/**
 * Define the behaviour of {@link Caste#WARRIOR} ants. They will patrol around
 * the hill until they find danger pheromones to follow.
 */
public class WarriorStrategicBehaviour implements AntStrategicBehaviour {

	@Override
	public Influence computeInfluence(AntPerception perception, HashMap<String, Object> memory) {
		// TODO Auto-generated method stub
		return null;
	}

}
