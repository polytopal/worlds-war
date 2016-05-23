package fr.utbm.info.vi51.worldswar.utils;

import java.awt.Point;
import java.util.ArrayList;

/**
 * Cardinal directions, with getters on the X and Y components of the
 * corresponding unit vector
 * 
 */
public enum Direction {

	NORTH_WEST(-1, -1),

	NORTH(0, -1),

	NORTH_EAST(1, -1),

	EAST(1, 0),

	SOUTH_EAST(1, 1),

	SOUTH(0, 1),

	SOUTH_WEST(-1, 1),

	WEST(-1, 0);

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
	 * For each direction of the enum, computes the 2 adjacent directions and stores it in an ArrayList
	 * 
	 * @return ArrayList<{@link Direction}>
	 */
	public ArrayList<Direction> adjacentDirections() {
		ArrayList<Direction> adjacentDirections = new ArrayList<>();

		switch(this){
		case NORTH_WEST :
			adjacentDirections.add(Direction.WEST);
			adjacentDirections.add(Direction.NORTH);
			break;
		case NORTH :
			adjacentDirections.add(Direction.NORTH_WEST);
			adjacentDirections.add(Direction.NORTH_EAST);
			break;
		case NORTH_EAST :
			adjacentDirections.add(Direction.NORTH);
			adjacentDirections.add(Direction.EAST);
			break;
		case EAST :
			adjacentDirections.add(Direction.NORTH_EAST);
			adjacentDirections.add(Direction.SOUTH_EAST);
			break;
		case SOUTH_EAST :
			adjacentDirections.add(Direction.SOUTH);
			adjacentDirections.add(Direction.EAST);
			break;
		case SOUTH :
			adjacentDirections.add(Direction.SOUTH_WEST);
			adjacentDirections.add(Direction.SOUTH_EAST);
			break;
		case SOUTH_WEST :
			adjacentDirections.add(Direction.SOUTH);
			adjacentDirections.add(Direction.WEST);
			break;
		case WEST :
			adjacentDirections.add(Direction.NORTH_WEST);
			adjacentDirections.add(Direction.SOUTH_WEST);
			break;
		default :
			break;
		}

		return adjacentDirections;
	}
}
