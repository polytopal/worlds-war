package fr.utbm.info.vi51.worldswar.gui;

import java.awt.Event;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.KeyStroke;

import fr.utbm.info.vi51.worldswar.controller.Controller;

/**
 * @author Leo
 *
 *         The GUI action manager is a Singleton. It create all the actions
 *         available from the GUI at the start of the program.
 */
public class GuiActionsManager {

	private final Action newSimulationAction;
	private final Action stopSimulationAction;

	GuiActionsManager(final Controller controller) {
		this.newSimulationAction = new NewSimulationAction(controller);
		this.stopSimulationAction = new StopSimulationAction(controller);
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

	/**
	 * @author Leo
	 * 
	 *         This action allow to start a new simulation
	 */
	private class NewSimulationAction extends AbstractAction {
		private static final long serialVersionUID = 4958436812700297538L;
		private Controller controller;
		/**
		 * New Simulation Action constructor
		 */
		public NewSimulationAction(final Controller controller) {
			super(Messages.getString("MenuBar.newSimulation")); //$NON-NLS-1$
			putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_N, Event.CTRL_MASK));
			this.controller = controller;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			this.controller.newSimulation();
		}
	}

	/**
	 * @author Leo
	 * 
	 *         This action allow to stop the current simulation
	 */
	private class StopSimulationAction extends AbstractAction {
		private static final long serialVersionUID = 2286692516797367038L;
		private Controller controller;
		/**
		 * Stop Simulation Action constructor
		 */
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

}
