package fr.utbm.info.vi51.worldswar.agent;

import fr.utbm.info.vi51.worldswar.agent.operational_behaviour.MoveToTargetOperationalBehaviour;
import fr.utbm.info.vi51.worldswar.agent.strategic_behaviour.GathererStrategicBehaviour;
import fr.utbm.info.vi51.worldswar.agent.strategic_behaviour.StrategicBehaviour;
import fr.utbm.info.vi51.worldswar.agent.tactical_behaviour.CollectFoodTacticalBehaviour;
import fr.utbm.info.vi51.worldswar.agent.tactical_behaviour.GoHomeTacticalBehaviour;
import fr.utbm.info.vi51.worldswar.environment.Caste;

// create the behaviour
public class BehaviourHandler {

	private final GathererStrategicBehaviour gathererStrategicBehaviour;

	public BehaviourHandler() {

		// operational
		final MoveToTargetOperationalBehaviour moveToTargetOperationalBehaviour = new MoveToTargetOperationalBehaviour();

		// tactical
		final CollectFoodTacticalBehaviour collectFoodTacticalBehaviour = new CollectFoodTacticalBehaviour(
				moveToTargetOperationalBehaviour);
		final GoHomeTacticalBehaviour goHomeTacticalBehaviour = new GoHomeTacticalBehaviour(
				moveToTargetOperationalBehaviour);

		this.gathererStrategicBehaviour = new GathererStrategicBehaviour(collectFoodTacticalBehaviour,
				goHomeTacticalBehaviour);

	}

	public StrategicBehaviour getStrategicBehaviour(Caste caste) {
		switch (caste) {
		case GATHERER:
			return this.gathererStrategicBehaviour;
		default:
			return null;
		}
	}
}
