package fr.utbm.info.vi51.worldswar.gui.layer;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import fr.utbm.info.vi51.worldswar.perception.PerceptionCell;
import fr.utbm.info.vi51.worldswar.perception.PerceptionGrid;

public class DebugLayer implements GuiLayer {

	private static final Color FONT_COLOR = Color.ORANGE;
	private static final int CELL_SIZE_LIMIT = 20;
	protected int width;
	protected int height;
	protected final List<List<String>> panelTable;
	protected boolean enabled;

	public DebugLayer() {
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
	public void update(PerceptionGrid perceptionGrid) {
		if (this.width != perceptionGrid.getWidth() || this.height != perceptionGrid.getHeight()) {
			resizeGrid(perceptionGrid.getWidth(), perceptionGrid.getHeight());
		}
		for (int x = 0; x < this.width; x++) {
			for (int y = 0; y < this.height; y++) {
				final String s = computeString(perceptionGrid.getCell(x, y));
				this.panelTable.get(x).set(y, s);
			}
		}
	}

	private static String computeString(PerceptionCell cell) {
		if (cell.getAntHill() != null) {
			final int availableFood = cell.getAntHill().getAvailableFood();
			return String.format("f:%d", availableFood); //$NON-NLS-1$
		}
		if (cell.getAnt() != null) {
			final int foodCarried = cell.getAnt().getFoodCarried();
			return String.format("f:%d", foodCarried); //$NON-NLS-1$
		}

		return null;
	}

	@Override
	public void paintLayer(Graphics g, int cellSize) {
		if (cellSize > CELL_SIZE_LIMIT && this.enabled) {
			for (int x = 0; x < this.width; x++) {
				for (int y = 0; y < this.height; y++) {
					final String s = this.panelTable.get(x).get(y);
					if (s != null) {
						g.setColor(FONT_COLOR);
						g.drawString(s, x * cellSize, y * cellSize + cellSize);
					}
				}
			}
		}
	}

	private void resizeGrid(int w, int h) {
		this.width = w;
		this.height = h;
		this.panelTable.clear();
		for (int x = 0; x < this.width; x++) {
			final ArrayList<String> column = new ArrayList<>(h);
			for (int y = 0; y < this.height; y++) {
				column.add(null);
			}
			this.panelTable.add(column);
		}
	}

}
