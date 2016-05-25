package fr.utbm.info.vi51.worldswar.simulator;

import java.awt.event.KeyEvent;

import javax.swing.KeyStroke;

/**
 * Defines the available speed settings for the simulation
 *
 */
public enum SimulationSpeed {

	SLOW("SimulationSpeed.slow", 500, KeyStroke.getKeyStroke(KeyEvent.VK_1, 0)), //$NON-NLS-1$

	NORMAL("SimulationSpeed.normal", 250, KeyStroke.getKeyStroke(KeyEvent.VK_2, 0)), //$NON-NLS-1$

	FAST("SimulationSpeed.fast", 100, KeyStroke.getKeyStroke(KeyEvent.VK_3, 0)), //$NON-NLS-1$
	/**
	 * No delay between steps means the simulation will run as fast as possible
	 */
	MAX("SimulationSpeed.max", 1, KeyStroke.getKeyStroke(KeyEvent.VK_4, 0)); //$NON-NLS-1$

	private SimulationSpeed(String key, int ms) {
		this.propertyKey = key;
		this.milliseconds = ms;
		this.keyStroke = null;
	}

	private SimulationSpeed(String key, int ms, KeyStroke ks) {
		this.propertyKey = key;
		this.milliseconds = ms;
		this.keyStroke = ks;
	}

	private String propertyKey;
	private int milliseconds;
	private KeyStroke keyStroke;

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

	/**
	 * @return the key combination used by the user to set this speed
	 */
	public KeyStroke getKeyStroke() {
		return this.keyStroke;
	}
}
