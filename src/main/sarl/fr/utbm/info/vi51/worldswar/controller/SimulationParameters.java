package fr.utbm.info.vi51.worldswar.controller;

import java.util.List;

import fr.utbm.info.vi51.worldswar.environment.Breed;
import fr.utbm.info.vi51.worldswar.environment.Colony;

/**
 * This class represents the parameters of a new simulation. It contains the
 * grid width and height and the list of colonies
 * 
 * This class contains also the constants about the grid min and max size, the
 * default breed list ...
 */
public class SimulationParameters {

	/** Default width of the map **/
	public static final int DEFAULT_WIDTH = 300;
	/** Minimum width of the map **/
	public static final int MIN_WIDTH = 30;
	/** Maximum width of the map **/
	public static final int MAX_WIDTH = 1000;

	/** Default height of the map **/
	public static final int DEFAULT_HEIGHT = 300;
	/** Minimum height of the map **/
	public static final int MIN_HEIGHT = 30;
	/** Maximum height of the map **/
	public static final int MAX_HEIGHT = 1000;

	/** Default breed proposed when adding a new colony **/
	public static final Breed DEFAULT_BREED_ON_ADD = Breed.DARK_ANTS;
	/** List of breeds used to set up the default colonies **/
	public static final Breed[] DEFAULT_BREED_LIST = { Breed.DARK_ANTS, Breed.RED_ANTS };
	/** Maximum number of different colonies in a simulation **/
	public static final int MAX_COLONY_NUMBER = 10;
	/** Minimum number of different colonies in a simulation **/
	public static final int MIN_COLONY_NUMBER = 1;

	/** Default proportion of food on the map (in percents) **/
	public static final int DEFAULT_FOOD_PROPORTION = 45;
	/** Minimum proportion of food on the map (in percents) must be > 0 **/
	public static final int MIN_FOOD_PROPORTION = 10;
	/** Maximum proportion of food on the map (in percents) must be <= 100 **/
	public static final int MAX_FOOD_PROPORTION = 70;

	/** Default proportion of rocks on the map (in percents) **/
	public static final int DEFAULT_ROCK_PROPORTION = 40;
	/** Minimum proportion of rocks on the map (in percents) must be >= 0 **/
	public static final int MIN_ROCK_PROPORTION = 0;
	/** Maximum proportion of rocks on the map (in percents) must be <= 100 **/
	public static final int MAX_ROCK_PROPORTION = 50;

	// -----

	private final int gridWidth;
	private final int gridHeight;
	private final List<Colony> coloniesList;
	// the food proportion, between 0.001f and 1.0f
	private final float foodProportion;
	// the rock proportion, between 0f and 1.0f
	private final float rockProportion;
	// the seed used for the map generation
	private final int noiseSeed;

	/**
	 * 
	 * @param gridWidth
	 * @param gridHeight
	 * @param coloniesList
	 * @param foodProportion
	 * @param rockProportion
	 */
	public SimulationParameters(int gridWidth, int gridHeight, List<Colony> coloniesList, float foodProportion,
			float rockProportion, int noiseSeed) {
		this.gridWidth = gridWidth;
		this.gridHeight = gridHeight;
		this.coloniesList = coloniesList;
		this.foodProportion = foodProportion;
		this.rockProportion = rockProportion;
		this.noiseSeed = noiseSeed;
	}

	/**
	 * @return the map width
	 */
	public int getGridWidth() {
		return this.gridWidth;
	}

	/**
	 * @return the map height
	 */
	public int getGridHeight() {
		return this.gridHeight;
	}

	/**
	 * @return the list of the ant colonies to place in the environment
	 */
	public List<Colony> getColoniesList() {
		return this.coloniesList;
	}

	/**
	 * @return the proportion of food to place on the map
	 */
	public float getFoodProportion() {
		return this.foodProportion;
	}

	/**
	 * @return the proportion of rocks to place on the map
	 */
	public float getRockProportion() {
		return this.rockProportion;
	}

	/**
	 * @return the seed used for the map generation
	 */
	public int getNoiseSeed() {
		return this.noiseSeed;
	}

}
