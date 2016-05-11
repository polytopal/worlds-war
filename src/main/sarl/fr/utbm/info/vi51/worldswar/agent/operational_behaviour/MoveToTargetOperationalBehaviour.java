package fr.utbm.info.vi51.worldswar.agent.operational_behaviour;

import java.awt.Point;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import fr.utbm.info.vi51.worldswar.environment.PerceptionGrid;
import fr.utbm.info.vi51.worldswar.environment.influence.Influence;
import fr.utbm.info.vi51.worldswar.environment.influence.MoveInfluence;
import fr.utbm.info.vi51.worldswar.utils.Direction;

public class MoveToTargetOperationalBehaviour implements OperationalBehaviour {

	@Override
	public Influence computeInfluence(PerceptionGrid perceptionGrid, HashMap<String, Object> memory, Point Target) {
		// TODO - implement function here

		final List<Direction> list = Arrays.asList(Direction.values());
		Collections.shuffle(list);
		final Direction direction = list.get(0);

		return new MoveInfluence(direction);
	}

}
