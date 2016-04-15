package fr.utbm.info.vi51.worldswar.utils;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author Leo
 *
 * @param <T>
 * 
 *            This Class is a generic that contains a matrix of collection of
 *            type T.
 */
public class CollectionGrid<T> {

	private final ArrayList<ArrayList<ArrayList<T>>> grid;

	private final int xOffset;
	private final int yOffset;

	private final int width;
	private final int height;

	/**
	 * @param xMin
	 * @param xMax
	 * @param yMin
	 * @param yMax
	 * 
	 *            Build collection grid with the extreme points, each cell
	 *            contains an empty collection. Each coordinate can be positive
	 *            or negative.
	 */
	public CollectionGrid(int xMin, int xMax, int yMin, int yMax) {

		if (xMin >= xMax) {
			throw new IllegalArgumentException("xMax have to be greater than xMin"); //$NON-NLS-1$
		}
		if (yMin >= yMax) {
			throw new IllegalArgumentException("yMax have to be greater than yMin"); //$NON-NLS-1$
		}

		this.width = xMax - xMin + 1;
		this.height = yMax - yMin + 1;

		this.xOffset = -xMin;
		this.yOffset = -yMin;

		// build the first List, that contains the lines
		this.grid = new ArrayList<>(this.height);
		for (int y = yMin; y <= yMax; y++) {
			// build each line
			final ArrayList<ArrayList<T>> line = new ArrayList<>(this.width);
			for (int x = xMin; x <= xMax; x++) {
				// build the list of each cell
				final ArrayList<T> cellList = new ArrayList<>();
				line.add(cellList);
			}
			this.grid.add(line);
		}

	}

	/**
	 * @return the width of the grid
	 */
	public int getWidth() {
		return this.width;
	}

	/**
	 * @return the height of the matrix
	 */
	public int getHeight() {
		return this.height;
	}

	/**
	 * @return the minimum index of the matrix on the axis x
	 */
	public int getXMin() {
		return 0 - this.xOffset;
	}

	/**
	 * @return the minimum index of the matrix on the axis y
	 */
	public int getYMin() {
		return 0 - this.yOffset;
	}

	/**
	 * @return the maximum index of the matrix on the axis x
	 */
	public int getXMax() {
		return getXMin() + this.width - 1;
	}

	/**
	 * @return the maximum index of the matrix on the axis y
	 */
	public int getYMax() {
		return getYMin() + this.height - 1;
	}

	/**
	 * @param x
	 * @param y
	 * @return the collection at the position (x,y)
	 */
	public Collection<T> get(int x, int y) {
		return this.grid.get(y + this.yOffset).get(x + this.xOffset);
	}

	/**
	 * Set the collection at the position (x,y)
	 * 
	 * @param x
	 * @param y
	 * @param collection
	 */
	public void set(int x, int y, Collection<T> collection) {
		this.grid.get(y + this.yOffset).set(x + this.xOffset, new ArrayList<>(collection));
	}

	/**
	 * Add an element to the collection at the position (x,y)
	 * 
	 * @param x
	 * @param y
	 * @param value
	 */
	public void add(int x, int y, T value) {
		this.grid.get(y + this.yOffset).get(x + this.xOffset).add(value);
	}

	@Override
	public String toString() {
		final StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("["); //$NON-NLS-1$
		for (final ArrayList<ArrayList<T>> line : this.grid) {
			stringBuilder.append(line + "\n"); //$NON-NLS-1$
		}
		stringBuilder.append("]"); //$NON-NLS-1$
		return stringBuilder.toString();
	}

}
