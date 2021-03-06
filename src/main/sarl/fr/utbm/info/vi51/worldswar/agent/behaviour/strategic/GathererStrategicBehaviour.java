package fr.utbm.info.vi51.worldswar.agent.behaviour.strategic;

import java.util.HashMap;

import fr.utbm.info.vi51.worldswar.agent.behaviour.tactical.AntTacticalBehaviour;
import fr.utbm.info.vi51.worldswar.environment.Caste;
import fr.utbm.info.vi51.worldswar.environment.influence.Influence;
import fr.utbm.info.vi51.worldswar.perception.AntPerception;

/**
 * Define the behaviour of {@link Caste#GATHERER} ants. They will try to follow
 * food pheromones to gather as much food as possible.
 *
 */
public class GathererStrategicBehaviour implements AntStrategicBehaviour {

	private final AntTacticalBehaviour tacticalBehaviour;

	/**
	 * @param tacticalBehaviour
	 */
	public GathererStrategicBehaviour(AntTacticalBehaviour tacticalBehaviour) {
		this.tacticalBehaviour = tacticalBehaviour;
	}

	@Override
	public Influence computeInfluence(AntPerception perception, HashMap<String, Object> memory) {
		// Survivability prevails : first the ant checks if there is an enemy in
		// sight
		if(perception.isEnemyInSight()){
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
		return this.tacticalBehaviour.collectFood(perception, memory);
	}

}
