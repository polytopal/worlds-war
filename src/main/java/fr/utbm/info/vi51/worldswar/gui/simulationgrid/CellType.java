package fr.utbm.info.vi51.worldswar.gui.simulationgrid;

import java.awt.Color;

/**
 * @author Leo
 * 
 *         The different kind of GUI cells
 */
public enum CellType {
	/**
	 * A empty cell, doesn't contains anything
	 */
	EMPTY {
		@Override
		public Color getColor() {
			return Color.WHITE;
		}
	},
	/**
	 * The ant cell
	 */
	ANT {
		@Override
		public Color getColor() {
			return Color.BLACK;
		}
	},
	/**
	 * The food cell
	 */
	FOOD {
		@Override
		public Color getColor() {
			return Color.GREEN;
		}
	},
	/**
	 * The ant hill
	 */
	ANT_HILL {
		@Override
		public Color getColor() {
			return Color.RED;
		}
	};

	/**
	 * @return the cell Color
	 */
	public abstract Color getColor();
}
