package fr.utbm.info.vi51.worldswar.environment.envobject;

import java.awt.Point;

import fr.utbm.info.vi51.worldswar.utils.Stock;

/**
 * Represents food on the map
 */
public class Food extends StaticObject {

	private Stock stock;

	/**
	 * Creates a food stack with the given amount of food units
	 * 
	 * @param position
	 * @param qty
	 */

	public Food(Point position, int qty) {
		super(position);
		this.stock = new Stock(qty);
	}

	/** {@inheritDoc} **/
	@Override
	public boolean isTraversable() {
		return true;
	}

	/**
	 * @param qty
	 * @return the amount of food that was actually picked
	 * @see fr.utbm.info.vi51.worldswar.utils.Stock#pick(int)
	 */
	public int pick(int qty) {
		return this.stock.pick(qty);
	}

	/**
	 * @param qty
	 * @see fr.utbm.info.vi51.worldswar.utils.Stock#drop(int)
	 */
	public void drop(int qty) {
		this.stock.drop(qty);
	}

	/**
	 * @return the amount of available food
	 * @see fr.utbm.info.vi51.worldswar.utils.Stock#getAvailable()
	 */
	public int getAvailable() {
		return this.stock.getAvailable();
	}

	/**
	 * @return true if there is no food in the stock
	 * @see fr.utbm.info.vi51.worldswar.utils.Stock#isEmpty()
	 */
	public boolean isEmpty() {
		return this.stock.isEmpty();
	}

}
