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

	private final JScrollPane scrollPane;

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
				this.setPreferredSize(new Dimension(GridPanel.this.cellSize * W, GridPanel.this.cellSize * H));

				g.setColor(Color.WHITE); // background color
				// show background
				g.fillRect(0, 0, this.getWidth(), this.getHeight());

				for (int y = 0; y < H; y++) {
					for (int x = 0; x < W; x++) {
						g.setColor(GridPanel.this.panelTable.get(y).get(x).getColor());
						g.fillRect(x * GridPanel.this.cellSize, y * GridPanel.this.cellSize, GridPanel.this.cellSize,
								GridPanel.this.cellSize);
					}
				}

			}
		};

		// random grid generation
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
					list.add(CellType.VOID);
				}
			}
			this.panelTable.add(list);
		}

		this.scrollPane = new JScrollPane(matrixPanel);
		this.scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		this.scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		this.setLayout(new BorderLayout());

		this.add(this.scrollPane, BorderLayout.CENTER);

		this.setPreferredSize(new Dimension(400, 400));

		final MouseWheelListener mouseWheelListener = new MouseWheelListener() {
			@Override
			public void mouseWheelMoved(MouseWheelEvent e) {
				if (e.getPreciseWheelRotation() > 0) {
					zoomOut();
				} else {
					zoomIn();
				}
			}
		};
		matrixPanel.addMouseWheelListener(mouseWheelListener);

		final KeyListener keyListener = new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {

				switch (e.getExtendedKeyCode()) {
				case KeyEvent.VK_LEFT:
					moveLeft();
					break;
				case KeyEvent.VK_UP:
					moveUp();
					break;
				case KeyEvent.VK_RIGHT:
					moveRight();
					break;
				case KeyEvent.VK_DOWN:
					moveDown();
					break;
				case 107:
					zoomIn();
					break;
				case 109:
					zoomOut();
					break;
				default:
					break;
				}

			}
		};
		window.addKeyListener(keyListener);

	}

	private void zoomIn() {
		if (GridPanel.this.cellSize < CELL_SIZE_MAX) {
			GridPanel.this.cellSize++;
			GridPanel.this.repaint();
		}
	}

	private void zoomOut() {
		if (GridPanel.this.cellSize > CELL_SIZE_MIN) {
			GridPanel.this.cellSize--;
			GridPanel.this.repaint();
		}
	}

	private void moveLeft() {
		final Point currentPos = this.scrollPane.getViewport().getViewPosition();
		if (currentPos.getX() > CAMERA_MOVE_SPEED) {
			currentPos.setLocation(currentPos.getX() - CAMERA_MOVE_SPEED, currentPos.getY());
			this.scrollPane.getViewport().setViewPosition(currentPos);
		}
	}

	private void moveUp() {
		final Point currentPos = this.scrollPane.getViewport().getViewPosition();
		if (currentPos.getY() > CAMERA_MOVE_SPEED) {
			currentPos.setLocation(currentPos.getX(), currentPos.getY() - CAMERA_MOVE_SPEED);
			GridPanel.this.scrollPane.getViewport().setViewPosition(currentPos);
		}
	}

	private void moveRight() {
		final Point currentPos = this.scrollPane.getViewport().getViewPosition();
		currentPos.setLocation(currentPos.getX() + CAMERA_MOVE_SPEED, currentPos.getY());
		GridPanel.this.scrollPane.getViewport().setViewPosition(currentPos);
	}

	private void moveDown() {
		final Point currentPos = this.scrollPane.getViewport().getViewPosition();

		currentPos.setLocation(currentPos.getX(), currentPos.getY() + CAMERA_MOVE_SPEED);
		GridPanel.this.scrollPane.getViewport().setViewPosition(currentPos);
	}

}
