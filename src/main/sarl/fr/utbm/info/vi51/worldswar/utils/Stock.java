package fr.utbm.info.vi51.worldswar.utils;

/**
 * Utility class that provides methods for managing a stock of resources (eg.
 * food)
 * 
 *
 */
public class Stock {

	private int available;

	/**
	 * Creates a stock containing the specified amount of resource
	 * 
	 * @param qty
	 */
	public Stock(int qty) {
		this.available = qty;
	}

	/**
	 * Picks up the specified quantity in the stock. If there is not enough
	 * available, picks up as much as possible.
	 * 
	 * @param qty
	 * 
	 * @return The quantity of resource that has been picked
	 */
	public int pick(int qty) {
		if (qty > this.available) {
			final int r = this.available;
			this.available = 0;
			return r;
		}
		this.available -= qty;
		return qty;
	}

	/**
	 * Adds the specified quantity of resource to the stock
	 * 
	 * @param qty
	 */
	public void drop(int qty) {
		this.available += qty;
	}

	/**
	 * @return the available amount of resources in the stock
	 */
	public int getAvailable() {
		return this.available;
	}

	/**
	 * @return true if the stock is empty
	 */
	public boolean isEmpty() {
		return this.available == 0;
	}

	@Override
	public String toString() {
		return "Stock [available=" + this.available + "]"; //$NON-NLS-1$ //$NON-NLS-2$
	}

}
