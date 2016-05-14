package fr.utbm.info.vi51.worldswar.controller;

import java.util.UUID;

import fr.utbm.info.vi51.worldswar.simulator.SimulationSpeed;
import fr.utbm.info.vi51.worldswar.simulator.Simulator;
import io.sarl.lang.core.Address;
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
	private final UUID uuid;
	private final Address address;

	/**
	 * Builds a controller that will send its events to the given
	 * {@link EventSpace}
	 * 
	 * @param space
	 */
	public Controller(EventSpace space) {
		this.space = space;
		this.uuid = UUID.randomUUID();
		this.address = new Address(space.getID(), this.uuid);
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
	 * @param simulationParameters
	 *            The parameters of the simulation of type
	 *            {@link SimulationParameters}
	 */
	public void newSimulation(SimulationParameters simulationParameters) {
		this.emitEvent(new ControllerCommandEvent(new NewSimulationCommand(simulationParameters)));
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
	 * Pause the current simulation
	 */
	public void pauseSimulation() {
		this.emitEvent(new ControllerCommandEvent(new PauseSimulationCommand()));
		System.out.println("pause simulation"); //$NON-NLS-1$
	}
	
	/**
	 * Resume the current simulation
	 */
	public void resumeSimulation() {
		this.emitEvent(new ControllerCommandEvent(new ResumeSimulationCommand()));
		System.out.println("resume simulation"); //$NON-NLS-1$
	}
	
	/**
	 * Execute a step in the current simulation
	 */
	public void stepSimulation() {
		this.emitEvent(new ControllerCommandEvent(new StepSimulationCommand()));
		System.out.println("step simulation"); //$NON-NLS-1$
	}


	/**
	 * Closes the program
	 */
	public void stopProgram() {
		this.emitEvent(new ControllerCommandEvent(new StopProgramCommand()));
		System.out.println("arret programme"); //$NON-NLS-1$
	}

	private void emitEvent(ControllerCommandEvent event) {
		event.setSource(this.address);
		this.space.emit(event, Scopes.allParticipants());
	}

}
