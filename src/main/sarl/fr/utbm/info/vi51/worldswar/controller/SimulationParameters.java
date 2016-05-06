package fr.utbm.info.vi51.worldswar.controller;

import java.util.List;

import fr.utbm.info.vi51.worldswar.environment.Breed;
import fr.utbm.info.vi51.worldswar.environment.Colony;

/**
 * 
 * @author Leo
 *
 *         This class represents the parameters of a new simulation. It contains
 *         the grid width and height and the list of colonies
 * 
 *         This class contains also the constants about the grid min and max
 *         size, the default breed list ...
 */
public class SimulationParameters {

	public static final int DEFAULT_WIDTH = 500;
	public static final int MIN_WIDTH = 10;
	public static final int MAX_WIDTH = 5000;

	public static final int DEFAULT_HEIGHT = 500;
	public static final int MIN_HEIGHT = 10;
	public static final int MAX_HEIGHT = 5000;

	public static final Breed DEFAULT_BREED_ON_ADD = Breed.DARK_ANTS;
	public static final Breed[] DEFAULT_BREED_LIST = { Breed.DARK_ANTS, Breed.RED_ANTS };
	public static final int MAX_COLONY_NUMBER = 5;
	public static final int MIN_COLONY_NUMBER = 1;

	private final int gridWidth;
	private final int gridHeight;
	private final List<Colony> coloniesList;

	public SimulationParameters(int gridWidth, int gridHeight, List<Colony> coloniesList) {
		this.gridWidth = gridWidth;
		this.gridHeight = gridHeight;
		this.coloniesList = coloniesList;
	}

	public int getGridWidth() {
		return this.gridWidth;
	}

	public int getGridHeight() {
		return this.gridHeight;
	}

	public List<Colony> getColoniesList() {
		return this.coloniesList;
	}

}
