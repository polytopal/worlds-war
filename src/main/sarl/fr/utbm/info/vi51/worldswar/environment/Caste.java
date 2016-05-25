package fr.utbm.info.vi51.worldswar.environment;

/**
 * Caste of an ant : its role in its colony
 *
 */
@SuppressWarnings("static-method")
public enum Caste {

	/**
	 * These ants try to gather food and bring it home as efficiently as
	 * possible.
	 */
	GATHERER {
		@Override
		public int getCapacity() {
			return 5;
		}
	},
	/**
	 * Wanders around the ant hill, trying to find food in unexplored areas.
	 */
	EXPLORER {
		@Override
		public int getPerceptionRange() {
			return super.getPerceptionRange() + 1;
		}
	},
	/**
	 * Patrols the ant hill to fight potential threats.
	 */
	SOLDIER {
		@Override
		public int getAttackDamage() {
			return 30;
		}

		@Override
		public int getMaxHealth() {
			return 150;
		}

		@Override
		public int getCapacity() {
			return 1;
		}
	};

	// All of theses methods return the default ant characteristics.
	// They can be override for a specific Cast. After that, the Breed modifier
	// will be applied

	/**
	 * @return the default max health of the ant
	 */
	public int getMaxHealth() {
		return 100;
	}

	/**
	 * @return the default attackDamage of the ant
	 */
	public int getAttackDamage() {
		return 10;
	}

	/**
	 * @return the default capacity of the ant
	 */
	public int getCapacity() {
		return 2;
	}

	/**
	 * @return the default initial life time of the ant
	 */
	public int getLifeTime() {
		return 150;
	}

	/**
	 * @return the default perception range of the ant
	 */
	public int getPerceptionRange() {
		return 3;
	}
}
