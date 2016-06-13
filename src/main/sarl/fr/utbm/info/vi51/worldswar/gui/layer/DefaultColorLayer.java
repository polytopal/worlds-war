package fr.utbm.info.vi51.worldswar.gui.layer;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import fr.utbm.info.vi51.worldswar.environment.MapInformation;
import fr.utbm.info.vi51.worldswar.perception.PerceptionCell;
import fr.utbm.info.vi51.worldswar.perception.PerceptionGrid;

/**
 * The DefaultColorLayer gathers the methods used by the layers that use colored
 * squares
 */
public abstract class DefaultColorLayer implements GuiLayer {

	protected int width;
	protected int height;
	protected final List<List<Color>> panelTable;
	protected boolean enabled;

	public DefaultColorLayer() {
		this.panelTable = new ArrayList<>(0);
		this.width = 0;
		this.height = 0;
		this.enabled = true;
	}

	@Override
	public void setEnabled(boolean b) {
		this.enabled = b;
	}

	@Override
	public boolean isEnabled() {
		return this.enabled;
	}

	@Override
	public void paintLayer(Graphics g, int cellSize) {

		for (int x = 0; x < this.width; x++) {
			for (int y = 0; y < this.height; y++) {
				final Color color = this.panelTable.get(x).get(y);
				if (color != null) {
					g.setColor(color);
					g.fillRect(x * cellSize, y * cellSize, cellSize, cellSize);
				}
			}
		}
	}

	@Override
	public void simulationStarted(MapInformation mapInfo) {
		// Do nothing
	}

	@Override
	public void update(PerceptionGrid perceptionGrid) {
		if (this.width != perceptionGrid.getWidth() || this.height != perceptionGrid.getHeight()) {
			resizeGrid(perceptionGrid.getWidth(), perceptionGrid.getHeight());
		}
		for (int x = 0; x < this.width; x++) {
			for (int y = 0; y < this.height; y++) {
				final Color cellColor = computeCellColor(perceptionGrid.getCell(x, y));
				this.panelTable.get(x).set(y, cellColor);
			}
		}
	}

	/**
	 * Methods used when a new environment is created with a different size from
	 * the previous
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

	/**
	 * Compute the color of a cell
	 * 
	 * @param cell
	 * @return the color of the cell
	 */
	protected abstract Color computeCellColor(PerceptionCell cell);
}
