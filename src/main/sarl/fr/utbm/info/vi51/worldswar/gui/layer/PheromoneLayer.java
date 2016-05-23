package fr.utbm.info.vi51.worldswar.gui.layer;

import java.awt.Color;

import fr.utbm.info.vi51.worldswar.environment.PheromoneType;
import fr.utbm.info.vi51.worldswar.perception.PerceptionCell;

public class PheromoneLayer extends DefaultColorLayer {

	private static float PHEROMONE_VISIBILITY_COEFFICIENT = 10f;
	private static Color PHEROMONE_DANGER_COLOR = new Color(255, 102, 255);
	private static Color PHEROMONE_FOOD_COLOR = new Color(102, 255, 255);
	private static Color PHEROMONE_HOME_COLOR = new Color(255, 178, 102);

	private final PheromoneType pheromoneType;

	private final Color PheromoneColor;

	public PheromoneLayer(PheromoneType pheromoneType) {
		this.pheromoneType = pheromoneType;
		switch (pheromoneType) {
		case DANGER:
			this.PheromoneColor = PHEROMONE_DANGER_COLOR;
			break;
		case FOOD:
			this.PheromoneColor = PHEROMONE_FOOD_COLOR;
			break;
		case HOME:
			this.PheromoneColor = PHEROMONE_HOME_COLOR;
			break;
		default:
			this.PheromoneColor = Color.BLACK;
			break;
		}

		this.enabled = false;
	}

	public PheromoneType getPheromoneType() {
		return this.pheromoneType;
	}

	@Override
	protected Color computeCellColor(PerceptionCell cell) {
		Color color = null;
		final float pheroQty = cell.getTotalPheromoneQuantity(this.pheromoneType);
		if (pheroQty > 0) {
			final int alpha = Math.min((int) (pheroQty * PHEROMONE_VISIBILITY_COEFFICIENT), 255);
			color = new Color(this.PheromoneColor.getRed(), this.PheromoneColor.getGreen(),
					this.PheromoneColor.getBlue(), alpha);
		}
		return color;
	}

}
