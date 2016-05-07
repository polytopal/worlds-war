package fr.utbm.info.vi51.worldswar.simulator;

/**
 * Defines the available speed settings for the simulation
 *
 */
public enum SimulationSpeed {

	EXTRA_SLOW("SimulationSpeed.extraSlow", 10000), SLOW("SimulationSpeed.slow", 500), NORMAL("SimulationSpeed.normal", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			250), FAST("SimulationSpeed.fast", 100), //$NON-NLS-1$
	/**
	 * No delay between steps means the simulation will run as fast as possible
	 */
	MAX("SimulationSpeed.max", 1); //$NON-NLS-1$

	private SimulationSpeed(String key, int ms) {
		this.propertyKey = key;
		this.milliseconds = ms;
	}

	private String propertyKey;
	private int milliseconds;

	/**
	 * @return the key representing the value in the properties
	 */
	public String getPropertyKey() {
		return this.propertyKey;
	}

	/**
	 * @return the initial speed used when the simulator is initialized.
	 * 
	 *         When a new simulation is started, the speed of the previous
	 *         simulation is kept for the new one
	 */
	public static SimulationSpeed getInitialSpeed() {
		return NORMAL;
	}

	/**
	 * @return the minimum delay in ms between each simulation step
	 */
	public int getMilliseconds() {
		return this.milliseconds;
	}
}
