package fr.utbm.info.vi51.worldswar.environment;

import java.util.List;

/**
 * MapInformation is a class that is meant to contain various information
 * related to the simulation map. The user can access this information thanks
 * to the MapInfoDialog.
 */
public class MapInformation {
	
	private final int noiseSeed;
	private final List<Colony> colonyList;
	
	/**
	 * Constructor
	 * @param noiseSeed
	 * @param colonyList 
	 */
	public MapInformation(int noiseSeed, List<Colony> colonyList){
		this.noiseSeed = noiseSeed;
		this.colonyList = colonyList;
	}
	
	/**
	 * @return the noise seed for the simulator map
	 */
	public int getNoiseSeed(){
		return this.noiseSeed;
	}
	
	/**
	 * @return colonyMap a collection of the colonies in the simulation
	 */
	public List<Colony> getColonies() {
		return this.colonyList;
	}

}
