package fr.utbm.info.vi51.worldswar.environment;

import java.awt.Point;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import fr.utbm.info.vi51.worldswar.controller.SimulationParameters;
import fr.utbm.info.vi51.worldswar.environment.envobject.AntBody;
import fr.utbm.info.vi51.worldswar.environment.envobject.AntHill;
import fr.utbm.info.vi51.worldswar.environment.envobject.EnvironmentObject;
import fr.utbm.info.vi51.worldswar.environment.envobject.Food;
import fr.utbm.info.vi51.worldswar.environment.envobject.Pheromone;
import fr.utbm.info.vi51.worldswar.utils.Direction;
import fr.utbm.info.vi51.worldswar.utils.Grid;

/**
 * Contains static utils methods to help on environment stuff, e.g. pheromones
 * dissipation, objects displacement, etc
 *
 */
public class EnvironmentUtils {
	/**
	 * Private empty constructor : this class is not meant to be instantiated
	 */
	private EnvironmentUtils() {
	}

	public static Grid<EnvCell> generateMap(SimulationParameters simulationParameters) {

		final int width = simulationParameters.getGridWidth();
		final int height = simulationParameters.getGridHeight();
		final List<Colony> coloniesList = simulationParameters.getColoniesList();

		// ant hills positions
		final int nbColony = coloniesList.size();
		// the initial angle, the first colony in near the left border
		float angle = (float) Math.PI;
		final List<AntHill> antHillList = new ArrayList<>();
		for (final Colony colony : coloniesList) {
			// the colonies are disposed on an oval shape
			// center = (width/2, height/2)
			// x radius = width/3, y radius = height/3
			final int x = (int) (width / 2 + (width / 3) * Math.cos(angle));
			final int y = (int) (height / 2 + (height / 3) * Math.sin(angle));
			antHillList.add(new AntHill(new Point(x, y), colony));
			angle += 2 * Math.PI / nbColony;
		}

		// allow to choose how the food is grouped in parcel
		final int octaveCount = 6;
		final float foodProportion = 0.3f; // must be between 0 and 1
		final float max = 50; // !!! warning !!! represent the theoretically
								// maximum food on a cell, but the real maximum
								// food will be lower cause of the
								// interpolations of the Perlin noise

		// simple cross product
		final float min = max * (1f - (1f / foodProportion));
		// the value of each cell will be between min and max
		final Grid<Float> randomFoodGrid = PerlinNoiseGenerator.generatePerlinNoiseHeightGrid(width, height,
				octaveCount, min, max);

		final Grid<EnvCell> grid = new Grid<>(0, width - 1, 0, height - 1);
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				final EnvCell envCell = new EnvCell();
				grid.set(x, y, envCell);
				final Point position = new Point(x, y);

				boolean antHillCell = false;
				for (final AntHill antHill : antHillList) {
					if (antHill.getPosition().equals(position)) {
						envCell.addEnvObject(antHill);
						antHillCell = true;
					}
				}
				// if there is an ant hill, there is not other objects on the
				// cell
				if (!antHillCell) {
					final float perlinHeight = randomFoodGrid.get(position).floatValue();
					if (perlinHeight > 0.0f) {
						envCell.addEnvObject(new Food(position, (int) perlinHeight));
					}
				}
			}
		}
		return grid;
	}

	/**
	 * Applies one step of {@link Pheromone} dissipation. If the pheromone
	 * quantity reaches zero, the corresponding {@link EnvironmentObject} is
	 * removed from the map.
	 * 
	 * @param grid
	 *            the environment grid
	 * 
	 * 
	 * @see Pheromone#dissipate()
	 */
	public static void applyPheromoneDissipation(Grid<EnvCell> grid) {

		for (final EnvCell c : grid) {
			final List<EnvironmentObject> toRemove = new LinkedList<>();
			for (final EnvironmentObject envObj : c.getEnvObjects()) {
				if (envObj instanceof Pheromone) {
					final Pheromone p = (Pheromone) envObj;
					p.dissipate();
					if (p.getQty() <= 0) {
						toRemove.add(p);
					}
				}
			}
			c.removeAllEnvObjects(toRemove);
		}
	}

	////////////////////////////////////////
	////////// INFLUENCE HANDLING //////////
	////////////////////////////////////////

	/**
	 * Burrows or unburrows the specified {@link AntBody} if possible according
	 * to the environment rules
	 * 
	 * @param ant
	 * @param grid
	 */
	public static void burrow(AntBody ant, Grid<EnvCell> grid) {
		if (ant.isBurrowed()) {
			ant.setBurrowed(false);
		} else if (getAntHillAt(ant.getPosition(), grid) != null) {
			ant.setBurrowed(true);
		}
	}

	/**
	 * Drops the given qty of food from the ant's inventory to the ground under
	 * its feet.
	 * 
	 * @param ant
	 * @param qty
	 * @param grid
	 */
	public static void dropFood(AntBody ant, int qty, Grid<EnvCell> grid) {
		final int foodDropped = ant.pickFood(qty);
		Food food = getFoodAt(ant.getPosition(), grid);
		if (food == null) {
			// If there was no food at this place, we have to create the
			// envobject
			food = new Food(ant.getPosition(), foodDropped);
			grid.get(ant.getPosition()).addEnvObject(food);
		} else {
			food.drop(foodDropped);
		}
	}

	/**
	 * Picks food from the ground to the ant's inventory.
	 * 
	 * @param ant
	 * @param requestedQty
	 * @param grid
	 */
	public static void pickFood(AntBody ant, int requestedQty, Grid<EnvCell> grid) {
		// Prevent the ant to pick more food than it can carry
		final int qty = Math.min(requestedQty, ant.getCapacity() - ant.getFoodCarried());
		if (qty <= 0) {
			return;
		}

		final Food food = getFoodAt(ant.getPosition(), grid);
		if (food == null) {
			// Nothing to do if there is no food to pick
			return;
		}

		final int foodPicked = food.pick(qty);
		ant.giveFood(foodPicked);

		// Remove food object from the map if it is empty
		if (food.isEmpty()) {
			grid.get(ant.getPosition()).removeEnvObject(food);
		}
	}

	/**
	 * Moves the ant it the specified direction if it is possible according to
	 * the environment rules.
	 * 
	 * @param body
	 * @param direction
	 * @param grid
	 */
	public static void moveAnt(AntBody body, Direction direction, Grid<EnvCell> grid) {
		final Point target = new Point(body.getPosition().x + direction.getX(),
				body.getPosition().y + direction.getY());

		// Prevents moving out of the map
		if (!grid.containsPosition(target)) {
			return;
		}
		// Prevents going into non-traversable cells
		if (!grid.get(target).isTraversable()) {
			return;
		}
		// Move the body
		if (!grid.get(body.getPosition()).removeEnvObject(body)) {
			System.err.println("Body #" + body.getUuid() + " not found in the grid when trying to move it");
		}
		body.setPosition(target);
		grid.get(target).addEnvObject(body);
	}

	/**
	 * Places pheromones on the ground
	 * 
	 * @param colony
	 * @param type
	 * @param qty
	 * @param position
	 * @param grid
	 */
	public static void placePheromone(Colony colony, PheromoneType type, float qty, Point position,
			Grid<EnvCell> grid) {
		Pheromone pheromone = getPheromoneAt(position, colony, type, grid);
		if (pheromone == null) {
			pheromone = new Pheromone(position, colony, type, qty);
			grid.get(position).addEnvObject(pheromone);
		} else {
			pheromone.addQty(qty);
		}
	}

	////////////////////////////////////////
	/////////// BASIC UTILITIES ////////////
	////////////////////////////////////////
	/**
	 * @param position
	 * @param grid
	 * @return the {@link AntHill} at the given position, or {@code null} if
	 *         there is none.
	 */
	public static AntHill getAntHillAt(Point position, Grid<EnvCell> grid) {
		AntHill antHill = null;
		for (final EnvironmentObject object : grid.get(position).getEnvObjects()) {
			if (object instanceof AntHill) {
				antHill = (AntHill) object;
			}
		}
		return antHill;
	}

	/**
	 * @param position
	 * @param grid
	 * @return the {@link Food} at the given position, or {@code null} if there
	 *         is none.
	 */
	public static Food getFoodAt(Point position, Grid<EnvCell> grid) {
		Food food = null;
		for (final EnvironmentObject object : grid.get(position).getEnvObjects()) {
			if (object instanceof Food) {
				food = (Food) object;
			}
		}
		return food;
	}

	/**
	 * 
	 * @param position
	 * @param colony
	 * @param type
	 * @return the {@link Pheromone} at the given position, of the given
	 *         {@link PheromoneType} and belonging to the given {@link Colony},
	 *         or {@code null} if there is none.
	 */
	private static Pheromone getPheromoneAt(Point position, Colony colony, PheromoneType type, Grid<EnvCell> grid) {
		Pheromone validPheromone = null;
		for (final EnvironmentObject object : grid.get(position).getEnvObjects()) {
			if (object instanceof Pheromone) {
				final Pheromone pheromone = (Pheromone) object;
				if (pheromone.getColony() == colony && pheromone.getType() == type) {
					validPheromone = pheromone;
				}
			}
		}
		return validPheromone;
	}
}
