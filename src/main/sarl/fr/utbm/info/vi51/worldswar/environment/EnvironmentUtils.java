package fr.utbm.info.vi51.worldswar.environment;

import java.awt.Point;
import java.util.LinkedList;
import java.util.List;

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
	public static void applyPheromoneDissipation(Grid<Cell> grid) {

		for (Cell c : grid) {
			List<EnvironmentObject> toRemove = new LinkedList<>();
			for (EnvironmentObject envObj : c.getEnvObjects()) {
				if (envObj instanceof Pheromone) {
					Pheromone p = (Pheromone) envObj;
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
	public static void burrow(AntBody ant, Grid<Cell> grid) {
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
	public static void dropFood(AntBody ant, int qty, Grid<Cell> grid) {
		int foodDropped = ant.pickFood(qty);
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
	 * @param qty
	 * @param grid
	 */
	public static void pickFood(AntBody ant, int qty, Grid<Cell> grid) {
		Food food = getFoodAt(ant.getPosition(), grid);
		if (food == null) {
			// Nothing to do if there is no food to pick
			return;
		}

		int foodPicked = food.pick(qty);
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
	public static void moveAnt(AntBody body, Direction direction, Grid<Cell> grid) {
		Point target = new Point(body.getPosition().x + direction.getX(), body.getPosition().y + direction.getY());

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
	public static void placePheromone(Colony colony, PheromoneType type, float qty, Point position, Grid<Cell> grid) {
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
	public static AntHill getAntHillAt(Point position, Grid<Cell> grid) {
		AntHill antHill = null;
		for (EnvironmentObject object : grid.get(position).getEnvObjects()) {
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
	public static Food getFoodAt(Point position, Grid<Cell> grid) {
		Food food = null;
		for (EnvironmentObject object : grid.get(position).getEnvObjects()) {
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
	private static Pheromone getPheromoneAt(Point position, Colony colony, PheromoneType type, Grid<Cell> grid) {
		Pheromone validPheromone = null;
		for (EnvironmentObject object : grid.get(position).getEnvObjects()) {
			if (object instanceof Pheromone) {
				Pheromone pheromone = (Pheromone) object;
				if (pheromone.getColony() == colony && pheromone.getType() == type) {
					validPheromone = pheromone;
				}
			}
		}
		return validPheromone;
	}
}
