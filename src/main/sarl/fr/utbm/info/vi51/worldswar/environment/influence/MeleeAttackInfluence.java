package fr.utbm.info.vi51.worldswar.environment.influence;

import fr.utbm.info.vi51.worldswar.utils.Direction;

/**
 * Melee attack in the targeted {@link Direction}. {@see Influence}
 *
 */
public class MeleeAttackInfluence implements Influence {

	private Direction target;

	/**
	 * 
	 * @param target
	 *            the direction of the attack
	 */
	public MeleeAttackInfluence(Direction target) {
		this.target = target;
	}

	public Direction getTarget() {
		return this.target;
	}
}
