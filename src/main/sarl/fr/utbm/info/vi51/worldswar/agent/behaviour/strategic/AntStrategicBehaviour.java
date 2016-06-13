package fr.utbm.info.vi51.worldswar.agent.behaviour.strategic;

import java.util.HashMap;

import fr.utbm.info.vi51.worldswar.agent.behaviour.tactical.AntTacticalBehaviour;
import fr.utbm.info.vi51.worldswar.environment.influence.Influence;
import fr.utbm.info.vi51.worldswar.perception.AntPerception;

/**
 * A strategic behaviour represent the general decision of the agent. Each ant
 * caste have a different one. The Strategic Behaviour can use several
 * {@link AntTacticalBehaviour}. The goal of this behaviour will be to choose
 * the good {@link AntTacticalBehaviour} to compute the {@link Influence}. It
 * can access to the ant memory to take his decision.
 *
 */
public interface AntStrategicBehaviour {

	/**
	 * {@code true} if the ant has encountered something perceived as danger
	 * recently, {@code false} else. Reset to {@code false} when the ant reaches
	 * its hill.
	 */
	public static final String ENCOUNTERED_DANGER = "encounteredDanger"; //$NON-NLS-1$

	/**
	 * 
	 * @param perception
	 *            the perception of the ant
	 * @param memory
	 *            the memory of the ant
	 * @return the computed influence based on the behaviour of the ant
	 */
	Influence computeInfluence(AntPerception perception, HashMap<String, Object> memory);
}
