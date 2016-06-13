package fr.utbm.info.vi51.worldswar.environment;

/**
 * An ant colony is a group of allies ants. Ants from the same colony will
 * cooperate for their colony's survival, and compete against any other colony.
 *
 */
public class Colony {

	private final Breed breed;

	/**
	 * Create a new ant Colony
	 * 
	 * @param breed
	 *            the {@link Breed} of the ants of this colony
	 */
	public Colony(Breed breed) {
		this.breed = breed;
	}

	/**
	 * @return the breed of the colony
	 */
	public Breed getBreed() {
		return this.breed;
	}

	@Override
	public String toString() {
		return "Colony [breed=" + this.breed + "]"; //$NON-NLS-1$ //$NON-NLS-2$
	}

}
