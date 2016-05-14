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
import fr.utbm.info.vi51.worldswar.environment.PheromoneType;
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
	private final Action resumeSimulationAction;
	private final Action stepSimulationAction;
	
	private final Map<SimulationSpeed, Action> speedActionsMap;

	private final Action noPheromoneFilterAction;
	private final Map<PheromoneType, Action> pheromoneFilterActionsMap;

	GuiActionsManager(final Controller controller, final CentralPanel centralPanel) {
		this.newSimulationAction = new NewSimulationAction(controller);
		this.stopSimulationAction = new StopSimulationAction(controller);
		this.pauseSimulationAction = new PauseSimulationAction(controller);
		this.resumeSimulationAction = new ResumeSimulationAction(controller);
		this.stepSimulationAction = new StepSimulationAction(controller);

		this.speedActionsMap = new HashMap<>();
		for (final SimulationSpeed simSpeed : SimulationSpeed.values()) {
			this.speedActionsMap.put(simSpeed, new SpeedSetterAction(controller, simSpeed));
		}

		this.noPheromoneFilterAction = new PheromoneFilterAction(centralPanel, null);
		this.pheromoneFilterActionsMap = new HashMap<>();
		for (final PheromoneType pheromoneType : PheromoneType.values()) {
			this.pheromoneFilterActionsMap.put(pheromoneType, new PheromoneFilterAction(centralPanel, pheromoneType));
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
	
	public Action getResumeSimulationAction() {
		return this.resumeSimulationAction;
	}

	public Action getStepSimulationAction() {
		return this.stepSimulationAction;
	}
	
	public Action getSpeedAction(SimulationSpeed simSpeed) {
		return this.speedActionsMap.get(simSpeed);
	}

	public Action getPheromoneFilterActions(PheromoneType pheromoneType) {
		if (pheromoneType == null) {
			return this.noPheromoneFilterAction;
		}
		return this.pheromoneFilterActionsMap.get(pheromoneType);
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
	 *         This action allows to pause the simulation
	 */
	private class PauseSimulationAction extends AbstractAction {
		private static final long serialVersionUID = 2286692516797367038L;
		private final Controller controller;

		public PauseSimulationAction(final Controller controller) {
			super(Messages.getString("MenuBar.pauseSimulation")); //$NON-NLS-1$
			putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_P, Event.CTRL_MASK));
			this.controller = controller;
			this.setEnabled(false);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			this.controller.pauseSimulation();
		}
	}
	/**
	 *         This action allows to resume the simulation
	 */
	private class ResumeSimulationAction extends AbstractAction {
		private static final long serialVersionUID = 2286692516797367038L;
		private final Controller controller;

		public ResumeSimulationAction(final Controller controller) {
			super(Messages.getString("MenuBar.resumeSimulation")); //$NON-NLS-1$
			putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_P, Event.CTRL_MASK));
			this.controller = controller;
			this.setEnabled(false);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			this.controller.resumeSimulation();
		}
	}
	
	/**
	 *         This action allows to execute a step in the simulation when it is paused
	 */
	private class StepSimulationAction extends AbstractAction {
		private static final long serialVersionUID = 2286692516797367038L;
		private final Controller controller;

		public StepSimulationAction(final Controller controller) {
			super(Messages.getString("MenuBar.stepSimulation")); //$NON-NLS-1$
			putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, Event.CTRL_MASK));
			this.controller = controller;
			this.setEnabled(false);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			this.controller.stepSimulation();
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

	private class PheromoneFilterAction extends AbstractAction {
		private static final long serialVersionUID = -8482432665855856415L;

		private final CentralPanel centralPanel;
		private final PheromoneType pheromoneType;

		public PheromoneFilterAction(CentralPanel centralPanel, PheromoneType pheromoneType) {
			super((pheromoneType != null) ? Messages.getString(pheromoneType.getPropertyKey())
					: Messages.getString("PheromoneType.noPheromone")); //$NON-NLS-1$
			this.centralPanel = centralPanel;
			this.pheromoneType = pheromoneType;
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			this.centralPanel.setPheromoneFilter(this.pheromoneType);
		}

	}

}
