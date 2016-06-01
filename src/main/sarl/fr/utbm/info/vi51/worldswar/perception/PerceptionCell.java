package fr.utbm.info.vi51.worldswar.perception;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.xtext.xbase.lib.Pair;

import fr.utbm.info.vi51.worldswar.environment.Colony;
import fr.utbm.info.vi51.worldswar.environment.PheromoneType;
import fr.utbm.info.vi51.worldswar.environment.envobject.AntBody;
import fr.utbm.info.vi51.worldswar.environment.envobject.AntHill;
import fr.utbm.info.vi51.worldswar.environment.envobject.EnvironmentObject;
import fr.utbm.info.vi51.worldswar.environment.envobject.Food;
import fr.utbm.info.vi51.worldswar.environment.envobject.Pheromone;
import fr.utbm.info.vi51.worldswar.environment.envobject.Wall;
import fr.utbm.info.vi51.worldswar.perception.perceivable.Perceivable;
import fr.utbm.info.vi51.worldswar.perception.perceivable.PerceivableAnt;
import fr.utbm.info.vi51.worldswar.perception.perceivable.PerceivableAntHill;
import fr.utbm.info.vi51.worldswar.perception.perceivable.PerceivableFood;
import fr.utbm.info.vi51.worldswar.perception.perceivable.PerceivablePheromone;
import fr.utbm.info.vi51.worldswar.perception.perceivable.PerceivableWall;

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
	 * The cache of the function {@link PerceptionCell#getAntHill()}
	 */
	private PerceivableAntHill antHillCache = null;
	private boolean antHillCalculated = false;

	/**
	 * @return the antFill found, or null otherwise
	 */
	public PerceivableAntHill getAntHill() {
		if (this.antHillCalculated) {
			return this.antHillCache;
		}

		for (final Perceivable perceivable : this.perceptionList) {
			if (perceivable instanceof PerceivableAntHill) {
				final PerceivableAntHill result = (PerceivableAntHill) perceivable;
				this.antHillCalculated = true;
				this.antHillCache = result;
				return result;
			}
		}
		this.antHillCalculated = true;
		return null;
	}

	/**
	 * the cache of the function {@link PerceptionCell#getAnts()}
	 */
	private List<PerceivableAnt> antsCache = null;
	private boolean antsCalculated = false;

	/**
	 * @return all the ants found in the cell
	 */
	public List<PerceivableAnt> getAnts() {
		if (this.antsCalculated) {
			return this.antsCache;
		}

		final ArrayList<PerceivableAnt> antList = new ArrayList<>();
		for (final Perceivable perceivable : this.perceptionList) {
			if (perceivable instanceof PerceivableAnt) {
				antList.add((PerceivableAnt) perceivable);
			}
		}
		this.antsCalculated = true;
		this.antsCache = antList;
		return antList;
	}

	/**
	 * @return the first ant found in the cell, or null otherwise
	 */
	public PerceivableAnt getAnt() {
		final List<PerceivableAnt> ants = this.getAnts();
		if (ants.size() > 0) {
			return ants.get(0);
		}
		return null;
	}

	/**
	 * the cache of the function {@link PerceptionCell#getFood()}
	 */
	private PerceivableFood foodCache = null;
	private boolean foodCalculated = false;

	/**
	 * @return the food found, or null otherwise
	 */
	public PerceivableFood getFood() {
		if (this.foodCalculated) {
			return this.foodCache;
		}

		for (final Perceivable perceivable : this.perceptionList) {
			if (perceivable instanceof PerceivableFood) {
				final PerceivableFood result = (PerceivableFood) perceivable;
				this.foodCalculated = true;
				this.foodCache = result;
				return result;
			}
		}
		this.foodCalculated = true;
		return null;
	}

	/**
	 * the cache of the function {@link PerceptionCell#getWall()}
	 */
	private PerceivableWall wallCache = null;
	private boolean wallCalculated = false;

	/**
	 * @return the wall found, or null otherwise
	 */
	public PerceivableWall getWall() {
		if (this.wallCalculated) {
			return this.wallCache;
		}

		for (final Perceivable perceivable : this.perceptionList) {
			if (perceivable instanceof PerceivableWall) {
				final PerceivableWall wall = (PerceivableWall) perceivable;
				this.wallCalculated = true;
				this.wallCache = wall;
				return wall;
			}
		}
		this.wallCalculated = true;
		return null;
	}

	/**
	 * The cache of the function
	 * {@link PerceptionCell#getPheromoneQuantity(PheromoneType, Colony)}
	 */
	private final Map<Pair<PheromoneType, Colony>, Float> pheromoneQuantityCache = new HashMap<>();

	/**
	 * If the cell contains the pheromone type for the given colony, return its
	 * quantity, else return 0
	 * 
	 * @param type
	 *            the {@link PheromoneType} of the pheromone
	 * @param colony
	 * @return the pheromone quantity
	 */
	public float getPheromoneQuantity(PheromoneType type, Colony colony) {
		if (this.pheromoneQuantityCache.get(new Pair<>(type, colony)) != null) {
			return this.pheromoneQuantityCache.get(new Pair<>(type, colony));
		}

		for (final Perceivable perceivable : this.perceptionList) {
			if (perceivable instanceof PerceivablePheromone && ((PerceivablePheromone) perceivable).getType() == type
					&& ((PerceivablePheromone) perceivable).getColony() == colony) {
				final float qty = ((PerceivablePheromone) perceivable).getQty();
				this.pheromoneQuantityCache.put(new Pair<>(type, colony), qty);
				return qty;
			}
		}
		this.pheromoneQuantityCache.put(new Pair<>(type, colony), (float) 0);
		return 0;
	}

	/**
	 * The cache of the function
	 * {@link PerceptionCell#getTotalPheromoneQuantity(PheromoneType)}
	 */
	private final Map<PheromoneType, Float> totalPheromoneQuantityCache = new HashMap<>();

	/**
	 * 
	 * @param type
	 * @return the total quantity of a given pheromone type, adding the
	 *         pheromone of each colony
	 */
	public float getTotalPheromoneQuantity(PheromoneType type) {
		if (this.totalPheromoneQuantityCache.get(type) != null) {
			return this.totalPheromoneQuantityCache.get(type);
		}

		float qty = 0;
		for (final Perceivable perceivable : this.perceptionList) {
			if (perceivable instanceof PerceivablePheromone && ((PerceivablePheromone) perceivable).getType() == type) {
				qty += ((PerceivablePheromone) perceivable).getQty();
			}
		}
		this.totalPheromoneQuantityCache.put(type, qty);
		return qty;
	}

	/**
	 * The cache of the function {@link PerceptionCell#isTraversable()}
	 */
	private boolean TaversableCache = false;
	private boolean TaversableCalculated = false;

	public boolean isTraversable() {
		if (this.TaversableCalculated) {
			return this.TaversableCache;
		}

		for (final Perceivable p : this.perceptionList) {
			if (!p.isTraversable()) {
				this.TaversableCalculated = true;
				return false;
			}
		}
		this.TaversableCalculated = true;
		this.TaversableCache = true;
		return true;
	}

	@Override
	public String toString() {
		return this.perceptionList.toString();
	}

}
