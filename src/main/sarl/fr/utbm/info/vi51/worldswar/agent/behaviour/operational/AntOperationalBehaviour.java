package fr.utbm.info.vi51.worldswar.agent.behaviour.operational;

import static fr.utbm.info.vi51.worldswar.perception.PerceptionGrid.MY_POSITION;

import java.awt.Point;
import java.util.HashMap;

import fr.utbm.info.vi51.worldswar.environment.PheromoneType;
import fr.utbm.info.vi51.worldswar.environment.influence.DoNothingInfluence;
import fr.utbm.info.vi51.worldswar.environment.influence.DropFoodInfluence;
import fr.utbm.info.vi51.worldswar.environment.influence.Influence;
import fr.utbm.info.vi51.worldswar.environment.influence.MeleeAttackInfluence;
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

	private static final float MAX_PHEROMONE = 100;
	private static final float PHEROMONE_DECAY = 0.5f;

	private static final String PHEROMONE_QTY = "pheromoneQty"; //$NON-NLS-1$
	private static final String PHEROMONE_TYPE = "pheromoneType"; //$NON-NLS-1$
	private static final String LAST_MOVE_DIRECTION = "lastMoveDirection"; //$NON-NLS-1$
	private static final String LAST_ATTACK_DIRECTION = "lastAttackDirection"; //$NON-NLS-1$

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

		// The direction corresponding to the target received
		Direction primaryDirection = null;

		// The direction that will be chosen by this method
		Direction chosenDirection = null;

		primaryDirection = Direction.fromPoint(target);

		if (primaryDirection == null) {
			// Target is (0,0), this means we already reached it
			return new DoNothingInfluence();
		}

		// the direction is initialized to the primary direction
		chosenDirection = primaryDirection;
		// the angle between the primary direction and the chosen Direction
		// (1 delta = 45°)
		int delta = 1;
		// this RotationDirection will be tested first
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
			return this.move(chosenDirection, memory);
		}
		// if there is no free direction
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
			return this.move(d, memory);
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
		return this.move(d, memory);
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

		memory.put(PHEROMONE_TYPE, type);
		memory.put(PHEROMONE_QTY, new Float(MAX_PHEROMONE * coeff));
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
		this.startPheromoneTrail(memory, type, 0.35f);
	}

	/**
	 * Computes the influence to move in the specified direction while putting
	 * pheromones according to the PHEROMONE_QTY and PHEROMONE_TYPE values in
	 * memory. Also stores the direction to the "lastMoveDirection" entry in
	 * memory
	 * 
	 * @param d
	 * @param memory
	 * @return a {@link PheromoneAndMoveInfluence} or a {@link MoveInfluence}
	 *         with the specified direction
	 */
	private Influence move(Direction d, HashMap<String, Object> memory) {
		if (memory.containsKey(PHEROMONE_TYPE) && memory.containsKey(PHEROMONE_QTY)) {
			// Retrieve pheromone to put from memory
			final PheromoneType pheromoneType = (PheromoneType) (memory.get(PHEROMONE_TYPE));
			float pheromoneQty = ((Float) memory.get(PHEROMONE_QTY)).floatValue();

			// Update pheromone quantity
			pheromoneQty -= PHEROMONE_DECAY;

			// If the pheromone quantity is now negative, delete the keys from
			// memory and perform a simple move
			if (pheromoneQty <= 0) {
				memory.remove(PHEROMONE_TYPE);
				memory.remove(PHEROMONE_QTY);
				return this.moveWithoutPheromone(memory, d);
			}

			// Update memory
			memory.put(PHEROMONE_QTY, new Float(pheromoneQty));

			memory.put(LAST_MOVE_DIRECTION, d);
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
		memory.put(LAST_MOVE_DIRECTION, d); // $NON-NLS-1$
		return new MoveInfluence(d);
	}

	/**
	 * Attacks the target whose direction was given as parameter
	 * 
	 * @param d
	 * @return a {@link MeleeAttackInfluence}
	 */
	public Influence attackMeleeTarget(Direction d) {
		return new MeleeAttackInfluence(d);
	}
}
