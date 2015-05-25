package org.awesometeam.gamelogic;

import math.geom2d.Point2D;
import math.geom2d.Vector2D;

public class Projectile extends Obstacle {

    public final static int DAMAGE = 50;
    
    int turnsToDie;

    public Projectile(Point2D pos) {
        position = pos;
        turnsToDie = 40;
        //tmp begin
        //size = 0;
        radius = 10;
        //tmp end
    }
    
    @Override
    public void die() {
        state = State.DEAD;
        actorLists.removeProjectile(this);
    }
    
    @Override
    public void collision(BoardActor other) {
        other.projectileCollision(this);
    }
    
    public void asteroidCollision(Asteroid asteroid) {
        injure(Asteroid.DAMAGE);
    }
    
    public void spaceshipCollision(Spaceship spaceship) {
        injure(Spaceship.DAMAGE);
    }
    
    public void projectileCollision(Projectile projectile) {
        
    }
    
    public Projectile() {
        this(new Point2D(0 ,0));
    }
    
    @Override
    public void move(double timeInterval) {
        Vector2D vector = actorLists.getPhyscics().getMove(mass, velocity, timeInterval);
        position = actorLists.getBoard().getNewPosition(position, vector);
        turnsToDie--;
        if (turnsToDie <= 0)
            state = BoardActor.State.DYING;
    }

}
