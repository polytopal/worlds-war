package fr.utbm.info.vi51.worldswar.simulator;

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
	 * Invoked when the simulation is paused
	 */
	public void simulationPaused();
	/**
	 * Invoked when the simulation is resumed
	 */
	public void simulationResumed();
}
