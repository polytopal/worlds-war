package fr.utbm.info.vi51.worldswar.environment;

/**
 * Lists the available ant breeds for colonies
 *
 */
public enum Breed {
	DARK_ANTS("Breed.darkAnts"), RED_ANTS("Breed.redAnts"); //$NON-NLS-1$ //$NON-NLS-2$

	private String stringRepresentation;

	private Breed(String stringRepresentation) {
		this.stringRepresentation = stringRepresentation;
	}

	@Override
	public String toString() {
		return Messages.getString(this.stringRepresentation);
	}
}
