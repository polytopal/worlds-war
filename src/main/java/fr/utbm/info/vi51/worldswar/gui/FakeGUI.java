package fr.utbm.info.vi51.worldswar.gui;

import static fr.utbm.info.vi51.worldswar.simulator.SimulationSpeed.SLOW;

import fr.utbm.info.vi51.worldswar.controller.Controller;
import fr.utbm.info.vi51.worldswar.environment.EnvironmentListener;

/**
 * Temporary class to replace with the actual GUI when we have one
 * 
 *
 */
public class FakeGUI implements EnvironmentListener {

	private Controller controller;

	public FakeGUI(Controller controller) {
		this.controller = controller;
		this.controller.setSimulationSpeed(SLOW);
	}

}
