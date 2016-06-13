package fr.utbm.info.vi51.worldswar.utils;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

/**
 * Cardinal directions, with getters on the X and Y components of the
 * corresponding unit vector
 * 
 */
public enum Direction {

	@SuppressWarnings("javadoc") NORTH_WEST(-1, -1),

	@SuppressWarnings("javadoc") NORTH(0, -1),

	@SuppressWarnings("javadoc") NORTH_EAST(1, -1),

	@SuppressWarnings("javadoc") EAST(1, 0),

	@SuppressWarnings("javadoc") SOUTH_EAST(1, 1),

	@SuppressWarnings("javadoc") SOUTH(0, 1),

	@SuppressWarnings("javadoc") SOUTH_WEST(-1, 1),

	@SuppressWarnings("javadoc") WEST(-1, 0);

	private final int x;
	private final int y;

	private Direction(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * @return the X component of the corresponding unit vector
	 */
	public int getX() {
		return this.x;
	}

	/**
	 * @return the Y component of the corresponding unit vector
	 */
	public int getY() {
		return this.y;
	}

	/**
	 * @return the point corresponding to the direction
	 */
	public Point getPoint() {
		return new Point(this.x, this.y);
	}

	/**
	 * @param point
	 * @return the Direction corresponding to the Point
	 */
	public static Direction fromPoint(Point point) {
		/*
		 * Right now, succession of if (max 4). Might be optimized by forcing
		 * the points coords in [(-1,-1),(1,1)]
		 */
		if (point.x < 0) {
			if (point.y < 0) { // (-1,-1)
				return NORTH_WEST;
			} else if (point.y > 0) { // (-1,1)
				return SOUTH_WEST;
			} else { // (-1,0)
				return WEST;
			}
		} else if (point.x > 0) {
			if (point.y < 0) { // (1,-1)
				return NORTH_EAST;
			} else if (point.y > 0) { // (1,1)
				return SOUTH_EAST;
			} else { // (1,0)
				return EAST;
			}
		} else if (point.y < 0) { // (0,-1)
			return NORTH;
		} else if (point.y > 0) { // (0,1)
			return SOUTH;
		} else { // (0,0)
			return null;
		}
	}

	/**
	 * @param rotationDirection
	 * @param delta
	 * @return the direction after performing delta times a 1/8 turn in the
	 *         given rotation direction
	 */
	public Direction adjacentDirection(RotationDirection rotationDirection, int delta) {
		Direction d = this;
		for (int i = 0; i < delta; i++) {
			d = d.adjacentDirection(rotationDirection);
		}
		return d;
	}

	/**
	 * @param rotationDirection
	 * @return the direction after performing a 1/8 turn in the given rotation
	 *         direction
	 */
	public Direction adjacentDirection(RotationDirection rotationDirection) {
		switch (rotationDirection) {
		case CLOCKWISE:
			switch (this) {
			case NORTH:
				return Direction.NORTH_EAST;
			case NORTH_EAST:
				return EAST;
			case EAST:
				return SOUTH_EAST;
			case SOUTH_EAST:
				return SOUTH;
			case SOUTH:
				return SOUTH_WEST;
			case SOUTH_WEST:
				return WEST;
			case WEST:
				return NORTH_WEST;
			case NORTH_WEST:
				return NORTH;
			default:
				throw new RuntimeException("undefined Direction : " + this); //$NON-NLS-1$
			}
		case COUNTER_CLOCKWISE:
			switch (this) {
			case NORTH:
				return Direction.NORTH_WEST;
			case NORTH_WEST:
				return WEST;
			case WEST:
				return SOUTH_WEST;
			case SOUTH_WEST:
				return SOUTH;
			case SOUTH:
				return SOUTH_EAST;
			case SOUTH_EAST:
				return EAST;
			case EAST:
				return NORTH_EAST;
			case NORTH_EAST:
				return NORTH;
			default:
				throw new RuntimeException("undefined Direction : " + this); //$NON-NLS-1$
			}
		default:
			throw new RuntimeException("undefined RotationDirection : " + rotationDirection); //$NON-NLS-1$
		}
	}

	/**
	 * For each direction of the enum, computes the 2 adjacent directions and
	 * stores it in an ArrayList
	 * 
	 * @return ArrayList<{@link Direction}>
	 */
	public ArrayList<Direction> adjacentDirections() {
		final ArrayList<Direction> adjacentDirections = new ArrayList<>();

		adjacentDirections.add(this.adjacentDirection(RotationDirection.COUNTER_CLOCKWISE));
		adjacentDirections.add(this.adjacentDirection(RotationDirection.CLOCKWISE));

		return adjacentDirections;
	}

	/**
	 * @return a random direction
	 */
	public static Direction random() {
		return Direction.values()[new Random().nextInt(Direction.values().length)];
	}

	/**
	 * Represents an angular direction : clockwise or counter clockwise
	 */
	public enum RotationDirection {
		@SuppressWarnings("javadoc") CLOCKWISE, @SuppressWarnings("javadoc") COUNTER_CLOCKWISE;

		/**
		 * @return the opposite direction
		 */
		public RotationDirection getOpposite() {
			switch (this) {
			case CLOCKWISE:
				return COUNTER_CLOCKWISE;
			case COUNTER_CLOCKWISE:
				return CLOCKWISE;
			default:
				throw new RuntimeException("undefined Direction : " + this); //$NON-NLS-1$
			}
		}

		/**
		 * @return a random rotation direction
		 */
		public static RotationDirection random() {
			return RotationDirection.values()[new Random().nextInt(RotationDirection.values().length)];
		}
	}
}
