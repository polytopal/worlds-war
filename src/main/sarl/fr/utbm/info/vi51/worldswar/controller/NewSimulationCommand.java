package fr.utbm.info.vi51.worldswar.controller;

/**
 * {@link ControllerCommand} requesting a new simulation
 * 
 */
public class NewSimulationCommand {
	private final SimulationParameters simulationParameters;

	public NewSimulationCommand(SimulationParameters simParameters) {
		this.simulationParameters = simParameters;
	}

	public SimulationParameters getSimulationParameters() {
		return this.simulationParameters;
	}
}
