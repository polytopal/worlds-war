package fr.utbm.info.vi51.worldswar.controller;

import java.util.List;

import fr.utbm.info.vi51.worldswar.environment.Breed;
import fr.utbm.info.vi51.worldswar.environment.Colony;

public class SimulationParameters {

	public static final int DEFAULT_WIDTH = 500;
	public static final int MIN_WIDTH = 30;
	public static final int MAX_WIDTH = 5000;

	public static final int DEFAULT_HEIGHT = 500;
	public static final int MIN_HEIGHT = 30;
	public static final int MAX_HEIGHT = 5000;

	public static final Breed DEFAULT_BREED = Breed.DARK_ANTS_OF_THE_NORTH;

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
