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

import fr.utbm.info.vi51.worldswar.environment.PerceptionGrid;

/**
 * @author Leo
 * 
 *         Swing panel that show the environment grid. It doesn't show the all
 *         grid but it contains a viewport that show a part of this grid.
 */
public class CentralPanel extends JPanel {
	private static final long serialVersionUID = -8443885607526578507L;

	private static final int CAMERA_MOVE_SPEED = 10;
	private static final int CELL_SIZE_MIN = 2;
	private static final int CELL_SIZE_MAX = 20;

	private int width;
	private int height;

	private int cellSize = 8;

	private final JScrollPane scrollPane;
	private final JPanel gridPanel;

	private final List<List<Color>> panelTable;

	/**
	 * @param SimulatorController
	 * 
	 *            The SARL agent that control the simulation
	 * 
	 * @param window
	 * 
	 *            The application window, used for the key listener
	 */
	public CentralPanel(JFrame window) {

		this.gridPanel = new JPanel() {
			private static final long serialVersionUID = 7038441700106081363L;

			@Override
			public void paintComponent(Graphics g) {
				this.setPreferredSize(new Dimension(CentralPanel.this.cellSize * CentralPanel.this.height,
						CentralPanel.this.cellSize * CentralPanel.this.width));

				g.setColor(Color.WHITE);
				g.fillRect(0, 0, this.getWidth(), this.getHeight());
				synchronized (CentralPanel.this.panelTable) {
					for (int x = 0; x < CentralPanel.this.width; x++) {
						for (int y = 0; y < CentralPanel.this.height; y++) {
							g.setColor(CentralPanel.this.panelTable.get(x).get(y));
							g.fillRect(x * CentralPanel.this.cellSize, y * CentralPanel.this.cellSize,
									CentralPanel.this.cellSize, CentralPanel.this.cellSize);
						}
					}
				}
			}
		};

		this.panelTable = new ArrayList<>(0);
		this.width = 0;
		this.height = 0;

		this.scrollPane = new JScrollPane(this.gridPanel);
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
		this.gridPanel.addMouseWheelListener(mouseWheelListener);

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

	public void updateGrid(PerceptionGrid perceptionGrid) {
		synchronized (CentralPanel.this.panelTable) {
			if (this.width != perceptionGrid.getWidth() || this.height != perceptionGrid.getHeight()) {
				resizeGrid(perceptionGrid.getWidth(), perceptionGrid.getHeight());
			}
			for (int x = 0; x < this.width; x++) {
				for (int y = 0; y < this.height; y++) {
					Color cellColor = Utils.computeCellColor(perceptionGrid.getCell(x, y));
					if (Math.random() > 0.8) {
						cellColor = Color.CYAN;
					}
					this.panelTable.get(x).set(y, cellColor);
				}
			}
		}
		this.gridPanel.repaint();
	}

	/**
	 * Methods used only when a new environment with a different size of the
	 * older environment
	 */
	private void resizeGrid(int w, int h) {
		this.width = w;
		this.height = h;
		this.panelTable.clear();
		for (int x = 0; x < this.width; x++) {
			final ArrayList<Color> column = new ArrayList<>(h);
			for (int y = 0; y < this.height; y++) {
				column.add(Color.WHITE);
			}
			this.panelTable.add(column);
		}
	}

	private void zoomIn() {
		if (CentralPanel.this.cellSize < CELL_SIZE_MAX) {
			CentralPanel.this.cellSize++;
			CentralPanel.this.repaint();
		}
	}

	private void zoomOut() {
		if (CentralPanel.this.cellSize > CELL_SIZE_MIN) {
			CentralPanel.this.cellSize--;
			CentralPanel.this.repaint();
		}
	}

	private void moveCameraLeft() {
		final Point viewPosition = this.scrollPane.getViewport().getViewPosition();
		if (viewPosition.getX() > CAMERA_MOVE_SPEED) {
			viewPosition.setLocation(viewPosition.getX() - CAMERA_MOVE_SPEED, viewPosition.getY());
			this.scrollPane.getViewport().setViewPosition(viewPosition);
		}
	}

	private void moveCameraUp() {
		final Point viewPosition = this.scrollPane.getViewport().getViewPosition();
		if (viewPosition.getY() > CAMERA_MOVE_SPEED) {
			viewPosition.setLocation(viewPosition.getX(), viewPosition.getY() - CAMERA_MOVE_SPEED);
			CentralPanel.this.scrollPane.getViewport().setViewPosition(viewPosition);
		}
	}

	private void moveCameraRight() {
		final Point viewPosition = this.scrollPane.getViewport().getViewPosition();
		viewPosition.setLocation(viewPosition.getX() + CAMERA_MOVE_SPEED, viewPosition.getY());
		CentralPanel.this.scrollPane.getViewport().setViewPosition(viewPosition);
	}

	private void moveCameraDown() {
		final Point viewPosition = this.scrollPane.getViewport().getViewPosition();
		viewPosition.setLocation(viewPosition.getX(), viewPosition.getY() + CAMERA_MOVE_SPEED);
		CentralPanel.this.scrollPane.getViewport().setViewPosition(viewPosition);
	}

}
