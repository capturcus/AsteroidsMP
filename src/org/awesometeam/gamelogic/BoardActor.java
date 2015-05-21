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

    static double haxxx = 0;

    public BoardActor(Point2D pos) {
        position = pos;
        shape = new PointArray2D();
        healthPoints = 1;
        mass = 0;
        velocity = new Vector2D(10, 0);
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
        Projectile projectile = new Projectile();
        projectile.setPosition(position);
        projectile.setOrientation(orientation);
        projectile.setVelocity(orientation.normalize().times(30));
        projectiles.add(projectile);
        
        actorLists.addProjectile(projectile);
        
        return projectiles;
    }

    
    public void move(Physics physics, Board board, int timeInterval) {
        Vector2D vector = physics.getMove(mass, velocity, timeInterval);
        position = board.getNewPosition(position, vector);
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
}