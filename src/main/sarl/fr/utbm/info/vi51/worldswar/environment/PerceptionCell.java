package fr.utbm.info.vi51.worldswar.environment;

import java.util.ArrayList;
import java.util.List;

import fr.utbm.info.vi51.worldswar.environment.envobject.AntBody;
import fr.utbm.info.vi51.worldswar.environment.envobject.AntHill;
import fr.utbm.info.vi51.worldswar.environment.envobject.EnvironmentObject;
import fr.utbm.info.vi51.worldswar.environment.envobject.Food;
import fr.utbm.info.vi51.worldswar.environment.envobject.Pheromone;
import fr.utbm.info.vi51.worldswar.environment.envobject.Wall;
import fr.utbm.info.vi51.worldswar.environment.perceivable.Perceivable;
import fr.utbm.info.vi51.worldswar.environment.perceivable.PerceivableAnt;
import fr.utbm.info.vi51.worldswar.environment.perceivable.PerceivableAntHill;
import fr.utbm.info.vi51.worldswar.environment.perceivable.PerceivableFood;
import fr.utbm.info.vi51.worldswar.environment.perceivable.PerceivablePheromone;
import fr.utbm.info.vi51.worldswar.environment.perceivable.PerceivableWall;

public class PerceptionCell {

	private final List<Perceivable> perceptionList;

	public PerceptionCell(List<EnvironmentObject> envObjectList) {

		this.perceptionList = new ArrayList<>();

		// foreach Environment object in the cell
		for (final EnvironmentObject envObject : envObjectList) {
			Perceivable perceivableObject = null;

			// we change each envObject in Perceivable
			if (envObject instanceof AntBody) {
				perceivableObject = new PerceivableAnt((AntBody) envObject);
			} else if (envObject instanceof AntHill) {
				perceivableObject = new PerceivableAntHill((AntHill) envObject);
			} else if (envObject instanceof Food) {
				perceivableObject = new PerceivableFood((Food) envObject);
			} else if (envObject instanceof Pheromone) {
				perceivableObject = new PerceivablePheromone((Pheromone) envObject);
			} else if (envObject instanceof Wall) {
				perceivableObject = new PerceivableWall((Wall) envObject);
			}

			if (perceivableObject != null) {
				this.perceptionList.add(perceivableObject);
			}
		}
	}

	/**
	 * remove the content of the cell
	 */
	public void clear() {
		this.perceptionList.clear();
	}

	@Override
	public String toString() {
		return this.perceptionList.toString();
	}
}
