package fr.utbm.info.vi51.worldswar.gui;

import java.awt.Event;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

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

	private final Action slowSpeedAction;
	private final Action normalSpeedAction;
	private final Action fastSpeedAction;
	private final Action maxSpeedAction;

	GuiActionsManager(final Controller controller) {
		this.newSimulationAction = new NewSimulationAction(controller);
		this.stopSimulationAction = new StopSimulationAction(controller);

		this.slowSpeedAction = new SetSpeedAction(controller, SimulationSpeed.SLOW);
		this.normalSpeedAction = new SetSpeedAction(controller, SimulationSpeed.NORMAL);
		this.fastSpeedAction = new SetSpeedAction(controller, SimulationSpeed.FAST);
		this.maxSpeedAction = new SetSpeedAction(controller, SimulationSpeed.MAX);
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

	public Action getSlowSpeedAction() {
		return this.slowSpeedAction;
	}

	public Action getNormalSpeedAction() {
		return this.normalSpeedAction;
	}

	public Action getFastSpeedAction() {
		return this.fastSpeedAction;
	}

	public Action getMaxSpeedAction() {
		return this.maxSpeedAction;
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

	private class SetSpeedAction extends AbstractAction {
		private static final long serialVersionUID = -3918672044655803199L;

		private final Controller controller;
		private final SimulationSpeed speed;

		public SetSpeedAction(final Controller controller, SimulationSpeed speed) {
			super(speed.getPropertyKey());
			this.speed = speed;
			this.controller = controller;
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			this.controller.setSimulationSpeed(this.speed);
		}

	}

}
