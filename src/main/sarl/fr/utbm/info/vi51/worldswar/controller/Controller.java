package fr.utbm.info.vi51.worldswar.controller;

import fr.utbm.info.vi51.worldswar.environment.Environment;
import fr.utbm.info.vi51.worldswar.simulator.SimulationSpeed;
import fr.utbm.info.vi51.worldswar.simulator.Simulator;
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
		this.emitEvent(new ControllerCommandEvent(new SimulationSpeedCommand(speed)));
	}

	/**
	 * Starts a new simulation
	 * 
	 * @param simPara
	 *            The parameters of the simulation of type
	 *            {@link SimulationParameters}
	 */
	public void newSimulation(SimulationParameters simPara) {
		this.emitEvent(new ControllerCommandEvent(new NewSimulationCommand(simPara)));
		System.out.println("lancement nouvelle simulation"); //$NON-NLS-1$
	}

	/**
	 * Stops the current simulation
	 */
	public void stopSimulation() {
		this.emitEvent(new ControllerCommandEvent(new StopSimulationCommand()));
		System.out.println("arret simulation"); //$NON-NLS-1$
	}

	/**
	 * Closes the program
	 */
	public void stopProgram() {
		this.emitEvent(new ControllerCommandEvent(new StopProgramCommand()));
		System.out.println("arret programme"); //$NON-NLS-1$
	}

	private void emitEvent(ControllerCommandEvent event) {
		this.space.emit(event, Scopes.allParticipants());
	}

}
