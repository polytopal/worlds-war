package fr.utbm.info.vi51.worldswar.gui;

import java.awt.Event;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.KeyStroke;

<<<<<<< HEAD
<<<<<<< HEAD
import fr.utbm.info.vi51.worldswar.controller.Controller;
=======
import fr.utbm.info.vi51.worldswar.gui.messages.Messages;
>>>>>>> 249c5b199d6ff2d49dce818b2a035e04c26ca549

=======
>>>>>>> 3b4934e2f7ec323200354155d423a594256842a2
/**
 * @author Leo
 *
 *         The GUI action manager is a Singleton. It create all the actions
 *         available from the GUI at the start of the program.
 */
public class GuiActionsManager {

	private final Action newSimulationAction;
	private final Action stopSimulationAction;

	GuiActionsManager(Controller controller) {
		this.newSimulationAction = new NewSimulationAction(controller);
		this.stopSimulationAction = new StopSimulationAction(controller);
	}

	/**
	 * @return the unique instance of the manager
	 */

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
		public NewSimulationAction(Controller controller) {
			super(Messages.getString("MenuBar.newSimulation")); //$NON-NLS-1$
			putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_N, Event.CTRL_MASK));
			this.controller = controller;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO LR - implementer lancement nouvelle simulation
			System.out.println("lancement nouvelle simulation"); //$NON-NLS-1$
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
		public StopSimulationAction(Controller controller) {
			super(Messages.getString("MenuBar.stopSimulation")); //$NON-NLS-1$
			putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_S, Event.CTRL_MASK));
			this.controller = controller;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO LR - implementer arret simulation
			System.out.println("arret simulation"); //$NON-NLS-1$
			this.controller.stopSimulation();
		}
	}

}
