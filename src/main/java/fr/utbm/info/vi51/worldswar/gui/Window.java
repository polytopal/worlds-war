package fr.utbm.info.vi51.worldswar.gui;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import fr.utbm.info.vi51.worldswar.controller.Controller;
import fr.utbm.info.vi51.worldswar.simulator.SimulationSpeed;
import fr.utbm.info.vi51.worldswar.simulator.SimulatorListener;

/**
 * @author Leo
 * 
 *         Application main window
 */
public class Window extends JFrame implements SimulatorListener {
	private static final long serialVersionUID = 3509021382819712013L;
<<<<<<< HEAD
<<<<<<< HEAD
	
	private GuiActionsManager guiActionsManager;
	
	private float stepsPerSecond;
	private long lastStepStart;
	
=======

	private final Controller controller;

=======
	
	private Controller controller;
	
>>>>>>> 3b4934e2f7ec323200354155d423a594256842a2
	private float stepsPerSecond;
	private long lastStepStart;

>>>>>>> 249c5b199d6ff2d49dce818b2a035e04c26ca549
	/**
	 * @param args
	 *            Temporary main method
	 */
	public static void main(String[] args) {
		final Window window = new Window(null);
		window.setVisible(true);
	}

	
	/**
	 * @param controller
	 * 
	 *            a Java class that can emit SARL evenments to the simulation
	 */
	public Window(final Controller controller) {

		// Controller initialization
<<<<<<< HEAD
<<<<<<< HEAD
		
		this.stepsPerSecond = 0;
		this.lastStepStart = System.currentTimeMillis();
		
		this.guiActionsManager = new GuiActionsManager(controller);
		
=======

		this.controller = controller;
		this.stepsPerSecond = 0;
		this.lastStepStart = System.currentTimeMillis();

>>>>>>> 249c5b199d6ff2d49dce818b2a035e04c26ca549
=======
		
		this.controller = controller;
		this.stepsPerSecond = 0;
		this.lastStepStart = System.currentTimeMillis();
		
>>>>>>> 3b4934e2f7ec323200354155d423a594256842a2
		// Window initialization

		this.setTitle(Messages.getString("Window.title")); //$NON-NLS-1$
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

		this.setJMenuBar(new MenuBar(this.guiActionsManager));

		this.getContentPane().add(new GridViewPanel(this));

		this.setLocationRelativeTo(null);

		this.pack();
		this.setVisible(true);
		System.out.println("GUI created"); //$NON-NLS-1$
	}
	
	@Override
	public void simulationStepFired() {
		long currentTime = System.currentTimeMillis();
		long stepDuration = currentTime - this.lastStepStart;
		this.lastStepStart = currentTime;
		this.stepsPerSecond = 1000.f / stepDuration;
		System.out.println(Messages.getString("Window.stepFiredMsg") + this.stepsPerSecond); //$NON-NLS-1$
	}

}
