package fr.utbm.info.vi51.worldswar.environment.envobject;

import java.awt.Point;

import fr.utbm.info.vi51.worldswar.environment.Colony;
import fr.utbm.info.vi51.worldswar.utils.Stock;

/**
 * Represents an anthill on the map
 *
 */
public class AntHill extends StaticObject {

	private static final int INITIAL_FOOD_STOCK = 10;

	private Stock foodStock;

	private Colony colony;

	/**
	 * @param position
	 * @param colony
	 */
	public AntHill(Point position, Colony colony) {
		super(position);
		this.colony = colony;
		this.foodStock = new Stock(INITIAL_FOOD_STOCK);
	}

	////////////////////////////////////////
	////////// GETTERS & SETTERS ///////////
	////////////////////////////////////////

	/**
	 * @return the colony
	 */
	public Colony getColony() {
		return this.colony;
	}

	/**
	 * @param colony
	 *            the colony to set
	 */
	public void setColony(Colony colony) {
		this.colony = colony;
	}

	////////////////////////////////////////
	/// INHERITED FROM ENVIRONMENTOBJECT ///
	////////////////////////////////////////

	/** {@inheritDoc} **/
	@Override
	public boolean isTraversable() {
		return true;
	}

	////////////////////////////////////////
	/////////// FOOD STOCK METHODS /////////
	////////////////////////////////////////

	/**
	 * @param qty
	 * @return
	 * @see fr.utbm.info.vi51.worldswar.utils.Stock#pick(int)
	 */
	public int pickFood(int qty) {
		return this.foodStock.pick(qty);
	}

	/**
	 * @param qty
	 * @see fr.utbm.info.vi51.worldswar.utils.Stock#drop(int)
	 */
	public void dropFood(int qty) {
		this.foodStock.drop(qty);
	}

	/**
	 * @return
	 * @see fr.utbm.info.vi51.worldswar.utils.Stock#getAvailable()
	 */
	public int getAvailableFood() {
		return this.foodStock.getAvailable();
	}

	/**
	 * @return
	 * @see fr.utbm.info.vi51.worldswar.utils.Stock#isEmpty()
	 */
	public boolean isFoodEmpty() {
		return this.foodStock.isEmpty();
	}

}
