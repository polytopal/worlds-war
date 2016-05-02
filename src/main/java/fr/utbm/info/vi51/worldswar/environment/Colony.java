package fr.utbm.info.vi51.worldswar.environment;

/**
 * An ant colony is a group of allies ants. Ants from the same colony will
 * cooperate for their colony's survival, and compete against any other colony.
 *
 */
public class Colony {

	private Breed breed;

	/**
	 * Create a new ant Colony
	 * 
	 * @param breed
	 *            the {@link Breed} of the ants of this colony
	 */
	public Colony(Breed breed) {
		this.breed = breed;
	}

	public Breed getBreed() {
		return this.breed;
	}

	public void setBreed(Breed breed) {
		this.breed = breed;
	}

}
