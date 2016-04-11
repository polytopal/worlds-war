package fr.utbm.info.vi51.worldsWarProject.GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

/**
 * @author Leo
 * 
 *         Swing panel that show the environment grid
 */
public class GridPanel extends JPanel {
	private static final long serialVersionUID = -8443885607526578507L;

	private static final int CAMERA_MOVE_SPEED = 10;
	private static final int CELL_SIZE_MIN = 3;
	private static final int CELL_SIZE_MAX = 20;

	// TODO LR - to delete
	private static final int W = 100;
	private static final int H = 100;

	private int cellSize = 8;

	private final List<List<CellType>> panelTable;

	/**
	 * @param SimulatorController
	 * 
	 *            The SARL agent that control the simulation
	 * 
	 * @param window
	 * 
	 *            The application window, used for the key listener
	 */
	public GridPanel(Object SimulatorController, JFrame window) {

		// TODO LR - cet algo ne ce trouvera pas ici, on pourra reconstruire la
		// grille de la GUI si on reconstruit un nouvel environnement

		final JPanel matrixPanel = new JPanel() {
			private static final long serialVersionUID = 7038441700106081363L;

			@Override
			public void paintComponent(Graphics g) {
				this.setPreferredSize(new Dimension(cellSize * W, cellSize * H));

				g.setColor(Color.WHITE); // background color
				g.fillRect(0, 0, this.getWidth(), this.getHeight());// show
																	// background

				for (int y = 0; y < H; y++) {
					for (int x = 0; x < W; x++) {
						g.setColor(panelTable.get(y).get(x).getColor());
						g.fillRect(x * cellSize, y * cellSize, cellSize, cellSize);
					}
				}

			}
		};

		this.panelTable = new ArrayList<>(H);
		for (int y = 0; y < H; y++) {
			final ArrayList<CellType> list = new ArrayList<>(W);
			for (int x = 0; x < W; x++) {
				final double random = Math.random();
				if (random < 0.1) {
					list.add(CellType.ANT);
				} else if (random < 0.2) {
					list.add(CellType.FOOD);
				} else if (random < 0.21) {
					list.add(CellType.ANT_HILL);
				} else {
					list.add(CellType.VOID);// ajoute à la liste
				}
			}
			this.panelTable.add(list);
		}

		matrixPanel.setPreferredSize(new Dimension(400, 400));

		final JScrollPane scrollPane = new JScrollPane(matrixPanel);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		this.setLayout(new BorderLayout());

		this.add(scrollPane, BorderLayout.CENTER);

		this.setPreferredSize(new Dimension(400, 400));

		final MouseWheelListener mouseWheelListener = new MouseWheelListener() {
			@Override
			public void mouseWheelMoved(MouseWheelEvent e) {
				if (e.getPreciseWheelRotation() > 0) {// dezoom
					if (GridPanel.this.cellSize > CELL_SIZE_MIN) {
						GridPanel.this.cellSize--;
					}
				} else {// zoom
					if (GridPanel.this.cellSize < CELL_SIZE_MAX) {
						GridPanel.this.cellSize++;
					}
				}
				GridPanel.this.repaint();
			}
		};
		matrixPanel.addMouseWheelListener(mouseWheelListener);

		final KeyListener keyListener = new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				final Point currentPos = scrollPane.getViewport().getViewPosition();
				if (e.getExtendedKeyCode() == KeyEvent.VK_LEFT && currentPos.getX() > CAMERA_MOVE_SPEED) {
					currentPos.setLocation(currentPos.getX() - CAMERA_MOVE_SPEED, currentPos.getY());
					scrollPane.getViewport().setViewPosition(currentPos);
				} else if (e.getExtendedKeyCode() == KeyEvent.VK_UP && currentPos.getY() > CAMERA_MOVE_SPEED) {
					currentPos.setLocation(currentPos.getX(), currentPos.getY() - CAMERA_MOVE_SPEED);
					scrollPane.getViewport().setViewPosition(currentPos);
				} else if (e.getExtendedKeyCode() == KeyEvent.VK_RIGHT) {
					currentPos.setLocation(currentPos.getX() + CAMERA_MOVE_SPEED, currentPos.getY());
					scrollPane.getViewport().setViewPosition(currentPos);
				} else if (e.getExtendedKeyCode() == KeyEvent.VK_DOWN) {
					currentPos.setLocation(currentPos.getX(), currentPos.getY() + CAMERA_MOVE_SPEED);
					scrollPane.getViewport().setViewPosition(currentPos);
				}
			}
		};
		window.addKeyListener(keyListener);

	}

}
