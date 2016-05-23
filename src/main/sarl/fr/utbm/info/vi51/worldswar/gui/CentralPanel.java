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

import fr.utbm.info.vi51.worldswar.environment.PheromoneType;
import fr.utbm.info.vi51.worldswar.gui.layer.AntLayer;
import fr.utbm.info.vi51.worldswar.gui.layer.GuiLayer;
import fr.utbm.info.vi51.worldswar.gui.layer.MapLayer;
import fr.utbm.info.vi51.worldswar.perception.PerceptionGrid;

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
	private static final int CELL_SIZE_MAX = 30;

	private int width;
	private int height;

	private int cellSize = 8;

	// TODO remove
	// private final PheromoneType pheromoneFilter;

	private final JScrollPane scrollPane;
	private final JPanel gridPanel;

	private final List<GuiLayer> layers;

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

				g.setColor(Color.LIGHT_GRAY);
				g.fillRect(0, 0, this.getWidth(), this.getHeight());

				for (final GuiLayer layer : CentralPanel.this.layers) {
					if (layer.isEnabled()) {
						layer.paintLayer(g, CentralPanel.this.cellSize);
					}
				}
			}
		};

		this.layers = new ArrayList<>();

		// !! the order is important
		this.layers.add(new MapLayer());
		this.layers.add(new AntLayer());

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
					moveCameraLeft(CAMERA_MOVE_SPEED);
					break;
				case KeyEvent.VK_UP:// up arrow
					moveCameraUp(CAMERA_MOVE_SPEED);
					break;
				case KeyEvent.VK_RIGHT:// right arrow
					moveCameraRight(CAMERA_MOVE_SPEED);
					break;
				case KeyEvent.VK_DOWN:// down arrow
					moveCameraDown(CAMERA_MOVE_SPEED);
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

		this.width = perceptionGrid.getWidth();
		this.height = perceptionGrid.getHeight();

		for (final GuiLayer guiLayer : this.layers) {
			guiLayer.update(perceptionGrid);
		}

		this.gridPanel.repaint();
	}

	public void setPheromoneFilter(PheromoneType pheromoneType) {
		// TODO - modify this method
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

	private void moveCameraLeft(int pixelGap) {
		final Point viewPosition = this.scrollPane.getViewport().getViewPosition();
		final double cameraX = viewPosition.getX();
		if (cameraX > 0) {
			if (cameraX > pixelGap) {
				viewPosition.setLocation(cameraX - pixelGap, viewPosition.getY());
			} else {
				viewPosition.setLocation(0, viewPosition.getY());
			}
			this.scrollPane.getViewport().setViewPosition(viewPosition);
		}
	}

	private void moveCameraUp(int pixelGap) {
		final Point viewPosition = this.scrollPane.getViewport().getViewPosition();
		final double cameraY = viewPosition.getY();
		if (cameraY > 0) {
			if (cameraY > pixelGap) {
				viewPosition.setLocation(viewPosition.getX(), cameraY - pixelGap);
			} else {
				viewPosition.setLocation(viewPosition.getX(), 0);
			}
			CentralPanel.this.scrollPane.getViewport().setViewPosition(viewPosition);
		}
	}

	private void moveCameraRight(int pixelGap) {
		final Point viewPosition = this.scrollPane.getViewport().getViewPosition();
		viewPosition.setLocation(viewPosition.getX() + pixelGap, viewPosition.getY());
		CentralPanel.this.scrollPane.getViewport().setViewPosition(viewPosition);
	}

	private void moveCameraDown(int pixelGap) {
		final Point viewPosition = this.scrollPane.getViewport().getViewPosition();
		viewPosition.setLocation(viewPosition.getX(), viewPosition.getY() + pixelGap);
		CentralPanel.this.scrollPane.getViewport().setViewPosition(viewPosition);
	}

}
