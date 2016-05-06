package fr.utbm.info.vi51.worldswar.simulator;

import fr.utbm.info.vi51.worldswar.environment.PerceptionGrid;

/**
 * Listener interface about a {@link Simulator}
 *
 */
public interface SimulatorListener {
	/**
	 * Invoked when a simulation step has been fired
	 */
	public void simulationStepFired();

	/**
	 * Invoked when the simulation is terminated
	 */
	public void simulationTerminated();

	/**
	 * Invoked when the simulation start
	 */
	public void simulationStarted();

	/**
	 * Invoked by the environment when it is updated
	 * 
	 * @param perceptionGrid
	 *            the complete {@link perceptionGrid} computed each step
	 */
	public void environmentUpdated(PerceptionGrid perceptionGrid);
}
