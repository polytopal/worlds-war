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
			return 30;
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
			return 5;
		}
	};

	public int getMaxHealth() {
		return 100;
	}

	public int getAttackDamage() {
		return 10;
	}

	public int getCapacity() {
		return 10;
	}

	public int getLifeTime() {
		return 150;
	}

	public int getPerceptionRange() {
		return 3;
	}
}
