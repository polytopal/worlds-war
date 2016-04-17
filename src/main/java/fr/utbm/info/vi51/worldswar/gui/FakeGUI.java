package fr.utbm.info.vi51.worldswar.gui;

import fr.utbm.info.vi51.worldswar.controller.Controller;
import fr.utbm.info.vi51.worldswar.environment.EnvironmentListener;
import fr.utbm.info.vi51.worldswar.simulator.SimulationSpeed;
import fr.utbm.info.vi51.worldswar.simulator.SimulatorListener;

/**
 * Temporary class to replace with the actual GUI when we have one
 * 
 *
 */
public class FakeGUI implements EnvironmentListener, SimulatorListener {

	private Controller controller;

	private float stepsPerSecond;
	private long lastStepStart;

	public FakeGUI(Controller controller) {
		this.controller = controller;
		this.controller.setSimulationSpeed(SimulationSpeed.MAX);
		this.stepsPerSecond = 0;
		this.lastStepStart = System.currentTimeMillis();
	}

	@Override
	public void simulationStepFired() {
		long currentTime = System.currentTimeMillis();
		long stepDuration = currentTime - this.lastStepStart;
		this.lastStepStart = currentTime;
		this.stepsPerSecond = 1000.f / stepDuration;
		System.out.println("Steps per second : " + this.stepsPerSecond);
	}

}
