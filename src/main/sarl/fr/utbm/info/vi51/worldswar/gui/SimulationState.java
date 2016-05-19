package fr.utbm.info.vi51.worldswar.gui;

public enum SimulationState {
	RUNNING("SimulationState.running"), //$NON-NLS-1$
	STOPPED("SimulationState.stopped"), //$NON-NLS-1$
	PAUSED("SimulationState.paused"); //$NON-NLS-1$

	private String propertyKey;
	
	private SimulationState(String key) {
		this.propertyKey = key;
	}
	
	/**
	 * @return the key representing the value in the properties
	 */
	public String getPropertyKey() {
		return this.propertyKey;
	}

}
