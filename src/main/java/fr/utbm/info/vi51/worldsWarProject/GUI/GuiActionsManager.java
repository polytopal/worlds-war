package fr.utbm.info.vi51.worldsWarProject.GUI;

import java.awt.Event;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.KeyStroke;

/**
 * @author Leo
 *
 *         Le gestionnaire des actions de l'interface graphique C'est un
 *         singleton, il se contente de créer les actions au démarrage puis de
 *         les donner a qui les demandes
 */
public class GuiActionsManager {

	private static GuiActionsManager INSTANCE = new GuiActionsManager();

	private final Action newSimulationAction;

	private GuiActionsManager() {
		this.newSimulationAction = new NewSimulationAction();
	}

	/**
	 * @return l'instance unique du manager
	 */
	public static GuiActionsManager getInstance() {
		return INSTANCE;
	}

	/**
	 * @return l'action de lancer une nouvelle simulation
	 */
	public Action getNewSimulationAction() {
		return this.newSimulationAction;
	}

	/**
	 * @author Leo
	 * 
	 *         Action permettant de lancer une nouvelle simulation
	 */
	private class NewSimulationAction extends AbstractAction {
		private static final long serialVersionUID = 4958436812700297538L;

		/**
		 * Constructeur de l'action permettant de lancer une nouvelle simulation
		 */
		public NewSimulationAction() {
			super(Messages.getString("MenuBar0")); //$NON-NLS-1$
			putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_N, Event.CTRL_MASK));
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO LR - implementer lancement nouvelle simulation
			System.out.println("lancement nouvelle simulation"); //$NON-NLS-1$
		}
	}
}
