package fr.utbm.info.vi51.worldswar.gui.layer;

import java.awt.Graphics;

import fr.utbm.info.vi51.worldswar.perception.PerceptionGrid;

public interface GuiLayer {

	/**
	 * Enable or disable the layer
	 * 
	 * @param b
	 */
	void setEnabled(boolean b);

	/**
	 * update the layer
	 * 
	 * @param perceptionGrid
	 */
	void update(PerceptionGrid perceptionGrid);

	/**
	 * 
	 * @param g
	 *            used to paint the components
	 * @param cellSize
	 *            in pixel
	 */
	void paintLayer(Graphics g, int cellSize);
}
