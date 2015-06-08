package org.awesometeam.gamelogic;

import static java.lang.Math.PI;
import java.util.Random;
import math.geom2d.Point2D;
import math.geom2d.Vector2D;

public class Asteroid extends Obstacle {

    public static final int MIN_RADIUS = 10;
    public static final int MAX_SIZE = 3;
    protected int size;
    private int id;
    public static int nextId = 0;
    public final static int START_HP = 1;
    
    public final static int DAMAGE = 40;
    public final static int START_INTERSPACE = 15;
    
    public static int bigAsteroidCount;

    public Asteroid(Point2D pos) {
        healthPoints = START_HP;
        id = nextId;
        id ++;
        position = pos;
        size =  3;//ActorMnager.randomGenerator.nextInt(MAX_SIZE + 1);
        radius = MIN_RADIUS * (size + 1);
        
        orientation = new Vector2D((ActorManager.randomGenerator.nextInt(100)-50),(ActorManager.randomGenerator.nextInt(100)-50)).normalize();
        velocity = orientation.times(100);
    }
    
    public Asteroid() {
        this(new Point2D(0, 0));
    }

    @Override
    public void die() {
        state = State.DEAD;
        actorLists.removeAsteroid(this);
        if (size > 0) {
            actorLists.addAsteroid(createSplinter(1));
            actorLists.addAsteroid(createSplinter(-1));
        }
    }
    
    private Asteroid createSplinter(double s) {
        Asteroid asteroid = new Asteroid(this.position);
        asteroid.setSize(size - 1);
        Vector2D vec = velocity.normalize().rotate(PI / 2 * s).times(asteroid.getRadius());
        asteroid.setPosition(this.position.plus(vec));
        asteroid.setVelocity(velocity.rotate(0.4 * s));
        return asteroid;
    }
    
    @Override
    public void collision(BoardActor other) {
        other.asteroidCollision(this);
    }
    
    @Override
    public void spaceshipCollision(Spaceship spaceship) {
        bounce(spaceship);
    }
    
    @Override
    public void asteroidCollision(Asteroid asteroid) {
        bounce(asteroid);
    }
    
    @Override
    public void projectileCollision(Projectile projectile) {
        boolean unused = injure(Projectile.DAMAGE);
    }
    
    public void setSize(int size) {
        this.size = size;
        this.radius = MIN_RADIUS * (size + 1);
    }
    
    public int getSize() {
        return size;
    }

    public int getId() {
        return id;
    }
    
    @Override
    public void move(double timeInterval) {
        Vector2D vector = actorLists.getPhyscics().getMove(mass, velocity, timeInterval);
        position = actorLists.getBoard().getNewPosition(position, vector);
    }
}
