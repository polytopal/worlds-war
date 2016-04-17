package fr.utbm.info.vi51.worldswar.simulator;

/**
 * Defines the available speed settings for the simulation
 *
 */
public enum SimulationSpeed {

	SLOW("slow", 500), NORMAL("normal", 250), FAST("fast", 100),
	/**
	 * No delay between steps means the simulation will run as fast as possible
	 */
	MAX("max", 1);

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
	 * @return the minimum delay in ms between each simulation step
	 */
	public int getMilliseconds() {
		return this.milliseconds;
	}
}
