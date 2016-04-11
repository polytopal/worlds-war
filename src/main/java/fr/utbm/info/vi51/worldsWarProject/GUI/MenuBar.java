package fr.utbm.info.vi51.worldsWarProject.GUI;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 * @author Leo
 * 
 *         The Menu Bar of the application
 */
public class MenuBar extends JMenuBar {
	private static final long serialVersionUID = -4720732790008718078L;

	/**
	 * The constructor of the menu bar
	 */
	public MenuBar() {

		final GuiActionsManager guiActionManager = GuiActionsManager.getInstance();

		final JMenu simulationMenu = new JMenu(Messages.getString("MenuBar.0")); //$NON-NLS-1$
		this.add(simulationMenu);

		final JMenuItem newSimulationItem = new JMenuItem(guiActionManager.getNewSimulationAction());
		simulationMenu.add(newSimulationItem);

		final JMenuItem stopSimulationItem = new JMenuItem(guiActionManager.getStopSimulationAction());
		simulationMenu.add(stopSimulationItem);

	}
}
