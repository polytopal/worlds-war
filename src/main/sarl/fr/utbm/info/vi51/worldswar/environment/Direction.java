package fr.utbm.info.vi51.worldswar.environment;

/**
 * Cardinal directions, with getters on the X and Y components of the
 * corresponding unit vector
 * 
 */
public enum Direction {

	NORTH(0, -1),

	EAST(1, 0),

	SOUTH(0, 1),

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
