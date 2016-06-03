package fr.utbm.info.vi51.worldswar.perception.perceivable;

import fr.utbm.info.vi51.worldswar.environment.envobject.Food;

/**
 * {@link Perceivable} representation of a {@link Food}
 */
public class PerceivableFood extends Perceivable {

	private final Food food;

	/**
	 * Builds the perceivable version of a {@link Food}
	 * 
	 * @param food
	 */
	public PerceivableFood(Food food) {
		super(food);
		this.food = food;
	}

	/**
	 * @return the available quantity of food on the stack
	 * @see fr.utbm.info.vi51.worldswar.environment.envobject.Food#getAvailable()
	 */
	public int getAvailable() {
		return this.food.getAvailable();
	}

	/**
	 * @return true if the food is worn out
	 * @see fr.utbm.info.vi51.worldswar.environment.envobject.Food#isEmpty()
	 */
	public boolean isEmpty() {
		return this.food.isEmpty();
	}
}
