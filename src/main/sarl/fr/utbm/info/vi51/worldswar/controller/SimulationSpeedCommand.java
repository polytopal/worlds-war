package fr.utbm.info.vi51.worldswar.controller;

import fr.utbm.info.vi51.worldswar.simulator.SimulationSpeed;

/**
 * {@link ControllerCommandEvent} requesting a simulation speed
 * 
 */
public class SimulationSpeedCommand {

	private SimulationSpeed speed;

	/**
	 * @param speed
	 *            the new {@link SimulationSpeed} to set
	 */
	public SimulationSpeedCommand(SimulationSpeed speed) {
		this.speed = speed;
	}

	/**
	 * @return the new {@link SimulationSpeed} to set
	 */
	public SimulationSpeed getSpeed() {
		return this.speed;
	}

}
