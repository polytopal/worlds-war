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
	private final Action coloredAntLayerAction;

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
		this.coloredAntLayerAction = new ColoredAntLayerAction(centralPanel);
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
	 * @return the action corresponding to pausing the simulation
	 */
	public Action getPauseSimulationAction() {
		return this.pauseSimulationAction;
	}

	/**
	 * @return the action corresponding to resuming the simulation after it was paused
	 */
	public Action getResumeSimulationAction() {
		return this.resumeSimulationAction;
	}

	/**
	 * @return the action corresponding to executing the next simulation step.
	 * Only usable when the simulation is paused.
	 */
	public Action getStepSimulationAction() {
		return this.stepSimulationAction;
	}
	
	/**
	 * @return the action corresponding to displaying a popup showing informations
	 * related to the map
	 */
	public Action getMapInfoAction() {
		return this.mapInfoAction;
	}
	/**
	 * This method update the map information needed for 
	 * display of the MapInfoDialog in MapInfoAction
	 * @param mapInfo informations related to the simulation map
	 */
	
	public void updateMapInfo(MapInformation mapInfo) {
		this.mapInfoAction.setMapInfo(mapInfo);
	}

	/**
	 * @param simSpeed
	 * @return the action corresponding to setting the simulation to a certain speed
	 */
	public Action getSpeedAction(SimulationSpeed simSpeed) {
		return this.speedActionsMap.get(simSpeed);
	}
	
	/**
	 * @param pheromoneType
	 * @return the action corresponding to activating or deactivating a pheromone
	 * of a certain type
	 */
	public Action getPheromoneFilterActions(PheromoneType pheromoneType) {
		return this.pheromoneFilterActionsMap.get(pheromoneType);
	}

	/**
	 * @return the action corresponding to activating or deactivating the debug filter
	 */
	public Action getDebugFilterAction() {
		return this.debugFilterAction;
	}

	public Action getColoredAntLayerAction() {
		return this.coloredAntLayerAction;
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
	 * This action allows to display information related to the simulation map
	 */
	private class MapInfoAction extends AbstractAction {
		private static final long serialVersionUID = 1818051679709294284L;
		private MapInformation mapInfo;

		public MapInfoAction() {
			super(Messages.getString("MenuBar.mapInfo")); //$NON-NLS-1$
			putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_I, 0));
			this.setEnabled(false);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			final MapInfoDialog mapInfoDialog = new MapInfoDialog(this.mapInfo);
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

	private class ColoredAntLayerAction extends AbstractAction {
		private static final long serialVersionUID = -9163474662975136163L;

		private final CentralPanel centralPanel;

		public ColoredAntLayerAction(CentralPanel centralPanel) {
			super(Messages.getString("MenuBar.coloredAnts")); //$NON-NLS-1$
			this.centralPanel = centralPanel;
		}

		@Override
		public void actionPerformed(ActionEvent ae) {
			if (ae.getSource() instanceof JCheckBoxMenuItem) {
				final boolean selected = ((JCheckBoxMenuItem) ae.getSource()).isSelected();
				this.centralPanel.setColoredAntLayer(selected);
			}
		}

	}

}
