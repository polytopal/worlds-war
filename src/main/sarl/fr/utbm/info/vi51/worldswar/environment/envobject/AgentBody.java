package fr.utbm.info.vi51.worldswar.environment.envobject;

import java.awt.Point;
import java.util.UUID;

import io.sarl.lang.core.Agent;

/**
 * Physical body belonging to an {@link Agent}
 *
 */
public abstract class AgentBody extends DynamicObject {

	/**
	 * The UUID of the agent
	 */
	private final UUID uuid;

	/**
	 * @param position
	 *            The Initial position of the body
	 * @param uuid
	 *            the UUID of the agent that owns the body
	 */
	public AgentBody(Point position, UUID uuid) {
		super(position);
		this.uuid = uuid;
	}

	/**
	 * @return the perception range of the agent body
	 */
	public abstract int getPerceptionRange();

	/**
	 * @return the uuid
	 */
	public UUID getUuid() {
		return this.uuid;
	}

}
