package fr.utbm.info.vi51.worldswar.gui;

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
 *         Swing panel that show the environment grid. It doesn't show the all
 *         grid but it contains a viewport that show a part of this grid.
 */
public class GridViewPanel extends JPanel {
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
	public GridViewPanel(JFrame window) {

		// TODO LR - cet algo ne ce trouvera pas ici, on pourra reconstruire la
		// grille de la GUI si on reconstruit un nouvel environnement

		final JPanel matrixPanel = new JPanel() {
			private static final long serialVersionUID = 7038441700106081363L;

			@Override
			public void paintComponent(Graphics g) {
				this.setPreferredSize(new Dimension(GridViewPanel.this.cellSize * W, GridViewPanel.this.cellSize * H));

				g.setColor(Color.WHITE); // background color
				// show background
				g.fillRect(0, 0, this.getWidth(), this.getHeight());

				for (int y = 0; y < H; y++) {
					for (int x = 0; x < W; x++) {
						g.setColor(GridViewPanel.this.panelTable.get(y).get(x).getColor());
						g.fillRect(x * GridViewPanel.this.cellSize, y * GridViewPanel.this.cellSize,
								GridViewPanel.this.cellSize, GridViewPanel.this.cellSize);
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
					list.add(CellType.EMPTY);
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
				case KeyEvent.VK_LEFT: // left arrow
					moveCameraLeft();
					break;
				case KeyEvent.VK_UP:// up arrow
					moveCameraUp();
					break;
				case KeyEvent.VK_RIGHT:// right arrow
					moveCameraRight();
					break;
				case KeyEvent.VK_DOWN:// down arrow
					moveCameraDown();
					break;
				case KeyEvent.VK_ADD:// + button
					zoomIn();
					break;
				case KeyEvent.VK_SUBTRACT:// - button
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
		if (GridViewPanel.this.cellSize < CELL_SIZE_MAX) {
			GridViewPanel.this.cellSize++;
			GridViewPanel.this.repaint();
		}
	}

	private void zoomOut() {
		if (GridViewPanel.this.cellSize > CELL_SIZE_MIN) {
			GridViewPanel.this.cellSize--;
			GridViewPanel.this.repaint();
		}
	}

	private void moveCameraLeft() {
		final Point currentPos = this.scrollPane.getViewport().getViewPosition();
		if (currentPos.getX() > CAMERA_MOVE_SPEED) {
			currentPos.setLocation(currentPos.getX() - CAMERA_MOVE_SPEED, currentPos.getY());
			this.scrollPane.getViewport().setViewPosition(currentPos);
		}
	}

	private void moveCameraUp() {
		final Point currentPos = this.scrollPane.getViewport().getViewPosition();
		if (currentPos.getY() > CAMERA_MOVE_SPEED) {
			currentPos.setLocation(currentPos.getX(), currentPos.getY() - CAMERA_MOVE_SPEED);
			GridViewPanel.this.scrollPane.getViewport().setViewPosition(currentPos);
		}
	}

	private void moveCameraRight() {
		final Point currentPos = this.scrollPane.getViewport().getViewPosition();
		currentPos.setLocation(currentPos.getX() + CAMERA_MOVE_SPEED, currentPos.getY());
		GridViewPanel.this.scrollPane.getViewport().setViewPosition(currentPos);
	}

	private void moveCameraDown() {
		final Point currentPos = this.scrollPane.getViewport().getViewPosition();
		currentPos.setLocation(currentPos.getX(), currentPos.getY() + CAMERA_MOVE_SPEED);
		GridViewPanel.this.scrollPane.getViewport().setViewPosition(currentPos);
	}

}
