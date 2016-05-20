package fr.utbm.info.vi51.worldswar.environment;

/**
 * Caste of an ant : its role in its colony
 *
 */
@SuppressWarnings("static-method")
public enum Caste {

	// TODO - all the stats have to be adjusted
	GATHERER {
		@Override
		public int getCapacity() {
			return 5;
		}
	},
	EXPLORER {
		@Override
		public int getPerceptionRange() {
			return super.getPerceptionRange() + 1;
		}
	},
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
