package org.awesometeam.gamelogic;

import static java.lang.Math.PI;
import math.geom2d.Point2D;
import math.geom2d.Vector2D;

public class Physics {

    public Vector2D getMove(int mass, Vector2D velocity, double timeInterval) {
        //velocity = new Vector2D(10, 0);
        return velocity.times(timeInterval);
        //return new Vector2D(10, 0);
    }

    public Vector2D bounce(Point2D myPos, Vector2D myVelocity, Point2D otherPos) {
        Vector2D vec = new Vector2D(myPos, otherPos);
        double angle = PI / 2 - (myVelocity.angle() - vec.angle());
        return myVelocity.rotate(2 * angle);
    }
}
