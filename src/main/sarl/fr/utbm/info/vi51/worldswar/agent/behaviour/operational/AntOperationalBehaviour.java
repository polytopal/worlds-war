package fr.utbm.info.vi51.worldswar.agent.behaviour.operational;

import static fr.utbm.info.vi51.worldswar.perception.PerceptionGrid.MY_POSITION;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

	private static final float MAX_PHEROMONE = 100;
	private static final float PHEROMONE_DECAY = 0.5f;

	/**
	 * Chances that a wandering ant will choose a new direction instead of going
	 * forward
	 */
	private static final float WANDER_TURN_FREQUENCY = 0.2f;

	/**
	 * Move in a direction to the target : either directly to the target
	 * (primary direction), either using an alternative direction.
	 * 
	 * @param perception
	 * @param memory
	 * @param target
	 * @return a {@link PheromoneAndMoveInfluence} or a {@link MoveInfluence}
	 *         with a direction to the target
	 */
	public Influence moveToTarget(AntPerception perception, HashMap<String, Object> memory, Point target) {
		/*
		 * The direction corresponding to the target received
		 */
		Direction primaryDirection = null;
		/*
		 * The directions directly next to the primary direction, that can be
		 * used if the primary target can't be reached
		 */
		ArrayList<Direction> alternativeDirections = new ArrayList<>(3);

		/*
		 * Right now, succession of if (max 4). Might be optimized by packing
		 * the target's coords in [(-1,-1),(1,1)]
		 */
		if (target.x < 0) {
			if (target.y < 0) { // (-1,-1)
				primaryDirection = Direction.NORTH_WEST;
			} else if (target.y > 0) { // (-1,1)
				primaryDirection = Direction.SOUTH_WEST;

			} else { // (-1,0)
				primaryDirection = Direction.WEST;

			}
		} else if (target.x > 0) {
			if (target.y < 0) { // (1,-1)
				primaryDirection = Direction.NORTH_EAST;

			} else if (target.y > 0) { // (1,1)
				primaryDirection = Direction.SOUTH_EAST;

			} else { // (1,0)
				primaryDirection = Direction.EAST;

			}
		} else if (target.y < 0) { // (0,-1)
			primaryDirection = Direction.NORTH;

		} else if (target.y > 0) { // (0,1)
			primaryDirection = Direction.SOUTH;

		}

		if (primaryDirection == null) {
			// Target is (0,0), this means we already reached it
			return new DoNothingInfluence();
		}
		// If the primaryDirection isn't null, compute
		alternativeDirections = primaryDirection.adjacentDirections();

		/*
		 * If the cell targeted by the primaryDirection is free (<=>
		 * traversable), generates the Influence corresponding to the direction
		 */
		if (perception.isTraversable(primaryDirection.getPoint())) {
			return this.move(primaryDirection, memory);
		}

		/*
		 * If not, uses one of the 2 alternative directions to generate the
		 * influence. This direction might not be traversable either.
		 */
		return this.move(alternativeDirections.get(new Random().nextInt(alternativeDirections.size())), memory);

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
	 * Move in a random direction. Produces a much smoother trajectory if the
	 * last move direction is stored in memory as "lastMoveDirection". In this
	 * case, will return the same direction, or an adjacent one.
	 * 
	 * @param perception
	 * @param memory
	 * @return a {@link PheromoneAndMoveInfluence} or a {@link MoveInfluence}
	 *         with a random direction
	 */
	public Influence wander(AntPerception perception, HashMap<String, Object> memory) {
		if (!memory.containsKey("lastMoveDirection")) {
			final Direction d = Direction.values()[new Random().nextInt(Direction.values().length)];
			return this.move(d, memory);
		}

		Direction lastDirection = (Direction) (memory.get("lastMoveDirection"));
		if (Math.random() < WANDER_TURN_FREQUENCY) {
			final List<Direction> adjacentDirections = lastDirection.adjacentDirections();
			final Direction d = adjacentDirections.get(new Random().nextInt(adjacentDirections.size()));
			return this.move(d, memory);
		}
		return this.move(lastDirection, memory);
	}

	/**
	 * Starts a pheromone trail by "remembering" in memory what pheromones to
	 * place
	 * 
	 * @param memory
	 *            the ant memory
	 * @param type
	 *            the type of pheromones to place
	 * @param coeff
	 *            between 0 and 1 : the intensity of the trail. A higher value
	 *            means the trail will last longer, and be taken more into
	 *            account by other ants.
	 */
	public void startPheromoneTrail(HashMap<String, Object> memory, PheromoneType type, float coeff) {
		assert (coeff >= 0);
		assert (coeff <= 1);

		memory.put("pheromoneType", type);
		memory.put("pheromoneQty", new Float(MAX_PHEROMONE * coeff));
	}

	/**
	 * Starts a pheromone trail by "remembering" in memory what pheromones to
	 * place. The trail will have the highest possible intensity.
	 * 
	 * @see AntOperationalBehaviour#startPheromoneTrail(HashMap, PheromoneType,
	 *      float)
	 * 
	 * @param memory
	 * @param type
	 */
	public void startPheromoneTrail(HashMap<String, Object> memory, PheromoneType type) {
		this.startPheromoneTrail(memory, type, 1.f);
	}

	/**
	 * Computes the influence to move in the specified direction while putting
	 * pheromones according to the "pheromoneQty" and "pheromoneType" values in
	 * memory. Also stores the direction to the "lastMoveDirection" entry in
	 * memory
	 * 
	 * @param d
	 * @param memory
	 * @return a {@link PheromoneAndMoveInfluence} or a {@link MoveInfluence}
	 *         with the specified direction
	 */
	private Influence move(Direction d, HashMap<String, Object> memory) {
		if (memory.containsKey("pheromoneType") && memory.containsKey("pheromoneQty")) {
			// Retrieve pheromone to put from memory
			PheromoneType pheromoneType = (PheromoneType) (memory.get("pheromoneType"));
			float pheromoneQty = ((Float) memory.get("pheromoneQty")).floatValue();

			// Update pheromone quantity
			pheromoneQty -= PHEROMONE_DECAY;

			// If the pheromone quantity is now negative, delete the keys from
			// memory and perform a simple move
			if (pheromoneQty <= 0) {
				memory.remove("pheromoneType");
				memory.remove("pheromoneQty");
				return this.moveWithoutPheromone(memory, d);
			}

			// Update memory
			memory.put("pheromoneQty", new Float(pheromoneQty));

			memory.put("lastMoveDirection", d);
			return new PheromoneAndMoveInfluence(pheromoneType, pheromoneQty, d);
		}
		return this.moveWithoutPheromone(memory, d);
	}

	/**
	 * Computes the influence to move in the specified direction, without
	 * putting any pheromone on ground. Stores the direction in memory as
	 * "lastMoveDirection".
	 * 
	 * @param memory
	 * @param d
	 * @return a {@link MoveInfluence}
	 */
	private Influence moveWithoutPheromone(HashMap<String, Object> memory, Direction d) {
		memory.put("lastMoveDirection", d);
		return new MoveInfluence(d);
	}
}
