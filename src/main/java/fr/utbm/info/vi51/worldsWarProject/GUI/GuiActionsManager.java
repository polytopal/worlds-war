package fr.utbm.info.vi51.worldsWarProject.GUI;

import java.awt.Event;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.KeyStroke;

/**
 * @author Leo
 *
 *         The GUI action manager is a Singleton. It create all the actions
 *         available from the GUI at the start of the program.
 */
public class GuiActionsManager {

	private static GuiActionsManager INSTANCE = new GuiActionsManager();

	private final Action newSimulationAction;
	private final Action stopSimulationAction;

	// private final Action zoomAction;
	// private final Action dezoomAction;

	private GuiActionsManager() {
		this.newSimulationAction = new NewSimulationAction();
		this.stopSimulationAction = new StopSimulationAction();
		// this.zoomAction = new ZoomAction();
		// this.dezoomAction = new DeZoomAction();
	}

	/**
	 * @return the unique instance of the manager
	 */
	public static GuiActionsManager getInstance() {
		return INSTANCE;
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

	// /**
	// * @return the action to zoom on the simulation grid
	// */
	// public Action getZoomAction() {
	// return this.zoomAction;
	// }
	//
	// /**
	// * @return the action to dezoom on the simulation grid
	// */
	// public Action getDeZoomAction() {
	// return this.dezoomAction;
	// }

	/**
	 * @author Leo
	 * 
	 *         This action allow to start a new simulation
	 */
	private class NewSimulationAction extends AbstractAction {
		private static final long serialVersionUID = 4958436812700297538L;

		/**
		 * New Simulation Action constructor
		 */
		public NewSimulationAction() {
			super(Messages.getString("MenuBar.1")); //$NON-NLS-1$
			putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_N, Event.CTRL_MASK));
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO LR - implementer lancement nouvelle simulation
			System.out.println("lancement nouvelle simulation"); //$NON-NLS-1$
		}
	}

	/**
	 * @author Leo
	 * 
	 *         This action allow to stop the current simulation
	 */
	private class StopSimulationAction extends AbstractAction {
		private static final long serialVersionUID = 2286692516797367038L;

		/**
		 * Stop Simulation Action constructor
		 */
		public StopSimulationAction() {
			super(Messages.getString("MenuBar.2")); //$NON-NLS-1$
			putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_S, Event.CTRL_MASK));
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO LR - implementer arret simulation
			System.out.println("arret simulation"); //$NON-NLS-1$
		}
	}

	/**
	 * @author Leo
	 * 
	 *         This action allow to zoom on the simulation grid
	 */
	// private class ZoomAction extends AbstractAction {
	// private static final long serialVersionUID = -5437005864722461320L;
	//
	// /**
	// * Stop Simulation Action constructor
	// */
	// public ZoomAction() {
	// super(Messages.getString(Messages.getString("MenuBar.3"))); //$NON-NLS-1$
	// putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_PLUS,
	// Event.CTRL_MASK));
	// }
	//
	// @Override
	// public void actionPerformed(ActionEvent e) {
	// // TODO LR - implementer arret simulation
	// System.out.println("zoom"); //$NON-NLS-1$
	// }
	// }

	/**
	 * @author Leo
	 * 
	 *         This action allow to dezoom on the simulation grid
	 */
	// private class DeZoomAction extends AbstractAction {
	// private static final long serialVersionUID = -5437005864722461320L;
	//
	// /**
	// * Stop Simulation Action constructor
	// */
	// public DeZoomAction() {
	// super(Messages.getString(Messages.getString("MenuBar.4"))); //$NON-NLS-1$
	// putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_MINUS,
	// Event.CTRL_MASK));
	// }
	//
	// @Override
	// public void actionPerformed(ActionEvent e) {
	// // TODO LR - implementer arret simulation
	// System.out.println("dezoom"); //$NON-NLS-1$
	// }
	// }

}
