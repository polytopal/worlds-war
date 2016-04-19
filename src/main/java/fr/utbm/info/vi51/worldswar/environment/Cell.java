package fr.utbm.info.vi51.worldswar.environment;

import java.awt.Point;
import java.util.LinkedList;
import java.util.List;

import fr.utbm.info.vi51.worldswar.environment.object.EnvironmentObject;

/**
 * Stores the content of a tile in the environment grid.
 *
 */
public class Cell {
	private List<EnvironmentObject> objects;

	private Point position;

	public Point getPosition() {
		return this.position;
	}

	public Cell(Point position) {
		this.objects = new LinkedList<>();
		this.position = position;
	}

	public List<EnvironmentObject> getObjects() {
		return this.objects;
	}
}
