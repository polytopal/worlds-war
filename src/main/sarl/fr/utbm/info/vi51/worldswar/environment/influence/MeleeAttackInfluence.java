package fr.utbm.info.vi51.worldswar.environment.influence;

import fr.utbm.info.vi51.worldswar.utils.Direction;

/**
 * Melee attack in the targeted {@link Direction}.
 * 
 * @see Influence
 *
 */
public class MeleeAttackInfluence implements Influence {

	private Direction direction;

	/**
	 * 
	 * @param direction
	 *            the direction of the attack
	 */
	public MeleeAttackInfluence(Direction direction) {
		this.direction = direction;
	}

	/**
	 * @return the direction where the attack is targeted
	 */
	public Direction getDirection() {
		return this.direction;
	}
}
