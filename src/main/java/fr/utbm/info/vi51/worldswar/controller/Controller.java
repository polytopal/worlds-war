package fr.utbm.info.vi51.worldswar.controller;

import org.omg.CORBA.Environment;

import fr.utbm.info.vi51.worldswar.simulator.SimulationSpeed;
import io.sarl.lang.core.EventSpace;
import io.sarl.util.Scopes;

/**
 * 
 * Allows the GUI to send commands to the {@link Simulator} and the
 * {@link Environment}
 * 
 */
public class Controller {

	private final EventSpace space;

	/**
	 * Builds a controller that will send its events to the given
	 * {@link EventSpace}
	 * 
	 * @param space
	 */
	public Controller(EventSpace space) {
		this.space = space;
	}

	/**
	 * Requests a change in the speed of the simulation
	 * 
	 * @param speed
	 */
	public void setSimulationSpeed(SimulationSpeed speed) {
		emitEvent(new ControllerCommandEvent(new SimulationSpeedCommand(speed)));
	}

	private void emitEvent(ControllerCommandEvent event) {
		this.space.emit(event, Scopes.allParticipants());
	}

}
