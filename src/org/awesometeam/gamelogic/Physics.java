package org.awesometeam.gamelogic;

import math.geom2d.Vector2D;

public class Physics {

    public Vector2D getMove(int mass, Vector2D velocity, double timeInterval) {
        timeInterval = 0.5;
        //velocity = new Vector2D(10, 0);
        return new Vector2D(velocity.getX() * timeInterval, velocity.getY() * timeInterval);
        //return new Vector2D(10, 0);
    }

}
