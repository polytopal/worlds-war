package fr.utbm.info.vi51.worldswar.agent.behaviour.tactical;

import static fr.utbm.info.vi51.worldswar.perception.PerceptionGrid.MY_POSITION;

import java.awt.Point;
import java.util.HashMap;

import fr.utbm.info.vi51.worldswar.agent.behaviour.operational.AntOperationalBehaviour;
import fr.utbm.info.vi51.worldswar.environment.PheromoneType;
import fr.utbm.info.vi51.worldswar.environment.influence.DoNothingInfluence;
import fr.utbm.info.vi51.worldswar.environment.influence.Influence;
import fr.utbm.info.vi51.worldswar.perception.AntPerception;
import fr.utbm.info.vi51.worldswar.utils.Direction;

/**
 * Define basic tactical behaviour for ants.
 *
 */
public class AntTacticalBehaviour {

	private final AntOperationalBehaviour operationalBehaviour;

	/**
	 * A food trail intensity depends on the quantity of food discovered. This
	 * number corresponds to the quantity of food needed to dispose a maximum
	 * intensity food trail.
	 */
	private static final float MAX_FOOD_TRAIL_QTY = 50;

	/**
	 * @param operationalBehaviour
	 */
	public AntTacticalBehaviour(AntOperationalBehaviour operationalBehaviour) {
		this.operationalBehaviour = operationalBehaviour;
	}

	/**
	 * Tries to find the best position to target to collect food, or picks the
	 * food that is on the ground.
	 * 
	 * @param perception
	 * @param memory
	 * @return the resulting influence
	 */
	public Influence collectFood(AntPerception perception, HashMap<String, Object> memory) {
		if (perception.isAtHome()) {
			this.operationalBehaviour.startPheromoneTrail(memory, PheromoneType.HOME);
		}
		if (perception.getFoodAt(MY_POSITION) > 0) {
			this.startFoodTrail(perception, memory);
			return this.operationalBehaviour.pickFood(perception);
		}
		if (perception.isAvailableFoodInSight()) {
			return this.operationalBehaviour.moveToTarget(perception, memory, perception.getClosestAvailableFoodPos());
		}
		final Point highestFoodPheromonePos = perception.getHighestPheromonePos(PheromoneType.FOOD);
		if (highestFoodPheromonePos != null) {
			return this.operationalBehaviour.moveToTarget(perception, memory, highestFoodPheromonePos);
		}
		return this.operationalBehaviour.wander(perception, memory);
	}

	/**
	 * Wanders randomly, trying to find food in unexplored areas
	 * 
	 * @param perception
	 * @param memory
	 * @return the resulting influence
	 */
	public Influence wanderForFood(AntPerception perception, HashMap<String, Object> memory) {
		if (perception.isAtHome()) {
			this.operationalBehaviour.startPheromoneTrail(memory, PheromoneType.HOME);
		}
		if (perception.getFoodAt(MY_POSITION) > 0) {
			this.startFoodTrail(perception, memory);
			return this.operationalBehaviour.pickFood(perception);
		}
		if (perception.isAvailableFoodInSight()) {
			return this.operationalBehaviour.moveToTarget(perception, memory, perception.getClosestAvailableFoodPos());
		}
		return this.operationalBehaviour.wander(perception, memory);
	}

	/**
	 * Tries to find the best position to target to go home, or drops the
	 * carried food if currently at home
	 * 
	 * @param perception
	 * @param memory
	 * @return the resulting influence
	 */
	public Influence bringFoodHome(AntPerception perception, HashMap<String, Object> memory) {
		if (perception.isAtHome()) {
			return this.operationalBehaviour.dropFood(perception);
		}
		return this.goHome(perception, memory);
	}

	/**
	 * Tries to find the best position to target to go home
	 * 
	 * @param perception
	 * @param memory
	 * @return the resulting influence
	 */
	public Influence goHome(AntPerception perception, HashMap<String, Object> memory) {
		if (perception.isHomeInSight()) {
			if (perception.isAtHome()) {
				return new DoNothingInfluence();
			}
			return this.operationalBehaviour.moveToTarget(perception, memory, perception.getHomePos());
		}

		final Point highestAntPheromonePos = perception.getHighestPheromonePos(PheromoneType.HOME);
		if (highestAntPheromonePos != null) {
			return this.operationalBehaviour.moveToTarget(perception, memory, highestAntPheromonePos);
		}
		return this.operationalBehaviour.wander(perception, memory);
	}

	/**
	 * Starts a food trail, auto determining its intensity given the quantity of
	 * food in sight
	 * 
	 * @param perception
	 * @param memory
	 */
	private void startFoodTrail(AntPerception perception, HashMap<String, Object> memory) {
		int foodInSight = perception.countFoodInSight() - perception.getMyBody().getCapacity();
		float trailCoeff = foodInSight / MAX_FOOD_TRAIL_QTY;
		trailCoeff = Math.min(trailCoeff, 1);
		trailCoeff = Math.max(trailCoeff, 0);
		this.operationalBehaviour.startPheromoneTrail(memory, PheromoneType.FOOD, trailCoeff);
	}

	/**
	 * Starts a danger trail. The quantity of pheromones dropped depends on the
	 * number/caste of enemies spotted
	 * 
	 * @param perception
	 * @param memory
	 */
	public void startDangerTrail(AntPerception perception, HashMap<String, Object> memory) {
		// TODO prendre en compte la qté d'ennemis
		this.operationalBehaviour.startPheromoneTrail(memory, PheromoneType.DANGER);
	}

	/**
	 * Patrols around the hill. TODO : Just a wander, maybe later with a
	 * perimeter to prevent the ant to get too far from the hill while
	 * patrolling
	 * 
	 * @param perception
	 * @param memory
	 * @return the resulting influence
	 */
	public Influence patrol(AntPerception perception, HashMap<String, Object> memory) {
		if (perception.isAtHome()) {
			this.operationalBehaviour.startPheromoneTrail(memory, PheromoneType.HOME);
		}
		// TODO implement a real patrolling behaviour
		return new DoNothingInfluence();
		// return this.operationalBehaviour.wander(perception, memory);
	}

	/**
	 * Simple flee behaviour : the ant simply runs back home, without avoiding
	 * the enemies on its way
	 * 
	 * @param perception
	 * @param memory
	 * @return the resulting influence
	 */
	public Influence flee(AntPerception perception, HashMap<String, Object> memory) {
		/*
		 * TODO La suite c ptet pabojeu : l'idée stratégique, c que qd le fourmi
		 * voit un ennemi, elle prenne la fuite -> call flee(). et c ds flee
		 * qu'on définit le comportement par défaut de la fuite, ici c rentrer
		 * maison en lachant des phero DANGER. Pb : pr éviter qu'à chaq step la
		 * fourmi refresh sa dangerTrail et donc que la qté de phéro drop
		 * repasse o max, need un check mémoire, et la constante liée est
		 * définie dans l'interface stratégique. La solution que j'ai choisie c
		 * de passer le startDangerTrail en public et le call dans le strategic.
		 * le flee ne contient alors plus qu'un simple goHome(). J'aurais
		 * préféré que tout se fasse dans le flee, pour éviter de devoir répéter
		 * le code pour chaque caste qui veut fuire. Ya mieux ? Genre rajouter
		 * une deuxieme constante stratégique publik (stratsFleeing) qui vaut
		 * true o premier ennemi rencontré, et qui déclenche la trail dans le
		 * flee, puis qui est passée à false dans le flee ? ca me parait pas
		 * génialissime non plus
		 */
		return this.goHome(perception, memory);
	}

	/**
	 * The ant will pursue its enemies, using the danger trails left by the
	 * other ants to find them
	 * 
	 * @param perception
	 * @param memory
	 * @return the resulting influence
	 */
	public Influence chaseAndFight(AntPerception perception, HashMap<String, Object> memory) {
		if (perception.isAtHome()) {
			this.operationalBehaviour.startPheromoneTrail(memory, PheromoneType.HOME);
		}

		if (perception.isEnemyInMeleeRange()) {
			/*
			 * TODO : Need improvement. Stupidest behavior ever : atm, only
			 * attacks a random target, without considering HPs or the previous
			 * target focused.
			 */
			return this.operationalBehaviour.attackMeleeTarget(Direction.fromPoint(perception.getClosestEnemyPos()));
		}
		if (perception.isEnemyInSight()) {
			// TODO find a way to ask other warriors to follow this way (if
			// starts a Danger Trail here, highest pheromone will return the
			// coords of the cell where the chase began, not the actual position
			// of the fight
			return this.operationalBehaviour.moveToTarget(perception, memory, perception.getClosestEnemyPos());
		}
		final Point highestDangerPheromonePos = perception.getHighestPheromonePos(PheromoneType.DANGER);
		if (highestDangerPheromonePos != null) {
			return this.operationalBehaviour.moveToTarget(perception, memory, highestDangerPheromonePos);
		}
		return this.operationalBehaviour.wander(perception, memory);
		/*
		 * TODO : atm si 2 gatherer se rencontrent au milieu de nulle part et
		 * rentrent à la maison, chacune des fourmilières va balancer toutes les
		 * guerrières du coin sur la pheroTrail -> fight vers le pt de rencontre
		 * des gatherer. Si 1 des 2 fourmilières n'envoit pas de guerriers, les
		 * guerriers de l'autre fourmilière vont arriver au point de rencontre
		 * (fin de la pheroTrail), et vont attendre la dispertion des phero
		 * avant de wander -> inactivité, à améliorer ?
		 */
	}
}
