package org.gamelogic;

import math.geom2d.Point2D;
import math.geom2d.Vector2D;
import math.geom2d.point.PointArray2D;

public class BoardActor {

	public enum State {
		ALIVE, DEAD, DYING
	}
	
	private Point2D position;
	private PointArray2D shape;
	private int healthPoints;
	private int mass;
	private Vector2D velocity;
	private State state;
	
	public BoardActor(Point2D pos) {
		position = pos;
		shape = new PointArray2D();
		healthPoints = 0;
		mass = 0;
		velocity = new Vector2D(0, 0);
		state = State.ALIVE;
	}
	
	public BoardActor() {
		this(new Point2D(0,0));
	}	
	
	public void collision(BoardActor other) {
		
	}
	
	public void move(Physics physics, Board board, int timeInterval) {
		Vector2D vector = physics.getMove(timeInterval, velocity, timeInterval);
		position = board.getNewPosition(position, vector);
	}
	
	public void die() {
		
	}

	public State getState() {
		return state;
	}
	
	public void setPosition(Point2D pos) {
		position = pos;
	}
	
}
