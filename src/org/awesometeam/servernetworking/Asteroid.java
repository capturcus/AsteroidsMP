/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.awesometeam.servernetworking;

import math.geom2d.Point2D;

/**
 *
 * @author michal
 */
public class Asteroid extends ThinActor {
    public int size;
    
    public Asteroid() {
        pos = new Point2D(0, 0);
        size = 0;
    }
    
    public Asteroid(Point2D p, int s) {
        pos = new Point2D(p);
        size = s;
    }
    
    public Asteroid(org.awesometeam.gamelogic.Asteroid as)
    {
        pos = as.getPosition();
        size = as.getSize();
    }
}
