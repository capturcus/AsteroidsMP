package org.awesometeam.gamelogic;

import math.geom2d.Point2D;
import math.geom2d.Vector2D;

public class Projectile extends Asteroid {

    int turnsToDie;

    public Projectile(Point2D pos) {
        position = pos;
        turnsToDie = 20;
        //tmp begin
        size = 0;
        //tmp end
    }
    
    @Override
    public void collision(BoardActor other) {
        other.projectileCollision(this);
    }
    
    public void asteroidCollision(Asteroid asteroid) {
        state = BoardActor.State.DYING;
    }
    
    public void spaceshipCollision(Spaceship spaceship) {
    
    }
    
    public void projectileCollision(Projectile projectile) {
        
    }
    
    public Projectile() {
        this(new Point2D(0 ,0));
    }
    
    public void move(Physics physics, Board board, int timeInterval) {
        Vector2D vector = physics.getMove(mass, velocity, timeInterval);
        position = board.getNewPosition(position, vector);
        turnsToDie--;
        if (turnsToDie <= 0)
            state = BoardActor.State.DYING;
    }

}
