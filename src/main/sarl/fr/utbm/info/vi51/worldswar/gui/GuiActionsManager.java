package fr.utbm.info.vi51.worldswar.gui;

import java.awt.Event;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.KeyStroke;

import fr.utbm.info.vi51.worldswar.controller.Controller;
import fr.utbm.info.vi51.worldswar.controller.SimulationParameters;
import fr.utbm.info.vi51.worldswar.environment.MapInformation;
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
	private final MapInfoAction mapInfoAction;

	private final Map<SimulationSpeed, Action> speedActionsMap;

	private final Map<PheromoneType, Action> pheromoneFilterActionsMap;

	private final Action debugFilterAction;

	GuiActionsManager(final Controller controller, final CentralPanel centralPanel) {
		this.newSimulationAction = new NewSimulationAction(controller);
		this.stopSimulationAction = new StopSimulationAction(controller);
		this.pauseSimulationAction = new PauseSimulationAction(controller);
		this.resumeSimulationAction = new ResumeSimulationAction(controller);
		this.stepSimulationAction = new StepSimulationAction(controller);
		this.mapInfoAction = new MapInfoAction();

		this.speedActionsMap = new HashMap<>();
		for (final SimulationSpeed simSpeed : SimulationSpeed.values()) {
			this.speedActionsMap.put(simSpeed, new SpeedSetterAction(controller, simSpeed));
		}

		this.pheromoneFilterActionsMap = new HashMap<>();
		for (final PheromoneType pheromoneType : PheromoneType.values()) {
			this.pheromoneFilterActionsMap.put(pheromoneType, new PheromoneFilterAction(centralPanel, pheromoneType));
		}

		this.debugFilterAction = new DebugFilterAction(centralPanel);
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
	
	public Action getMapInfoAction() {
		return this.mapInfoAction;
	}
	
	public void updateMapInfo(MapInformation mapInfo) {
		this.mapInfoAction.setMapInfo(mapInfo);
	}

	public Action getSpeedAction(SimulationSpeed simSpeed) {
		return this.speedActionsMap.get(simSpeed);
	}

	public Action getPheromoneFilterActions(PheromoneType pheromoneType) {
		return this.pheromoneFilterActionsMap.get(pheromoneType);
	}

	public Action getDebugFilterAction() {
		return this.debugFilterAction;
	}

	/**
	 * @author Leo
	 * 
	 *         This action allow to start a new simulation, by pressing ctrl+N
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
	 *         This action allow to stop the current simulation, by pressing
	 *         ctrl+C
	 */
	private class StopSimulationAction extends AbstractAction {
		private static final long serialVersionUID = 2286692516797367038L;
		private final Controller controller;

		public StopSimulationAction(final Controller controller) {
			super(Messages.getString("MenuBar.stopSimulation")); //$NON-NLS-1$
			putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_C, Event.CTRL_MASK));
			this.controller = controller;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			this.controller.stopSimulation();
		}
	}

	/**
	 * This action allows to pause the simulation, by pressing the key P
	 */
	private class PauseSimulationAction extends AbstractAction {
		private static final long serialVersionUID = 2286692516797367038L;
		private final Controller controller;

		public PauseSimulationAction(final Controller controller) {
			super(Messages.getString("MenuBar.pauseSimulation")); //$NON-NLS-1$
			putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_P, 0));
			this.controller = controller;
			this.setEnabled(false);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			this.controller.pauseSimulation();
		}
	}

	/**
	 * This action allows to resume the simulation, by pressing the key P
	 */
	private class ResumeSimulationAction extends AbstractAction {
		private static final long serialVersionUID = 2286692516797367038L;
		private final Controller controller;

		public ResumeSimulationAction(final Controller controller) {
			super(Messages.getString("MenuBar.resumeSimulation")); //$NON-NLS-1$
			putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_P, 0));
			this.controller = controller;
			this.setEnabled(false);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			this.controller.resumeSimulation();
		}
	}

	/**
	 * This action allows to execute a step in the simulation when it is paused,
	 * by pressing the space bar.
	 */
	private class StepSimulationAction extends AbstractAction {
		private static final long serialVersionUID = 2286692516797367038L;
		private final Controller controller;

		public StepSimulationAction(final Controller controller) {
			super(Messages.getString("MenuBar.stepSimulation")); //$NON-NLS-1$
			putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0));
			this.controller = controller;
			this.setEnabled(false);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			this.controller.stepSimulation();
		}
	}
	
	/**
	 * This action allows to execute a step in the simulation when it is paused,
	 * by pressing the space bar.
	 */
	private class MapInfoAction extends AbstractAction {
		private static final long serialVersionUID = 2286692516797367038L;
		
		private MapInformation mapInfo;

		public MapInfoAction() {
			super(Messages.getString("MenuBar.mapInfo")); //$NON-NLS-1$
			putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0));
			this.setEnabled(false);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			final MapInfoDialog mapInfoDialog = new MapInfoDialog(mapInfo);
		}
		
		public void setMapInfo(MapInformation mapInfo){
			this.mapInfo = mapInfo;
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
			if (speed.getKeyStroke() != null) {
				putValue(ACCELERATOR_KEY, speed.getKeyStroke());
			}
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
			super(Messages.getString(pheromoneType.getPropertyKey()));
			this.centralPanel = centralPanel;
			this.pheromoneType = pheromoneType;
		}

		@Override
		public void actionPerformed(ActionEvent ae) {
			if (ae.getSource() instanceof JCheckBoxMenuItem) {
				final boolean selected = ((JCheckBoxMenuItem) ae.getSource()).isSelected();
				this.centralPanel.setPheromoneFilterEnabled(this.pheromoneType, selected);
			}
		}

	}

	private class DebugFilterAction extends AbstractAction {
		private static final long serialVersionUID = 4726271442253782449L;

		private final CentralPanel centralPanel;

		public DebugFilterAction(CentralPanel centralPanel) {
			super(Messages.getString("MenuBar.debugFilter")); //$NON-NLS-1$
			this.centralPanel = centralPanel;
		}

		@Override
		public void actionPerformed(ActionEvent ae) {
			if (ae.getSource() instanceof JCheckBoxMenuItem) {
				final boolean selected = ((JCheckBoxMenuItem) ae.getSource()).isSelected();
				this.centralPanel.setDebugFilterEnabled(selected);
			}
		}

	}

}
