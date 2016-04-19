package fr.utbm.info.vi51.worldswar.environment.influence;

import java.awt.Point;

/**
 * Melee attack on the target location. {@see Influence}
 *
 */
public class AttackInfluence implements Influence {

	private Point target;

	/**
	 * 
	 * @param target
	 *            the attack's target location
	 */
	public AttackInfluence(Point target) {
		this.target = target;
	}

	public Point getTarget() {
		return this.target;
	}
}
