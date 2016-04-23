package fr.utbm.info.vi51.worldswar.environment.perceivable;

import fr.utbm.info.vi51.worldswar.environment.envobject.Food;

/**
 * {@link Perceivable} representation of a {@link Food}
 */
public class PerceivableFood extends Perceivable {
	private Food food;

	public PerceivableFood(Food food) {
		super(food);
		this.food = food;
	}

	/**
	 * @return
	 * @see fr.utbm.info.vi51.worldswar.environment.envobject.Food#getAvailable()
	 */
	public int getAvailable() {
		return this.food.getAvailable();
	}

	/**
	 * @return
	 * @see fr.utbm.info.vi51.worldswar.environment.envobject.Food#isEmpty()
	 */
	public boolean isEmpty() {
		return this.food.isEmpty();
	}
}
