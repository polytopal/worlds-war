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

	public static final int DEFAULT_WIDTH = 100;
	public static final int MIN_WIDTH = 30;
	public static final int MAX_WIDTH = 1000;

	public static final int DEFAULT_HEIGHT = 100;
	public static final int MIN_HEIGHT = 30;
	public static final int MAX_HEIGHT = 1000;

	public static final Breed DEFAULT_BREED_ON_ADD = Breed.DARK_ANTS;
	public static final Breed[] DEFAULT_BREED_LIST = { Breed.DARK_ANTS, Breed.RED_ANTS };
	public static final int MAX_COLONY_NUMBER = 5;
	public static final int MIN_COLONY_NUMBER = 1;

	// in percent
	public static final int DEFAULT_FOOD_PROPORTION = 30;
	public static final int MIN_FOOD_PROPORTION = 10;
	public static final int MAX_FOOD_PROPORTION = 70;

	// -----

	private final int gridWidth;
	private final int gridHeight;
	private final List<Colony> coloniesList;
	// the food proportion, between 0.0f and 1.0f
	private final float foodProportion;

	/**
	 * 
	 * @param gridWidth
	 * @param gridHeight
	 * @param coloniesList
	 * @param foodProportion
	 *            the food proportion, between 0.0f and 1.0f
	 */
	public SimulationParameters(int gridWidth, int gridHeight, List<Colony> coloniesList, float foodProportion) {
		this.gridWidth = gridWidth;
		this.gridHeight = gridHeight;
		this.coloniesList = coloniesList;
		this.foodProportion = foodProportion;
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

	public float getFoodProportion() {
		return foodProportion;
	}

}
