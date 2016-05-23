package fr.utbm.info.vi51.worldswar.agent;

import fr.utbm.info.vi51.worldswar.agent.behaviour.operational.AntOperationalBehaviour;
import fr.utbm.info.vi51.worldswar.agent.behaviour.strategic.AntStrategicBehaviour;
import fr.utbm.info.vi51.worldswar.agent.behaviour.strategic.GathererStrategicBehaviour;
import fr.utbm.info.vi51.worldswar.agent.behaviour.tactical.AntTacticalBehaviour;
import fr.utbm.info.vi51.worldswar.environment.Caste;

/**
 * create all the behaviours
 */
public class BehaviourHandler {

	private final GathererStrategicBehaviour gathererStrategicBehaviour;

	public BehaviourHandler() {

		// Operational layer
		final AntOperationalBehaviour antOperationalBehaviour = new AntOperationalBehaviour();

		// Tactical layer
		final AntTacticalBehaviour antTacticalBehaviour = new AntTacticalBehaviour(antOperationalBehaviour);

		// Strategic layer
		this.gathererStrategicBehaviour = new GathererStrategicBehaviour(antTacticalBehaviour);
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
