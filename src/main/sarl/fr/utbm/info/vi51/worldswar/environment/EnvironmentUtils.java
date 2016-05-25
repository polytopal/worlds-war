package fr.utbm.info.vi51.worldswar.environment;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import fr.utbm.info.vi51.worldswar.controller.SimulationParameters;
import fr.utbm.info.vi51.worldswar.environment.envobject.AntBody;
import fr.utbm.info.vi51.worldswar.environment.envobject.AntHill;
import fr.utbm.info.vi51.worldswar.environment.envobject.EnvironmentObject;
import fr.utbm.info.vi51.worldswar.environment.envobject.Food;
import fr.utbm.info.vi51.worldswar.environment.envobject.Pheromone;
import fr.utbm.info.vi51.worldswar.environment.envobject.Wall;
import fr.utbm.info.vi51.worldswar.utils.Direction;
import fr.utbm.info.vi51.worldswar.utils.Grid;

/**
 * Contains static utils methods to help on environment stuff, e.g. pheromones
 * dissipation, objects displacement, etc
 *
 */
public class EnvironmentUtils {

	private static final float ANT_HILL_RANGE = 20;

	/**
	 * Private empty constructor : this class is not meant to be instantiated
	 */
	private EnvironmentUtils() {
	}

	/**
	 * @param simulationParameters
	 * @return a map randomly generated, following the specified simulation
	 *         parameters
	 */
	public static Grid<EnvCell> generateMap(SimulationParameters simulationParameters) {

		final int width = simulationParameters.getGridWidth();
		final int height = simulationParameters.getGridHeight();
		final List<Colony> coloniesList = simulationParameters.getColoniesList();
		final float foodProportion = simulationParameters.getFoodProportion();
		final float rockProportion = simulationParameters.getRockProportion();

		// --- computation of ant hills positions ---

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

		// --- creation of the food ---

		// allow to choose how the food is grouped in parcel
		final int foodOctaveCount = 6;
		final float foodMaxValue = 80; // !!! warning !!! represent the
		// theoretically maximum food on a cell, but the real maximum food will
		// be lower cause of the interpolations of the Perlin noise

		// simple cross product
		final float foddMinValue = foodMaxValue * (1f - (1f / foodProportion));
		// the value of each cell will be between min and max
		final Grid<Float> randomFoodGrid = PerlinNoiseGenerator.generatePerlinNoiseHeightGrid(width, height,
				foodOctaveCount, foddMinValue, foodMaxValue);

		// --- creation of the rocks ---

		final int rockOctaveCount = 4;
		float rockMaxValue = ANT_HILL_RANGE;
		float rockMinValue = 0;
		try {
			rockMinValue = rockMaxValue * (1f - (1f / rockProportion));
		} catch (final ArithmeticException e) { // if rock proportion = 0
			rockMaxValue = -1;
			rockMinValue = -1;
		}
		final Grid<Float> randomRockGrid = PerlinNoiseGenerator.generatePerlinNoiseHeightGrid(width, height,
				rockOctaveCount, rockMinValue, rockMaxValue);

		// -----------------------------

		final Grid<EnvCell> grid = new Grid<>(0, width - 1, 0, height - 1);
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				final EnvCell envCell = new EnvCell();
				grid.set(x, y, envCell);
				final Point position = new Point(x, y);

				float nearestAntHillDistance = Float.MAX_VALUE;
				for (final AntHill antHill : antHillList) {
					final float AntHillDistance = (float) antHill.getPosition().distance(position);
					nearestAntHillDistance = Math.min(AntHillDistance, nearestAntHillDistance);
					if (nearestAntHillDistance == 0) {
						envCell.addEnvObject(antHill);
					}
				}

				// a negative value. antHillModifier = 0 if there is not near
				// hill. antHillModifier = -AntHillRange if on an ant hill
				final float antHillModifier = Math.min(0f, nearestAntHillDistance - ANT_HILL_RANGE);
				final float rockPerlinHeight = randomRockGrid.get(position).floatValue() + antHillModifier;
				if (rockPerlinHeight > 0) {
					envCell.addEnvObject(new Wall(position));
				} else if (nearestAntHillDistance > 0) {
					final int foodPerlinHeight = randomFoodGrid.get(position).intValue();
					if (foodPerlinHeight > 0) {
						envCell.addEnvObject(new Food(position, foodPerlinHeight));
					}
				}
			}
		}
		return grid;

	}

	/**
	 * @param grid
	 * @return a list of all the {@link AntHill}s in the given grid
	 */
	public static List<AntHill> getAntHills(Grid<EnvCell> grid) {
		final List<AntHill> list = new ArrayList<>();

		final Iterator<EnvCell> it = grid.iterator();
		while (it.hasNext()) {
			final EnvCell cell = it.next();
			final List<EnvironmentObject> envObjects = cell.getEnvObjects();
			for (final EnvironmentObject environmentObject : envObjects) {
				if (environmentObject instanceof AntHill) {
					list.add((AntHill) environmentObject);
				}
			}
		}

		return list;
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
	 * Drops the given qty of food from the ant's inventory to the ground or
	 * anthill under its feet
	 * 
	 * @param ant
	 * @param qty
	 * @param grid
	 */
	public static void dropFood(AntBody ant, int qty, Grid<EnvCell> grid) {
		// Ensure we do not drop more food than what the ant currently holds
		final int foodDropped = ant.pickFood(qty);

		// If there is an anthill, the food is dropped in it
		final AntHill antHill = getAntHillAt(ant.getPosition(), grid);
		if (antHill != null) {
			antHill.dropFood(foodDropped);
		} else {
			// Dispose food on the ground
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
	}

	/**
	 * Picks food from the ground or the anthill to the ant's inventory.
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

		final AntHill antHill = getAntHillAt(ant.getPosition(), grid);
		if (antHill != null) {
			// Picks the food from the ant hill
			ant.giveFood(antHill.pickFood(qty));
		} else {
			// Picks the food from the ground (if there is food to pick)
			final Food food = getFoodAt(ant.getPosition(), grid);
			if (food != null) {
				ant.giveFood(food.pick(qty));
				// Remove food object from the map if it is empty
				if (food.isEmpty()) {
					grid.get(ant.getPosition()).removeEnvObject(food);
				}
			}
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
			System.err.println("Body #" + body.getUuid() + " not found in the grid when trying to move it"); //$NON-NLS-1$ //$NON-NLS-2$
		}

		body.setPosition(target);

		// if the ant go out of the hill
		if (body.isBurrowed()) {
			body.setBurrowed(false);
		}
		// if the ant go in the hill
		else if (grid.get(target).isAntHillOf(body.getColony())) {
			body.setBurrowed(true);
		}

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
