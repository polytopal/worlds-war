package fr.utbm.info.vi51.worldswar.environment;

/**
 * MapInformation is a class that is meant to contain various information
 * related to the simulation map. The user can access this information thanks
 * to the MapInfoDialog.
 */
public class MapInformation {
	
	private final int noiseSeed;
	
	/**
	 * Constructor
	 * @param noiseSeed
	 */
	public MapInformation(int noiseSeed){
		this.noiseSeed = noiseSeed;
	}
	
	/**
	 * @return the noise seed for the simulator map
	 */
	public int getNoiseSeed(){
		return this.noiseSeed;
	}

}
