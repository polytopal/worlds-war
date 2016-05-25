package fr.utbm.info.vi51.worldswar.controller;

/**
 * {@link ControllerCommandEvent} requesting a new simulation
 * 
 */
public class NewSimulationCommand {
	private final SimulationParameters simulationParameters;

	/**
	 * Requests to start a new simulation with the given parameters
	 * 
	 * @param simParameters
	 *            the parameters of the new simulation
	 */
	public NewSimulationCommand(SimulationParameters simParameters) {
		this.simulationParameters = simParameters;
	}

	/**
	 * @return the simulation parameters
	 */
	public SimulationParameters getSimulationParameters() {
		return this.simulationParameters;
	}
}
