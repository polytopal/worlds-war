package fr.utbm.info.vi51.worldswar.agent.tactical_behaviour;

import static fr.utbm.info.vi51.worldswar.perception.PerceptionGrid.MY_POSITION;

import java.util.HashMap;

import fr.utbm.info.vi51.worldswar.agent.operational_behaviour.AntOperationalBehaviour;
import fr.utbm.info.vi51.worldswar.environment.PheromoneType;
import fr.utbm.info.vi51.worldswar.environment.influence.DoNothingInfluence;
import fr.utbm.info.vi51.worldswar.environment.influence.Influence;
import fr.utbm.info.vi51.worldswar.perception.AntPerception;

public class AntTacticalBehaviour {

	private final AntOperationalBehaviour operationalBehaviour;

	public AntTacticalBehaviour(AntOperationalBehaviour operationalBehaviour) {
		this.operationalBehaviour = operationalBehaviour;
	}

	public Influence collectFood(AntPerception perception, HashMap<String, Object> memory) {
		if (perception.isFoodInSight()) {
			if (perception.getFoodAt(MY_POSITION) > 0) {
				memory.put("pheromoneType", PheromoneType.FOOD);
				memory.put("pheromoneDistance", 0);
				return this.operationalBehaviour.pickFood(perception);
			}
			return this.operationalBehaviour.moveToTarget(perception, memory, perception.getClosestAvailableFoodPos());
		}
		return this.operationalBehaviour.moveToTarget(perception, memory,
				perception.getHighestPheromonePos(PheromoneType.FOOD));
	}

	public Influence BringFoodHome(AntPerception perception, HashMap<String, Object> memory) {
		if (perception.isAtHome()) {
			memory.put("pheromoneType", PheromoneType.HOME);
			memory.put("pheromoneDistance", 0);
			return this.operationalBehaviour.dropFood(perception);
		}
		return this.goHome(perception, memory);
	}

	public Influence goHome(AntPerception perception, HashMap<String, Object> memory) {
		if (perception.isHomeInSight()) {
			if (perception.isAtHome()) {
				return new DoNothingInfluence();
			}
			return this.operationalBehaviour.moveToTarget(perception, memory, perception.getHomePos());
		}
		return this.operationalBehaviour.moveToTarget(perception, memory,
				perception.getHighestPheromonePos(PheromoneType.HOME));
	}
}
