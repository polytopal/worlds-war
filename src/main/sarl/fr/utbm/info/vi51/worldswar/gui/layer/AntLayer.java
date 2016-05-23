package fr.utbm.info.vi51.worldswar.gui.layer;

import java.awt.Color;

import fr.utbm.info.vi51.worldswar.environment.Breed;
import fr.utbm.info.vi51.worldswar.perception.PerceptionCell;
import fr.utbm.info.vi51.worldswar.perception.perceivable.PerceivableAnt;

public class AntLayer extends DefaultColorLayer {

	private static Color DARK_ANT_COLOR = new Color(51, 25, 0);
	private static Color RED_ANT_COLOR = new Color(153, 0, 0);

	@Override
	protected Color computeCellColor(PerceptionCell cell) {
		Color c = null;
		final PerceivableAnt ant = cell.getAnt();
		if (ant != null) {
			final Breed breed = ant.getColony().getBreed();
			switch (breed) {
			case DARK_ANTS:
				c = DARK_ANT_COLOR;
				break;
			case RED_ANTS:
				c = RED_ANT_COLOR;
				break;
			default:
				c = Color.BLACK;
				break;
			}
		}
		return c;
	}

}
