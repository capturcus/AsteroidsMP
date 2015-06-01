package org.awesometeam.gamelogic;

import math.geom2d.Point2D;
import math.geom2d.Vector2D;

public class Projectile extends Obstacle {

    public final static int DAMAGE = 50;
    
    int turnsToDie;
    Spaceship owner;
    
    public Projectile(Point2D pos) {
        position = pos;
        turnsToDie = 40;
        //tmp begin
        //size = 0;
        radius = 10;
        //tmp end
    }
    
    public void setOwner(Spaceship owner) {
        this.owner = owner;
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
        if (injure(Asteroid.DAMAGE)) {
            if (asteroid.getSize() == 0)
                owner.increaseScore(Spaceship.SCORE_FOR_SMALL_ASTEROID);
            else
                owner.increaseScore(Spaceship.SCORE_FOR_BIG_ASTEROID);
        }
    }
    
    public void spaceshipCollision(Spaceship spaceship) {
        if (injure(Spaceship.DAMAGE))
            owner.increaseScore(Spaceship.SCORE_FOR_SPACESHIP);
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

    @Override
    public void increaseKills() {
        owner.increaseKills();
    }
    
}
