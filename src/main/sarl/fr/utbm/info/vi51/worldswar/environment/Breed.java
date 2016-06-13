package fr.utbm.info.vi51.worldswar.environment;

import fr.utbm.info.vi51.worldswar.gui.Messages;

/**
 * Lists the available ant breeds for colonies
 *
 */
public enum Breed {
	/**
	 * Most common European ants.
	 */
	DARK_ANTS("Breed.darkAnts", 1.0f, 1.0f, 1.0f, 1.0f, 1.0f), //$NON-NLS-1$
	/**
	 * Strong fighters, but less effective workers
	 */
	RED_ANTS("Breed.redAnts", 1.2f, 1.3f, 0.8f, 0.9f, 1.0f); //$NON-NLS-1$

	private String propertyKey;

	private float healthMultiplier;
	private float attackDamageMultiplier;
	private float capacityMultiplier;
	private float lifeTimeMultiplier;
	private float perceptionRangeMultiplier;

	private Breed(String stringRepresentation, float healthMultiplier, float attackDamageMultiplier,
			float capacityMultiplier, float lifeTimeMultiplier, float perceptionRangeMultiplier) {
		this.propertyKey = stringRepresentation;
		this.healthMultiplier = healthMultiplier;
		this.attackDamageMultiplier = attackDamageMultiplier;
		this.capacityMultiplier = capacityMultiplier;
		this.lifeTimeMultiplier = lifeTimeMultiplier;
		this.perceptionRangeMultiplier = perceptionRangeMultiplier;

	}

	/**
	 * @return the health multiplier of the breed
	 */
	public float getHealthMultiplier() {
		return this.healthMultiplier;
	}

	/**
	 * @return the attack damage multiplier of the breed
	 */
	public float getAttackDamageMultiplier() {
		return this.attackDamageMultiplier;
	}

	/**
	 * @return the capacity multiplier of the breed
	 */
	public float getCapacityMultiplier() {
		return this.capacityMultiplier;
	}

	/**
	 * @return the life time multiplier of the breed
	 */
	public float getLifeTimeMultiplier() {
		return this.lifeTimeMultiplier;
	}

	/**
	 * @return the perception range multiplier of the breed
	 */
	public float getPerceptionRangeMultiplier() {
		return this.perceptionRangeMultiplier;
	}

	@Override
	public String toString() {
		return Messages.getString(this.propertyKey);
	}

}
