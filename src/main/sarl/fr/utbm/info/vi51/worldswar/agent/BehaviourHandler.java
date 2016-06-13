package fr.utbm.info.vi51.worldswar.agent;

import fr.utbm.info.vi51.worldswar.agent.behaviour.operational.AntOperationalBehaviour;
import fr.utbm.info.vi51.worldswar.agent.behaviour.strategic.AntStrategicBehaviour;
import fr.utbm.info.vi51.worldswar.agent.behaviour.strategic.ExplorerStrategicBehaviour;
import fr.utbm.info.vi51.worldswar.agent.behaviour.strategic.GathererStrategicBehaviour;
import fr.utbm.info.vi51.worldswar.agent.behaviour.tactical.AntTacticalBehaviour;
import fr.utbm.info.vi51.worldswar.environment.Caste;

/**
 * create all the behaviours
 */
public class BehaviourHandler {

	private final GathererStrategicBehaviour gathererStrategicBehaviour;
	private final ExplorerStrategicBehaviour explorerStrategicBehaviour;

	/**
	 * Instantiates all behaviour objects, resolving dependencies between them
	 */
	public BehaviourHandler() {

		// Operational layer
		final AntOperationalBehaviour antOperationalBehaviour = new AntOperationalBehaviour();

		// Tactical layer
		final AntTacticalBehaviour antTacticalBehaviour = new AntTacticalBehaviour(antOperationalBehaviour);

		// Strategic layer
		this.gathererStrategicBehaviour = new GathererStrategicBehaviour(antTacticalBehaviour);
		this.explorerStrategicBehaviour = new ExplorerStrategicBehaviour(antTacticalBehaviour);
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
		case EXPLORER:
			return this.explorerStrategicBehaviour;
		default:
			throw new IllegalArgumentException("No behaviour set for caste " + caste); //$NON-NLS-1$
		}
	}
}
