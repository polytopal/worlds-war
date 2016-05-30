package fr.utbm.info.vi51.worldswar.agent.behaviour.strategic;

import java.util.HashMap;

import fr.utbm.info.vi51.worldswar.agent.behaviour.tactical.AntTacticalBehaviour;
import fr.utbm.info.vi51.worldswar.environment.Caste;
import fr.utbm.info.vi51.worldswar.environment.influence.Influence;
import fr.utbm.info.vi51.worldswar.perception.AntPerception;

/**
 * Define the behaviour of {@link Caste#WARRIOR} ants. They will patrol around
 * the hill until they find danger pheromones to follow.
 */
public class WarriorStrategicBehaviour implements AntStrategicBehaviour {

	private final AntTacticalBehaviour tacticalBehaviour;

	/**
	 * @param tacticalBehaviour
	 */
	public WarriorStrategicBehaviour(AntTacticalBehaviour tacticalBehaviour) {
		this.tacticalBehaviour = tacticalBehaviour;
	}

	@Override
	public Influence computeInfluence(AntPerception perception, HashMap<String, Object> memory) {
		// FOR THE HORDE !! Warrior are there to fight, they first check if
		// there is some kind of danger nearby.
		if (perception.isDangerNearby()) {
			return this.tacticalBehaviour.chaseAndFight(perception, memory);
		}
		// If not, they patrol until they find some.
		return this.tacticalBehaviour.patrol(perception, memory);
	}

}
