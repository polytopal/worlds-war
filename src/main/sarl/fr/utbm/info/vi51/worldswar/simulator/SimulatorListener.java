package fr.utbm.info.vi51.worldswar.simulator;

import fr.utbm.info.vi51.worldswar.environment.MapInformation;
import fr.utbm.info.vi51.worldswar.perception.PerceptionGrid;

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
	 * @param mapInfo information on the map (seed etc...)
	 */
	public void simulationStarted(MapInformation mapInfo);

	/**
	 * Invoked when the simulation is paused
	 */
	public void simulationPaused();

	/**
	 * Invoked when the simulation is resumed
	 */
	public void simulationResumed();

	/**
	 * Invoked by the environment when it is updated
	 * 
	 * @param perceptionGrid
	 *            the complete {@link perceptionGrid} computed each step
	 * @param agentCount
	 *            the number of agents currently running
	 */
	public void environmentUpdated(PerceptionGrid perceptionGrid, int agentCount);
}
