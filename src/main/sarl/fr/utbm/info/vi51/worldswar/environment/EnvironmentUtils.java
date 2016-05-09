package fr.utbm.info.vi51.worldswar.environment;

import java.awt.Point;
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

		final int octaveCount = 5;
		final float max = 20;
		final float min = -40;
		final Grid<Float> randomFoodGrid = PerlinNoiseGenerator.generatePerlinNoiseHeightGrid(width, height,
				octaveCount, min, max);

		final Grid<EnvCell> grid = new Grid<>(0, width - 1, 0, height - 1);
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				final EnvCell envCell = new EnvCell();
				grid.set(x, y, envCell);
				final Point point = new Point(x, y);

				// TODO add envObjects here

				// --- used to test GUI ---

				final float perlinHeight = randomFoodGrid.get(point);
				if (perlinHeight > 0.0f) {
					envCell.addEnvObject(new Food(point, (int) perlinHeight));
				}

				// if (x == 10 && y == 10) {
				// envCell.addEnvObject(new AntHill(point,
				// coloniesList.get(0)));
				// }
				// if (Math.random() > 0.9) {
				// envCell.addEnvObject(new Food(point, (int) (Math.random() *
				// 10) + 1));
				// }
				// if (Math.random() > 0.95) {
				// Collections.shuffle(coloniesList);
				// final List<Caste> castes = Arrays.asList(Caste.values());
				// Collections.shuffle(castes);
				// envCell.addEnvObject(new AntBody(point, new UUID(0, 0),
				// coloniesList.get(0), castes.get(0)));
				// }
				// if (Math.random() > 0.3) {
				// Collections.shuffle(coloniesList);
				// final List<PheromoneType> pheromonesTypes =
				// Arrays.asList(PheromoneType.values());
				// Collections.shuffle(pheromonesTypes);
				// envCell.addEnvObject(new Pheromone(point,
				// coloniesList.get(0), pheromonesTypes.get(0),
				// (float) Math.random() * 3));
				// }

				// ------------------------
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
