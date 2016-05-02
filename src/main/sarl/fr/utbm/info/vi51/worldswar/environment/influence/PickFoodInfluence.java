package fr.utbm.info.vi51.worldswar.environment.influence;

/**
 * Pick the specified amount of food on the current location. {@see Influence}
 *
 */
public class PickFoodInfluence implements Influence {

	private int qty;

	/**
	 * @param quantity
	 *            the quantity of food to pick
	 */
	public PickFoodInfluence(int quantity) {
		this.qty = quantity;
	}

	/**
	 * @return the qty
	 */
	public int getQty() {
		return this.qty;
	}

}
