package fr.utbm.info.vi51.worldswar.gui;

import javax.swing.ButtonGroup;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;

import fr.utbm.info.vi51.worldswar.simulator.SimulationSpeed;

/**
 * @author Leo
 * 
 *         The Menu Bar of the application
 */
public class MenuBar extends JMenuBar {
	private static final long serialVersionUID = -4720732790008718078L;

	public MenuBar(GuiActionsManager guiActionsManager) {

		// -------- simulation menu --------

		final JMenu simulationMenu = new JMenu(Messages.getString("MenuBar.simulation")); //$NON-NLS-1$
		this.add(simulationMenu);

		final JMenuItem newSimulationItem = new JMenuItem(guiActionsManager.getNewSimulationAction());
		simulationMenu.add(newSimulationItem);

		final JMenuItem stopSimulationItem = new JMenuItem(guiActionsManager.getStopSimulationAction());
		simulationMenu.add(stopSimulationItem);

		// -------- speed menu --------

		final JMenu speedMenu = new JMenu(Messages.getString("MenuBar.Speed")); //$NON-NLS-1$
		this.add(speedMenu);

		final ButtonGroup speedButtonsGroup = new ButtonGroup();
		for (final SimulationSpeed simSpeed : SimulationSpeed.values()) {
			final JRadioButtonMenuItem speedButton = new JRadioButtonMenuItem(
					guiActionsManager.getSpeedAction(simSpeed));
			speedButton.setSelected(simSpeed == SimulationSpeed.getInitialSpeed());
			speedButtonsGroup.add(speedButton);
			speedMenu.add(speedButton);
		}

	}
}
