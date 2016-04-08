package fr.utbm.info.vi51.worldsWarProject.GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

/**
 * @author Leo
 * 
 *         Panel Swing affichant la grille de l'environnement
 */
public class GridPanel extends JPanel {
	private static final long serialVersionUID = -8443885607526578507L;

	// TODO LR - to delete - sellement pour les tests de grille
	private static int W = 100;
	private static int H = 100;
	private static int CELL_SIZE = 2;
	private int counter = 0;

	private final List<List<CellPanel>> panelTable;

	/**
	 * @param SimulatorController
	 *            L'agent SARL qui gère l'environnement
	 */
	public GridPanel(Object SimulatorController) {

		// TODO LR - cet algo ne ce trouvera pas ici, on pourra reconstruire la
		// grille de la GUI si on reconstruit un nouvel environnement

		this.setLayout(new GridLayout(H, W));

		panelTable = new ArrayList<>(H);
		for (int y = 0; y < H; y++) {
			final ArrayList<CellPanel> list = new ArrayList<>(W);
			for (int x = 0; x < W; x++) {
				final CellPanel cellPanel = new CellPanel();
				cellPanel.setPreferredSize(new Dimension(CELL_SIZE, CELL_SIZE));

				cellPanel.setBackground(Color.GREEN);
				// if ((x + y) % 2 == 0) {
				// cellPanel.setBackground(Color.BLUE);
				// } else {
				// cellPanel.setBackground(Color.PINK);
				// }

				list.add(cellPanel);// ajoute à la liste
				this.add(cellPanel);// ajoute au layout
			}
			panelTable.add(list);
		}

		final Runnable runnable = new Runnable() {
			@Override
			public void run() {
				while (true) {
					GridPanel.this.updateGrid();
					try {
						Thread.sleep(100);
					} catch (final InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		};
		final Thread thread = new Thread(runnable);
		thread.start();

	}

	private void updateGrid() {
		System.out.println("update");
		for (int y = 0; y < H; y++) {
			for (int x = 0; x < W; x++) {
				final CellPanel cellPanel = panelTable.get(y).get(x);
				if ((x + y + counter) % 2 == 0) {
					cellPanel.setBackground(Color.BLUE);
				} else {
					cellPanel.setBackground(Color.GREEN);
				}
			}
		}
		counter++;
		this.repaint();
	}

}
