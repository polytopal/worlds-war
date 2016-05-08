package fr.utbm.info.vi51.worldswar.gui;

import java.awt.Event;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.KeyStroke;

import fr.utbm.info.vi51.worldswar.controller.Controller;
import fr.utbm.info.vi51.worldswar.controller.SimulationParameters;
import fr.utbm.info.vi51.worldswar.simulator.SimulationSpeed;

/**
 * @author Leo
 *
 *         The GUI action manager is a Singleton. It create all the actions
 *         available from the GUI at the start of the program.
 */
public class GuiActionsManager {

	private final Action newSimulationAction;
	private final Action stopSimulationAction;
	private final Action pauseSimulationAction;

	private final Map<SimulationSpeed, Action> speedActionsMap;

	GuiActionsManager(final Controller controller) {
		this.newSimulationAction = new NewSimulationAction(controller);
		this.stopSimulationAction = new StopSimulationAction(controller);
		this.pauseSimulationAction = new PauseSimulationAction(controller);

		this.speedActionsMap = new HashMap<>();
		for (final SimulationSpeed simSpeed : SimulationSpeed.values()) {
			this.speedActionsMap.put(simSpeed, new SpeedSetterAction(controller, simSpeed));
		}
	}

	/**
	 * @return the action to start a new simulation
	 */
	public Action getNewSimulationAction() {
		return this.newSimulationAction;
	}

	/**
	 * @return the action to stop the current simulation
	 */
	public Action getStopSimulationAction() {
		return this.stopSimulationAction;
	}
	
	public Action getPauseSimulationAction() {
		return this.pauseSimulationAction;
	}

	public Action getSpeedAction(SimulationSpeed simSpeed) {
		return this.speedActionsMap.get(simSpeed);
	}

	/**
	 * @author Leo
	 * 
	 *         This action allow to start a new simulation
	 */
	private class NewSimulationAction extends AbstractAction {
		private static final long serialVersionUID = 4958436812700297538L;
		private final Controller controller;

		public NewSimulationAction(final Controller controller) {
			super(Messages.getString("MenuBar.newSimulation")); //$NON-NLS-1$
			putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_N, Event.CTRL_MASK));
			this.controller = controller;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			final SimulationParameters simulationParameters = LaunchSimulationDialog.getSimulationParameters();

			if (simulationParameters != null) {
				this.controller.newSimulation(simulationParameters);
			}
		}
	}

	/**
	 * @author Leo
	 * 
	 *         This action allow to stop the current simulation
	 */
	private class StopSimulationAction extends AbstractAction {
		private static final long serialVersionUID = 2286692516797367038L;
		private final Controller controller;

		public StopSimulationAction(final Controller controller) {
			super(Messages.getString("MenuBar.stopSimulation")); //$NON-NLS-1$
			putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_S, Event.CTRL_MASK));
			this.controller = controller;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			this.controller.stopSimulation();
		}
	}
	
	/**
	 * 
	 *         This action allows to pause/unpause the simulation
	 */
	private class PauseSimulationAction extends AbstractAction {
		private static final long serialVersionUID = 2286692516797367038L;
		private final Controller controller;

		public PauseSimulationAction(final Controller controller) {
			super(Messages.getString("MenuBar.pauseSimulation")); //$NON-NLS-1$
			putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_P, Event.CTRL_MASK));
			this.controller = controller;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			this.controller.pauseSimulation();
		}
	}

	/**
	 * @author Leo
	 * 
	 *         This action allows to modify the simulation speed, with the speed
	 *         given in the constructor
	 */
	private class SpeedSetterAction extends AbstractAction {
		private static final long serialVersionUID = -3918672044655803199L;

		private final Controller controller;
		private final SimulationSpeed speed;

		public SpeedSetterAction(final Controller controller, SimulationSpeed speed) {
			super(Messages.getString(speed.getPropertyKey()));
			this.speed = speed;
			this.controller = controller;
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			this.controller.setSimulationSpeed(this.speed);
		}

	}

}
