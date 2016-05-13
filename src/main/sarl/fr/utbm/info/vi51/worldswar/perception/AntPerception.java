package fr.utbm.info.vi51.worldswar.perception;

import fr.utbm.info.vi51.worldswar.environment.envobject.AntBody;
import fr.utbm.info.vi51.worldswar.perception.perceivable.PerceivableAnt;

/**
 * Contains all the perceptions of an {@link AntBody} and provides utility
 * methods to make them digest.
 * 
 */
public class AntPerception {

	private PerceivableAnt myBody;
	private PerceptionGrid grid;

	/**
	 * 
	 * @param myBody
	 *            the body of the ant
	 * @param grid
	 *            the PerceptionGrid containing the perceivables perceived by
	 *            the ant
	 */
	public AntPerception(PerceivableAnt myBody, PerceptionGrid grid) {
		this.grid = grid;
		this.myBody = myBody;
	}

}
