package fr.utbm.info.vi51.worldswar.environment;

/**
 * An ant colony is a group of allies ants. Ants from the same colony will
 * cooperate for their colony's survival, and compete against any other colony.
 *
 */
public class Colony {

	/**
	 * Unique identifier of the colony in the environment.
	 */
	private int id;

	private Breed breed;

	/**
	 * Create a new Colony
	 * 
	 * @param id
	 *            an integer that must be a unique identifier of the colony for
	 *            the simulation
	 * @param breed
	 *            the {@link Breed} of the ants of the colony
	 */
	private Colony(int id, Breed breed) {
		this.id = id;
		this.breed = breed;
	}

	public Breed getBreed() {
		return this.breed;
	}

	public void setBreed(Breed breed) {
		this.breed = breed;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + this.id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (this.getClass() != obj.getClass())
			return false;
		Colony other = (Colony) obj;
		if (this.id != other.id)
			return false;
		return true;
	}

}
