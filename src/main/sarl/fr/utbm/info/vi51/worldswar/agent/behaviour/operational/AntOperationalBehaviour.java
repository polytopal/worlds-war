package fr.utbm.info.vi51.worldswar.agent.behaviour.operational;

import static fr.utbm.info.vi51.worldswar.perception.PerceptionGrid.MY_POSITION;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import fr.utbm.info.vi51.worldswar.environment.PheromoneType;
import fr.utbm.info.vi51.worldswar.environment.influence.DoNothingInfluence;
import fr.utbm.info.vi51.worldswar.environment.influence.DropFoodInfluence;
import fr.utbm.info.vi51.worldswar.environment.influence.Influence;
import fr.utbm.info.vi51.worldswar.environment.influence.MoveInfluence;
import fr.utbm.info.vi51.worldswar.environment.influence.PheromoneAndMoveInfluence;
import fr.utbm.info.vi51.worldswar.environment.influence.PickFoodInfluence;
import fr.utbm.info.vi51.worldswar.perception.AntPerception;
import fr.utbm.info.vi51.worldswar.utils.Direction;

/**
 * Defines basic operational behaviours for ants.
 */
@SuppressWarnings("static-method")
public class AntOperationalBehaviour {

	private static final float MAX_PHEROMONE = 15;
	private static final float PHEROMONE_DECAY = 0.08f;

	private static final int MAX_PHEROMONE_DISTANCE = (int) (MAX_PHEROMONE / PHEROMONE_DECAY);

	/**
	 * Move in a direction to the target
	 * 
	 * @param perception
	 * @param memory
	 * @param target
	 * @return a {@link PheromoneAndMoveInfluence} or a {@link MoveInfluence}
	 *         with a direction to the target
	 */
	public Influence moveToTarget(AntPerception perception, HashMap<String, Object> memory, Point target) {
		ArrayList<Direction> viableDirections = new ArrayList<>(2);
		if (target.x < 0) {
			viableDirections.add(Direction.WEST);
		} else if (target.x > 0) {
			viableDirections.add(Direction.EAST);
		}
		if (target.y < 0) {
			viableDirections.add(Direction.NORTH);
		} else if (target.y > 0) {
			viableDirections.add(Direction.SOUTH);
		}

		if (viableDirections.isEmpty()) {
			// Target is (0,0), this means we already reached it
			return new DoNothingInfluence();
		}
		// Pick a random direction from the list
		return this.move(viableDirections.get(new Random().nextInt(viableDirections.size())), memory);
	}

	/**
	 * Picks as much food as possible from the ground
	 * 
	 * @param perception
	 * @return a {@link PickFoodInfluence}
	 */
	public Influence pickFood(AntPerception perception) {
		return new PickFoodInfluence(perception.getFoodAt(MY_POSITION));
	}

	/**
	 * Drops as much food as possible to the ground
	 * 
	 * @param perception
	 * @return a {@link DropFoodInfluence}
	 */
	public Influence dropFood(AntPerception perception) {
		return new DropFoodInfluence(perception.getMyBody().getFoodCarried());
	}

	/**
	 * Move in a random direction
	 * 
	 * @param memory
	 * @return a {@link PheromoneAndMoveInfluence} or a {@link MoveInfluence}
	 *         with a random direction
	 */
	public Influence wander(HashMap<String, Object> memory) {
		final Direction d = Direction.values()[new Random().nextInt(Direction.values().length)];
		return this.move(d, memory);
	}

	/**
	 * Computes the influence to move in the specified direction while putting
	 * pheromones according to the "pheromoneDistance" and "pheromoneType"
	 * values in memory
	 * 
	 * @param d
	 * @param memory
	 * @return a {@link PheromoneAndMoveInfluence} or a {@link MoveInfluence}
	 *         with the specified direction
	 */
	private Influence move(Direction d, HashMap<String, Object> memory) {
		if (memory.containsKey("pheromoneType") && memory.containsKey("pheromoneDistance")) {
			// Retrieve pheromone to put from memory
			PheromoneType pheromoneType = (PheromoneType) (memory.get("pheromoneType"));
			int pheromoneDistance = ((Integer) memory.get("pheromoneDistance")).intValue();
			float pheromoneQty = MAX_PHEROMONE - (pheromoneDistance * PHEROMONE_DECAY);

			// Update memory
			if (pheromoneDistance >= MAX_PHEROMONE_DISTANCE) {
				memory.remove("pheromoneType");
				memory.remove("pheromoneDistance");
			} else {
				memory.put("pheromoneDistance", new Integer(pheromoneDistance + 1));
			}

			return new PheromoneAndMoveInfluence(pheromoneType, pheromoneQty, d);
		}
		return new MoveInfluence(d);
	}
}
