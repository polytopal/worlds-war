package fr.utbm.info.vi51.worldswar.controller;

/**
 * 
 * Represents a GUI input to process
 *
 */
public class ControllerCommand {

	private ControllerCommandType type;

	/**
	 * Builds a new ControllerCommand with the specified
	 * {@link ControllerCommandType type}
	 * 
	 * @param type
	 */
	public ControllerCommand(ControllerCommandType type) {
		this.type = type;
	}

	/**
	 * @return the {@link ControllerCommandType type} of the command
	 */
	public ControllerCommandType getType() {
		return type;
	}
}
