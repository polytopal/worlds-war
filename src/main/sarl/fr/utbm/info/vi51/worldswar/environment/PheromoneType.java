package fr.utbm.info.vi51.worldswar.environment;

/**
 * Enumerates the different types of pheromones that ants can place
 * 
 */
public enum PheromoneType {
	HOME("PheromoneType.home"), FOOD("PheromoneType.food"), DANGER("PheromoneType.danger"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

	private String propertyKey;

	private PheromoneType(String key) {
		this.propertyKey = key;
	}

	/**
	 * @return the key representing the value in the properties
	 */
	public String getPropertyKey() {
		return this.propertyKey;
	}
}
