package fr.utbm.info.vi51.worldswar.gui;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

/**
 * @author Leo
 * 
 *         Application main window
 */
public class Window extends JFrame {
	private static final long serialVersionUID = 3509021382819712013L;

	/**
	 * @param args
	 *            Temporary main method
	 */
	public static void main(String[] args) {
		final Window window = new Window(null);
		window.setVisible(true);
	}

	/**
	 * @param SimulatorController
	 * 
	 *            The SARL agent that control the simulation
	 */
	public Window(final Object SimulatorController) {

		this.setTitle(Messages.getString("Window.0")); //$NON-NLS-1$
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

		this.setJMenuBar(new MenuBar());

		this.getContentPane().add(new GridPanel(SimulatorController, this));

		this.setLocationRelativeTo(null);

		this.pack();

	}

}
