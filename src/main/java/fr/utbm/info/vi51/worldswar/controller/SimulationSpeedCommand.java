package fr.utbm.info.vi51.worldswar.controller;

import static fr.utbm.info.vi51.worldswar.controller.ControllerCommandType.SIMULATION_SPEED;

import fr.utbm.info.vi51.worldswar.simulator.SimulationSpeed;

/**
 * {@link ControllerCommand} requesting a simulation speed
 * 
 */
public class SimulationSpeedCommand extends ControllerCommand {

	private SimulationSpeed speed;

	public SimulationSpeedCommand(SimulationSpeed speed) {
		super(SIMULATION_SPEED);
		this.speed = speed;
	}

	public SimulationSpeed getSpeed() {
		return this.speed;
	}

}
