package fr.utbm.info.vi51.worldswar.agent.operational_behaviour;

import java.awt.Point;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import fr.utbm.info.vi51.worldswar.environment.influence.Influence;
import fr.utbm.info.vi51.worldswar.environment.influence.MoveInfluence;
import fr.utbm.info.vi51.worldswar.perception.AntPerception;
import fr.utbm.info.vi51.worldswar.utils.Direction;

public class MoveToTargetOperationalBehaviour implements AntOperationalBehaviour {

	@Override
	public Influence computeInfluence(AntPerception perception, HashMap<String, Object> memory, Point Target) {
		// TODO - implement function here

		// return a move in a random direction while this behavior is not
		// implemented
		final List<Direction> list = Arrays.asList(Direction.values());
		Collections.shuffle(list);
		final Direction direction = list.get(0);
		return new MoveInfluence(direction);
	}

}
