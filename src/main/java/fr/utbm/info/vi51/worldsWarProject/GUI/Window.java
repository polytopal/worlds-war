package fr.utbm.info.vi51.worldsWarProject.GUI;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

/**
 * @author Leo Fenetre principale de l'application
 */
public class Window extends JFrame {
	private static final long serialVersionUID = 3509021382819712013L;

	/**
	 * @param args
	 *            Methode main temporaire
	 */
	public static void main(String[] args) {
		final Window window = new Window(null);
		window.setVisible(true);
	}

	/**
	 * @param SimulatorController
	 *            l'agent SARL qui controlle la simulation
	 */
	public Window(final Object SimulatorController) {

		this.setTitle(Messages.getString("Window0")); //$NON-NLS-1$
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

		this.setJMenuBar(new MenuBar());

		this.getContentPane().add(new GridPanel(SimulatorController));

		// this.setPreferredSize(new Dimension(400, 400));
		this.setLocationRelativeTo(null);

		this.pack();
	}

}
