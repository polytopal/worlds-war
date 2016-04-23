package fr.utbm.info.vi51.worldswar.environment.envobject;

import fr.utbm.info.vi51.worldswar.environment.influence.Influence;
import io.sarl.lang.core.Agent;

/**
 * Physical body belonging to an {@link Agent}
 *
 */
public abstract class AgentBody extends DynamicObject {

	/**
	 * The {@link Environment} stores here the {@link Influence} he received for
	 * the current step
	 */
	private Influence influence;
}
