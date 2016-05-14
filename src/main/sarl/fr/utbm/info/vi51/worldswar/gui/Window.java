package fr.utbm.info.vi51.worldswar.gui;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import fr.utbm.info.vi51.worldswar.controller.Controller;
import fr.utbm.info.vi51.worldswar.environment.PerceptionGrid;
import fr.utbm.info.vi51.worldswar.simulator.SimulatorListener;

/**
 * @author Leo
 * 
 *         Application main window
 */
public class Window extends JFrame implements SimulatorListener {
	private static final long serialVersionUID = 3509021382819712013L;

	private final GuiActionsManager guiActionsManager;

	private final InfoPanel infoPanel;

	private float stepsPerSecond;
	private long lastStepStart;
	private SimulationState simState;
	private int stepNumber;

	private final CentralPanel centralPanel;

	/**
	 * @param controller
	 * 
	 *            a Java class that can emit SARL events to the simulation
	 */
	public Window(final Controller controller) {

		// Controller initialization

		this.stepsPerSecond = 0;
		this.lastStepStart = System.currentTimeMillis();

		this.stepNumber = 0;
		this.simState = SimulationState.STOPPED;

		// Window initialization
		this.setTitle(Messages.getString("Window.title")); //$NON-NLS-1$
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent evt) {
				controller.stopProgram();
			}
		});

		this.getContentPane().setLayout(new BorderLayout());

		this.centralPanel = new CentralPanel(this);
		this.getContentPane().add(this.centralPanel, BorderLayout.CENTER);

		this.guiActionsManager = new GuiActionsManager(controller, this.centralPanel);

		this.setJMenuBar(new MenuBar(this.guiActionsManager));

		this.infoPanel = new InfoPanel();
		this.getContentPane().add(this.infoPanel, BorderLayout.SOUTH);

		this.setLocationRelativeTo(null);

		this.pack();
		this.setVisible(true);
		System.out.println("GUI created"); //$NON-NLS-1$
	}

	@Override
	public void simulationStepFired() {
		final long currentTime = System.currentTimeMillis();
		final long stepDuration = currentTime - this.lastStepStart;
		this.lastStepStart = currentTime;
		this.stepsPerSecond = 1000.f / stepDuration;

		this.infoPanel.setSimulationStateLabel(this.simState);
		this.infoPanel.setStepPerSecondLabel(this.stepsPerSecond);
		this.infoPanel.setStepNumberLabel(this.stepNumber);

		this.stepNumber++;

	}

	@Override
	public void environmentUpdated(PerceptionGrid perceptionGrid) {
		this.centralPanel.updateGrid(perceptionGrid);
	}

	@Override
	public void simulationTerminated() {

		this.simState = SimulationState.STOPPED;
		this.infoPanel.setSimulationStateLabel(this.simState);
		this.guiActionsManager.getPauseSimulationAction().setEnabled(false);
		this.guiActionsManager.getResumeSimulationAction().setEnabled(false);
	}

	@Override
	public void simulationStarted() {
		this.stepNumber = 0;
		this.simState = SimulationState.RUNNING;
		this.infoPanel.setSimulationStateLabel(this.simState);
		this.guiActionsManager.getPauseSimulationAction().setEnabled(true);
	}

	@Override
	public void simulationPaused() {
		this.simState = SimulationState.PAUSED;
		this.infoPanel.setSimulationStateLabel(this.simState);
		this.guiActionsManager.getResumeSimulationAction().setEnabled(true);
		this.guiActionsManager.getPauseSimulationAction().setEnabled(false);
		this.guiActionsManager.getStepSimulationAction().setEnabled(true);
	}
	
	@Override
	public void simulationResumed() {
		this.simState = SimulationState.RUNNING;
		this.infoPanel.setSimulationStateLabel(this.simState);
		this.guiActionsManager.getPauseSimulationAction().setEnabled(true);
		this.guiActionsManager.getResumeSimulationAction().setEnabled(false);
		this.guiActionsManager.getStepSimulationAction().setEnabled(false);
	}

}
