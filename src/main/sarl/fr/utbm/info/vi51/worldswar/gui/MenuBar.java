package fr.utbm.info.vi51.worldswar.gui;

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
	public MenuBar(GuiActionsManager guiActionsManager) {

		final JMenu simulationMenu = new JMenu(Messages.getString("MenuBar.simulation")); //$NON-NLS-1$
		this.add(simulationMenu);

		final JMenuItem newSimulationItem = new JMenuItem(guiActionsManager.getNewSimulationAction());
		simulationMenu.add(newSimulationItem);

		final JMenuItem stopSimulationItem = new JMenuItem(guiActionsManager.getStopSimulationAction());
		simulationMenu.add(stopSimulationItem);

	}
}
