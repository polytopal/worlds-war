package fr.utbm.info.vi51.worldswar.environment;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.KeyStroke;

/**
 * Enumerates the different types of pheromones that ants can place
 * 
 */
public enum PheromoneType {

	/**
	 * Used to give the direction to the colony's home
	 */
	HOME("PheromoneType.home", KeyStroke.getKeyStroke(KeyEvent.VK_1, InputEvent.CTRL_MASK)), //$NON-NLS-1$

	/**
	 * Give a direction to some food
	 */
	FOOD("PheromoneType.food", KeyStroke.getKeyStroke(KeyEvent.VK_2, InputEvent.CTRL_MASK)), //$NON-NLS-1$

	/**
	 * Alerts about potential dangers
	 */
	DANGER("PheromoneType.danger", KeyStroke.getKeyStroke(KeyEvent.VK_3, InputEvent.CTRL_MASK)); //$NON-NLS-1$

	private String propertyKey;
	private KeyStroke keyStroke;

	private PheromoneType(String key, KeyStroke keyStroke) {
		this.propertyKey = key;
		this.keyStroke = keyStroke;
	}

	private PheromoneType(String key) {
		this.propertyKey = key;
		this.keyStroke = null;
	}

	/**
	 * @return the key representing the value in the properties
	 */
	public String getPropertyKey() {
		return this.propertyKey;
	}

	public KeyStroke getKeyStroke() {
		return this.keyStroke;
	}

}
