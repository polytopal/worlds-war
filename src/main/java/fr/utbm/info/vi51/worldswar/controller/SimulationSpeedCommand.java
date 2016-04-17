package fr.utbm.info.vi51.worldswar.controller;

import fr.utbm.info.vi51.worldswar.simulator.SimulationSpeed;

/**
 * {@link ControllerCommand} requesting a simulation speed
 * 
 */
public class SimulationSpeedCommand {

	private SimulationSpeed speed;

	public SimulationSpeedCommand(SimulationSpeed speed) {
		this.speed = speed;
	}

	public SimulationSpeed getSpeed() {
		return this.speed;
	}

}
