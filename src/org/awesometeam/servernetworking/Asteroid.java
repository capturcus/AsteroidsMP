/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.awesometeam.servernetworking;

import java.io.Serializable;
import math.geom2d.Point2D;

/**
 *
 * @author michal
 */
public class Asteroid extends ThinActor implements Serializable {
    public int size;
    
    public Asteroid() {
        x = 0;
        y = 0;
        size = 0;
    }
    
    public Asteroid(org.awesometeam.gamelogic.Asteroid as)
    {
        x = as.getPosition().getX();
        y = as.getPosition().getY();
        size = as.getSize();
    }
}
