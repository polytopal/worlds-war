package fr.utbm.info.vi51.worldswar.agent;

import fr.utbm.info.vi51.worldswar.agent.operational_behaviour.MoveToTargetOperationalBehaviour;
import fr.utbm.info.vi51.worldswar.agent.strategic_behaviour.GathererStrategicBehaviour;
import fr.utbm.info.vi51.worldswar.agent.strategic_behaviour.AntStrategicBehaviour;
import fr.utbm.info.vi51.worldswar.agent.tactical_behaviour.CollectFoodTacticalBehaviour;
import fr.utbm.info.vi51.worldswar.agent.tactical_behaviour.GoHomeTacticalBehaviour;
import fr.utbm.info.vi51.worldswar.environment.Caste;

/**
 * create all the behaviours
 */
public class BehaviourHandler {

	private final GathererStrategicBehaviour gathererStrategicBehaviour;

	public BehaviourHandler() {

		// operational layer
		final MoveToTargetOperationalBehaviour moveToTargetOperationalBehaviour = new MoveToTargetOperationalBehaviour();

		// tactical layer
		final CollectFoodTacticalBehaviour collectFoodTacticalBehaviour = new CollectFoodTacticalBehaviour(
				moveToTargetOperationalBehaviour);
		final GoHomeTacticalBehaviour goHomeTacticalBehaviour = new GoHomeTacticalBehaviour(
				moveToTargetOperationalBehaviour);

		// strategic layer
		this.gathererStrategicBehaviour = new GathererStrategicBehaviour(collectFoodTacticalBehaviour,
				goHomeTacticalBehaviour);

	}

	/**
	 * 
	 * @param caste
	 * @return the {@link AntStrategicBehaviour} related to the given caste
	 */
	public AntStrategicBehaviour getStrategicBehaviour(Caste caste) {
		switch (caste) {
		case GATHERER:
			return this.gathererStrategicBehaviour;
		// TODO - return a behaviour for the other castes
		default:
			return null;
		}
	}
}
