package fr.utbm.info.vi51.worldswar.utils;

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

}
