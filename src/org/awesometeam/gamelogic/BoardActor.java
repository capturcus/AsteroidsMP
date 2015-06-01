package org.awesometeam.gamelogic;

import java.util.ArrayList;
import math.geom2d.Point2D;
import math.geom2d.Vector2D;
import math.geom2d.point.PointArray2D;

public class BoardActor {

    public enum State {
        ALIVE, DEAD, DYING
    }

    protected ActorLists actorLists;
    protected Point2D position;
    protected PointArray2D shape;
    protected int healthPoints;
    protected int mass;
    protected Vector2D velocity;
    protected State state;
    protected Vector2D orientation;
    protected double radius;
    
    protected BoardActor killer;
    
    static double haxxx = 0;

    public BoardActor(Point2D pos) {
        killer = null;
        position = pos;
        shape = new PointArray2D();
        healthPoints = 1;
        mass = 0;
        velocity = new Vector2D(100, 0);
        state = State.ALIVE;
        orientation = new Vector2D(1, 0);
        radius = 10;
    }

    public BoardActor() {
        this(new Point2D(0, 0));
    }

    public void collision(BoardActor other) {
    }
    
    public void asteroidCollision(Asteroid asteroid) {
        
    }
    
    public void spaceshipCollision(Spaceship spaceship) {
    
    }
    
    public void projectileCollision(Projectile projectile) {
        
    }

    public boolean isAttacking() {
        return false;
    }
    //TODO
    public ArrayList<Projectile> attack(/*Projectile weapon*/) {
        ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
        return projectiles;
    }

    
    public void move(double timeInterval) {
        Vector2D vector = actorLists.getPhyscics().getMove(mass, velocity, timeInterval);
        position = actorLists.getBoard().getNewPosition(position, vector);
    }

    public void die() {
        state = State.DEAD;
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
        return orientation.angle() - 1.6;
        //  haxxx += 0.01;
        //   return haxxx; 
    }
    
    public void setOrientation(Vector2D v) {
        orientation = v;
    }
    
    public void setVelocity(Vector2D v) {
        velocity = v;
    }
    
    public double getRadius() {
        return radius;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + ": pos " + position + "; ";
    }
    
    public void setActorList(ActorLists actorLists) {
        this.actorLists = actorLists;
    }

    public void injure(int damage) {
        healthPoints -= damage;
        if (healthPoints <= 0)
            state = BoardActor.State.DYING;
    }

    public void bounce(BoardActor other) {
        position = position.plus(new Vector2D(other.getPosition(), position).normalize().times(2));
        velocity = actorLists.getPhyscics().bounce(position, velocity, other.getPosition());
    }
    
    public void increaseKills() {
        
    }
}
