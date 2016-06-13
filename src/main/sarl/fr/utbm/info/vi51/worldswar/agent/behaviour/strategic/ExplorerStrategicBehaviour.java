package fr.utbm.info.vi51.worldswar.agent.behaviour.strategic;

import java.util.HashMap;

import fr.utbm.info.vi51.worldswar.agent.behaviour.tactical.AntTacticalBehaviour;
import fr.utbm.info.vi51.worldswar.environment.Caste;
import fr.utbm.info.vi51.worldswar.environment.influence.Influence;
import fr.utbm.info.vi51.worldswar.perception.AntPerception;

/**
 * Defines the behaviour of {@link Caste#EXPLORER} ants. They will wander
 * without following food pheromones to find food in new, unexplored areas.
 *
 */
public class ExplorerStrategicBehaviour implements AntStrategicBehaviour {

	private final AntTacticalBehaviour tacticalBehaviour;

	/**
	 * @param tacticalBehaviour
	 */
	public ExplorerStrategicBehaviour(AntTacticalBehaviour tacticalBehaviour) {
		this.tacticalBehaviour = tacticalBehaviour;
	}

	@Override
	public Influence computeInfluence(AntPerception perception, HashMap<String, Object> memory) {
		// Survivability prevails :the ant checks if there is an enemy in sight
		// before doing anything else
		if (perception.isEnemyInSight()) {
			// default choice if an enemy is seen is to flee
			memory.put(ENCOUNTERED_DANGER, new Boolean(true));
			// voir commentaire flee pour expliquer le startDangerTrail ici
			this.tacticalBehaviour.startDangerTrail(perception, memory);
			return this.tacticalBehaviour.flee(perception, memory);
		}
		if (memory.containsKey(ENCOUNTERED_DANGER)) {
			if (perception.isAtHome()) {
				memory.remove(ENCOUNTERED_DANGER);
			}
			return this.tacticalBehaviour.flee(perception, memory);
		}
		if (perception.getMyBody().getFoodCarried() > 0) {
			return this.tacticalBehaviour.bringFoodHome(perception, memory);
		}
		return this.tacticalBehaviour.wanderForFood(perception, memory);
	}

}
