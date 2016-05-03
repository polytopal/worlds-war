package fr.utbm.info.vi51.worldswar.utils;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * @author Leo
 *
 * @param <T>
 * 
 *            This Class is a generic that contains a matrix of objects of type
 *            T.
 */
public class Grid<T> implements Iterable<T> {

	private final List<List<T>> grid;

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
	 *            contains a null object. Each coordinate can be positive or
	 *            negative.
	 */
	public Grid(int xMin, int xMax, int yMin, int yMax) {

		if (xMin >= xMax) {
			throw new IllegalArgumentException("xMax has to be greater than xMin"); //$NON-NLS-1$
		}
		if (yMin >= yMax) {
			throw new IllegalArgumentException("yMax has to be greater than yMin"); //$NON-NLS-1$
		}

		this.width = xMax - xMin + 1;
		this.height = yMax - yMin + 1;

		this.xOffset = -xMin;
		this.yOffset = -yMin;

		// build the first List, that contains the lines
		this.grid = new ArrayList<>(this.height);
		for (int y = yMin; y <= yMax; y++) {
			// build each line
			final List<T> line = new ArrayList<>(this.width);
			for (int x = yMin; x <= xMax; x++) {
				line.add(null);
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
		return this.getXMin() + this.width - 1;
	}

	/**
	 * @return the maximum index of the matrix on the axis y
	 */
	public int getYMax() {
		return this.getYMin() + this.height - 1;
	}

	/**
	 * @param x
	 * @param y
	 * @return the value at the position (x,y)
	 */
	public T get(int x, int y) {
		return this.grid.get(y + this.yOffset).get(x + this.xOffset);
	}

	/**
	 * @param position
	 * @return the value at the given position
	 */
	public T get(Point position) {
		return this.get(position.x, position.y);
	}

	/**
	 * Set the value at the position (x,y)
	 * 
	 * @param x
	 * @param y
	 * @param value
	 */
	public void set(int x, int y, T value) {
		this.grid.get(y + this.yOffset).set(x + this.xOffset, value);
	}

	@Override
	public String toString() {
		final StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("["); //$NON-NLS-1$
		for (final List<T> line : this.grid) {
			stringBuilder.append(line + "\n"); //$NON-NLS-1$
		}
		stringBuilder.append("]"); //$NON-NLS-1$
		return stringBuilder.toString();
	}

	@Override
	public Iterator<T> iterator() {
		return new GridIterator<>(this);
	}

	private static class GridIterator<T> implements Iterator<T> {

		private Grid<T> grid;
		private int x, y;

		public GridIterator(Grid<T> grid) {
			// This will be incremented by the first call to "next()" to point
			// position (xMin, yMin)
			this.x = grid.getXMin() - 1;
			this.y = grid.getYMin();
			this.grid = grid;
		}

		/** {@inheritDoc} **/
		@Override
		public boolean hasNext() {
			return (this.y < this.grid.getYMax() || this.x < this.grid.getXMax());
		}

		/** {@inheritDoc} **/
		@Override
		public T next() {
			if (!this.hasNext()) {
				throw new NoSuchElementException();
			}

			if (this.x < this.grid.getXMax()) {
				this.x++;
			} else {
				this.y++;
				this.x = this.grid.getXMin();
			}

			return this.grid.get(this.x, this.y);
		}
	}
}
