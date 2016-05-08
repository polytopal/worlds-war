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
	 * 
	 * @param simulationPaused : the boolean simulationPaused from the Simulator
	 * Invoked when the simulation is paused/unpaused
	 */
	public void simulationPaused(boolean simulationPaused);
}
