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
	 * @return the antFill found, or null otherwise
	 */
	public PerceivableAntHill getAntHill() {
		for (final Perceivable perceivable : this.perceptionList) {
			if (perceivable instanceof PerceivableAntHill) {
				return (PerceivableAntHill) perceivable;
			}
		}
		return null;
	}

	/**
	 * @return all the ants found in the cell
	 */
	public List<PerceivableAnt> getAnts() {
		final ArrayList<PerceivableAnt> antList = new ArrayList<>();
		for (final Perceivable perceivable : this.perceptionList) {
			if (perceivable instanceof PerceivableAnt) {
				antList.add((PerceivableAnt) perceivable);
			}
		}
		return antList;
	}

	/**
	 * @return the first ant found in the cell, or null otherwise
	 */
	public PerceivableAnt getAnt() {
		for (final Perceivable perceivable : this.perceptionList) {
			if (perceivable instanceof PerceivableAnt) {
				return (PerceivableAnt) perceivable;
			}
		}
		return null;
	}

	/**
	 * @return the food found, or null otherwise
	 */
	public PerceivableFood getFood() {
		for (final Perceivable perceivable : this.perceptionList) {
			if (perceivable instanceof PerceivableFood) {
				return (PerceivableFood) perceivable;
			}
		}
		return null;
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
