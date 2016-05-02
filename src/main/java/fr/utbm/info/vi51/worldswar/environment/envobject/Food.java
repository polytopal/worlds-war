package fr.utbm.info.vi51.worldswar.environment.envobject;

import java.awt.Point;

import fr.utbm.info.vi51.worldswar.environment.Stock;

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
	 * @return
	 * @see fr.utbm.info.vi51.worldswar.environment.Stock#pick(int)
	 */
	public int pick(int qty) {
		return this.stock.pick(qty);
	}

	/**
	 * @param qty
	 * @see fr.utbm.info.vi51.worldswar.environment.Stock#drop(int)
	 */
	public void drop(int qty) {
		this.stock.drop(qty);
	}

	/**
	 * @return
	 * @see fr.utbm.info.vi51.worldswar.environment.Stock#getAvailable()
	 */
	public int getAvailable() {
		return this.stock.getAvailable();
	}

	/**
	 * @return
	 * @see fr.utbm.info.vi51.worldswar.environment.Stock#isEmpty()
	 */
	public boolean isEmpty() {
		return this.stock.isEmpty();
	}

}
