package fr.utbm.info.vi51.worldswar.environment.influence;

/**
 * Drop the requested amount of food on the current location.
 * 
 *
 */
public class DropFoodInfluence implements Influence {

	private int droppedAmount;

	public DropFoodInfluence(int amount) {
		this.droppedAmount = amount;
	}

	/**
	 * @return the droppedAmount
	 */
	public int getDroppedAmount() {
		return this.droppedAmount;
	}
}
