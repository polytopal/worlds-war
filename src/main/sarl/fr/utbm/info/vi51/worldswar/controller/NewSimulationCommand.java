package fr.utbm.info.vi51.worldswar.controller;

/**
 * {@link ControllerCommand} requesting a new simulation
 * 
 */
public class NewSimulationCommand {
	private final SimulationParameters simParameters;

	public NewSimulationCommand(SimulationParameters simParameters) {
		this.simParameters = simParameters;
	}

	public SimulationParameters getSimulationParameters() {
		return this.simParameters;
	}
}
