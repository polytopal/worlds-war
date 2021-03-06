package fr.utbm.info.vi51.worldswar.gui.layer;

import java.awt.Graphics;

import fr.utbm.info.vi51.worldswar.environment.MapInformation;
import fr.utbm.info.vi51.worldswar.perception.PerceptionGrid;

public interface GuiLayer {

	/**
	 * Enable or disable the layer
	 * 
	 * @param b
	 */
	void setEnabled(boolean b);

	boolean isEnabled();

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

	/**
	 * Method called when a new simulation is started
	 * 
	 * @param mapInfo
	 */
	void simulationStarted(MapInformation mapInfo);
}
