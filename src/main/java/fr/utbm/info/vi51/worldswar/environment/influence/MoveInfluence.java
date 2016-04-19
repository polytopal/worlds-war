package fr.utbm.info.vi51.worldswar.environment.influence;

import fr.utbm.info.vi51.worldswar.environment.Direction;

/**
 * Move in the specified {@link Direction}. {@see Influence}
 *
 */
public class MoveInfluence {

	private Direction direction;

	/**
	 * 
	 * @param d
	 *            the direction to move in
	 */
	public MoveInfluence(Direction d) {
		this.direction = d;
	}

	/**
	 * @return the direction
	 */
	public Direction getDirection() {
		return this.direction;
	}
}
