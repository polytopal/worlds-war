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

	/**
	 * The constructor of the menu bar
	 */
	public MenuBar(GuiActionsManager guiActionsManager) {

		// -------- simulation menu --------

		final JMenu simulationMenu = new JMenu(Messages.getString("MenuBar.simulation")); //$NON-NLS-1$
		this.add(simulationMenu);

		final JMenuItem newSimulationItem = new JMenuItem(guiActionsManager.getNewSimulationAction());
		simulationMenu.add(newSimulationItem);

		final JMenuItem stopSimulationItem = new JMenuItem(guiActionsManager.getStopSimulationAction());
		simulationMenu.add(stopSimulationItem);

		simulationMenu.addSeparator();

		// -------- speed menu --------

		final JMenu speedMenu = new JMenu("Speed");
		this.add(speedMenu);

		final ButtonGroup speedButtonsGroup = new ButtonGroup();
		final JRadioButtonMenuItem slowSpeed = new JRadioButtonMenuItem(guiActionsManager.getSlowSpeedAction());
		final JRadioButtonMenuItem normalSpeed = new JRadioButtonMenuItem(guiActionsManager.getNormalSpeedAction());
		final JRadioButtonMenuItem fastSpeed = new JRadioButtonMenuItem(guiActionsManager.getFastSpeedAction());
		final JRadioButtonMenuItem maxSpeed = new JRadioButtonMenuItem(guiActionsManager.getMaxSpeedAction());

		speedButtonsGroup.add(slowSpeed);
		speedButtonsGroup.add(normalSpeed);
		speedButtonsGroup.add(fastSpeed);
		speedButtonsGroup.add(maxSpeed);

		assert (SimulationSpeed.getDefaultSpeed() == SimulationSpeed.NORMAL);
		normalSpeed.setSelected(true);

		speedMenu.add(slowSpeed);
		speedMenu.add(normalSpeed);
		speedMenu.add(fastSpeed);
		speedMenu.add(maxSpeed);

	}
}
