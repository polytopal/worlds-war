package fr.utbm.info.vi51.worldswar.agent.behaviour.operational;

import static fr.utbm.info.vi51.worldswar.perception.PerceptionGrid.MY_POSITION;

import java.awt.Point;
import java.util.HashMap;

import fr.utbm.info.vi51.worldswar.environment.PheromoneType;
import fr.utbm.info.vi51.worldswar.environment.influence.DoNothingInfluence;
import fr.utbm.info.vi51.worldswar.environment.influence.DropFoodInfluence;
import fr.utbm.info.vi51.worldswar.environment.influence.Influence;
import fr.utbm.info.vi51.worldswar.environment.influence.MoveInfluence;
import fr.utbm.info.vi51.worldswar.environment.influence.PheromoneAndMoveInfluence;
import fr.utbm.info.vi51.worldswar.environment.influence.PickFoodInfluence;
import fr.utbm.info.vi51.worldswar.perception.AntPerception;
import fr.utbm.info.vi51.worldswar.utils.Direction;
import fr.utbm.info.vi51.worldswar.utils.Direction.RotationDirection;

/**
 * Defines basic operational behaviours for ants.
 */
@SuppressWarnings("static-method")
public class AntOperationalBehaviour {

	private static final String PHEROMONE_DISTANCE = "pheromoneDistance"; //$NON-NLS-1$
	private static final String PHEROMONE_TYPE = "pheromoneType"; //$NON-NLS-1$
	private static final String LAST_MOVE_DIRECTION = "lastMoveDirection"; //$NON-NLS-1$

	private static final float MAX_PHEROMONE = 15;
	private static final float PHEROMONE_DECAY = 0.1f;

	private static final int MAX_PHEROMONE_DISTANCE = (int) (MAX_PHEROMONE / PHEROMONE_DECAY);

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
		 * The direction that will be chosen by this method
		 */
		Direction chosenDirection = null;

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
		// the direction is initializes to the primary direction
		chosenDirection = primaryDirection;
		// the angle between the primary direction and the chosen Direction
		// (1 delta = 45°)
		int delta = 1;
		// this RotationDirection while be tested first
		final RotationDirection preferedRotation = RotationDirection.random();
		// the RotationDirection between the primary direction and the chosen
		// Direction
		RotationDirection r = preferedRotation;

		while (!perception.isTraversable(chosenDirection.getPoint()) && delta <= 3) {
			chosenDirection = primaryDirection.adjacentDirection(preferedRotation, delta);
			r = r.getOpposite();
			if (r == preferedRotation) {
				delta++;
			}
		}

		if (delta <= 3) {
			return this.move(perception, chosenDirection, memory);
		}
		// if there is not free directions
		return new DoNothingInfluence();

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
		if (!memory.containsKey(LAST_MOVE_DIRECTION)) {
			final Direction d = Direction.random();
			return this.move(perception, d, memory);
		}

		final Direction lastDirection = (Direction) (memory.get(LAST_MOVE_DIRECTION));
		Direction d = lastDirection;
		final RotationDirection rd = RotationDirection.random();
		if (Math.random() < WANDER_TURN_FREQUENCY) {
			d = d.adjacentDirection(rd);
		}

		int delta = 1;
		while (!perception.isTraversable(d.getPoint()) && delta < 8) {
			d = d.adjacentDirection(rd);
			delta++;
		}
		return this.move(perception, d, memory);
	}

	/**
	 * Computes the influence to move in the specified direction while putting
	 * pheromones according to the "pheromoneDistance" and "pheromoneType"
	 * values in memory. Also stores the direction to the "lastMoveDirection"
	 * entry in memory
	 * 
	 * @param d
	 * @param memory
	 * @return a {@link PheromoneAndMoveInfluence} or a {@link MoveInfluence}
	 *         with the specified direction
	 */
	private Influence move(AntPerception perception, Direction d, HashMap<String, Object> memory) {
		if (memory.containsKey(PHEROMONE_TYPE) && memory.containsKey(PHEROMONE_DISTANCE)) {
			// Retrieve pheromone to put from memory
			final PheromoneType pheromoneType = (PheromoneType) (memory.get(PHEROMONE_TYPE));
			final int pheromoneDistance = ((Integer) memory.get(PHEROMONE_DISTANCE)).intValue();
			float pheromoneQty = MAX_PHEROMONE - (pheromoneDistance * PHEROMONE_DECAY);

			// Update memory
			if (pheromoneDistance >= MAX_PHEROMONE_DISTANCE) {
				memory.remove(PHEROMONE_TYPE);
				memory.remove(PHEROMONE_DISTANCE);
			} else {
				memory.put(PHEROMONE_DISTANCE, new Integer(pheromoneDistance + 1));
			}

			// Prevents ants from stacking pheromones by traversing the same
			// cell multiple times
			final float qtyOnGround = perception.getPheromoneQtyAt(MY_POSITION, pheromoneType,
					perception.getMyBody().getColony());
			pheromoneQty = Math.max(pheromoneQty, qtyOnGround);

			memory.put(LAST_MOVE_DIRECTION, d);
			return new PheromoneAndMoveInfluence(pheromoneType, pheromoneQty, d);
		}
		memory.put(LAST_MOVE_DIRECTION, d);
		return new MoveInfluence(d);
	}
}
