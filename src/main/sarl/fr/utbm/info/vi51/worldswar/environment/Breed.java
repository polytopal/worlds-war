package fr.utbm.info.vi51.worldswar.environment;

/**
 * Lists the available ant breeds for colonies
 *
 */
public enum Breed {
	DARK_ANTS_OF_THE_NORTH("dark ants of the north"), RED_ANTS_OF_THE_JUNGLE(
			"RED_ANTS_OF_THE_JUNGLE"), GREEN_ANTS_OF_MARS("GREEN_ANTS_OF_MARS");

	private String stringRepresentation;

	private Breed(String stringRepresentation) {
		this.stringRepresentation = stringRepresentation;
	}

	@Override
	public String toString() {
		return stringRepresentation;
	}
}
