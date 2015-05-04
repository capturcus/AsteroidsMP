package org.awesometeam.gamelogic;

import math.geom2d.Point2D;
import math.geom2d.Vector2D;
import math.geom2d.point.PointArray2D;

public class BoardActor {

	public enum State {
		ALIVE, DEAD, DYING
	}
	
	protected Point2D position;
	protected PointArray2D shape;
	protected int healthPoints;
	protected int mass;
	protected Vector2D velocity;
	protected State state;
        protected Vector2D orientation;
	
        static double haxxx = 0;
        
	public BoardActor(Point2D pos) {
		position = pos;
		shape = new PointArray2D();
		healthPoints = 0;
		mass = 0;
		velocity = new Vector2D(0, 0);
		state = State.ALIVE;
                orientation = new Vector2D(1, 0);
	}
	
	public BoardActor() {
		this(new Point2D(0,0));
	}	
	
	public void collision(BoardActor other) {
		
	}
	
	public void move(Physics physics, Board board, int timeInterval) {
		Vector2D vector = physics.getMove(mass, velocity, timeInterval);
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
	
	
	//
	public Point2D getPosition() {
		return position;
	}
	
	public int getHealthPoints() {
		return healthPoints;
	}
	
	public double getAngle() {
		//TODO
		return orientation.angle();
        //  haxxx += 0.01;
         //   return haxxx; 
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName()+": pos "+position+"; ";
	}
}
