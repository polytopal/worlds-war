package fr.utbm.info.vi51.worldswar.agent.strategic_behaviour;

import java.util.HashMap;

import fr.utbm.info.vi51.worldswar.agent.tactical_behaviour.TacticalBehaviour;
import fr.utbm.info.vi51.worldswar.environment.influence.Influence;
import fr.utbm.info.vi51.worldswar.perception.PerceptionGrid;

/**
 * A strategic behaviour represent the general decision of the agent. Each ant
 * caste have a different one. The Strategic Behaviour can use several
 * {@link TacticalBehaviour}. The goal of this behaviour will be to choose the
 * good {@link TacticalBehaviour} to compute the {@link Influence}. It can
 * access to the ant memory to take his decision.
 *
 */
public interface StrategicBehaviour {

	Influence computeInfluence(PerceptionGrid perceptionGrid, HashMap<String, Object> memory);
}
