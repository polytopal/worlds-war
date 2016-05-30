package fr.utbm.info.vi51.worldswar.environment;

public class MapInformation {
	
	private final int noiseSeed;
	
	public MapInformation(int noiseSeed){
		this.noiseSeed = noiseSeed;
	}
	
	public int getNoiseSeed(){
		return this.noiseSeed;
	}

}
