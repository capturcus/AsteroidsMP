package org.awesometeam.gamelogic;

import java.util.Random;
import math.geom2d.Point2D;
import math.geom2d.Vector2D;

public class Asteroid extends Obstacle {

    public static final int minRadius = 10;
    protected int size;
    public static final Random generator = new Random();
    private int moveLength;
    private int moveRandom;

    public Asteroid(Point2D pos) {
        position = pos;
        moveLength = 0;
        size = generator.nextInt(4);
        radius = minRadius * (size + 1);
    }
    
    public Asteroid() {
        this(new Point2D(0, 0));
    }

    @Override
    public void die() {
        state = State.DEAD;
        actorLists.removeAsteroid(this);
        if (size > 0) {
            Asteroid asteroid1 = new Asteroid(this.position);
            asteroid1.setSize(size - 1);
            Asteroid asteroid2 = new Asteroid(this.position);
            asteroid2.setSize(size - 1);
            actorLists.addAsteroid(asteroid1);
            actorLists.addAsteroid(asteroid2);
        }
    }
    
    @Override
    public void collision(BoardActor other) {
        other.asteroidCollision(this);
    }
  
    public void projectileCollision(Projectile projectile) {
        state = BoardActor.State.DYING;
    }
    
    public void setSize(int size) {
        this.size = size;
    }
    
    public int getSize() {
        return size;
    }

    @Override
    public void move(Physics physics, Board board, int timeInterval) {
        if (moveLength <= 0) {
            moveLength = generator.nextInt(20)+5;
            moveRandom = generator.nextInt(10);
        }
        moveLength--;
        if (moveRandom == 0) {
            orientation = orientation.rotate(0.1);
            velocity = velocity.rotate(0.1);
        }
        if (moveRandom == 1) {
            orientation = orientation.rotate(-0.1);
            velocity = velocity.rotate(-0.1);
        }
        Vector2D vector = physics.getMove(mass, velocity, timeInterval);
        position = board.getNewPosition(position, vector);
    }
}
