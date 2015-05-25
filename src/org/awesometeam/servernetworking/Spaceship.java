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
public class Spaceship {
    public Point2D pos;
    public double angle;
    public String name;
    public int ID;
    
    public Spaceship() {
        pos = new Point2D(0,0);
        angle = 0;
        name = "";
        ID = 0;
    }
    
    public Spaceship(org.awesometeam.gamelogic.Spaceship sp){
        pos = sp.getPosition();
        angle = sp.getAngle();
        name = "";
        ID = 0;
    }
}
