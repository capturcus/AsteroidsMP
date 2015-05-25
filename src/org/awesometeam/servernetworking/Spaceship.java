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
public class Spaceship extends ThinActor implements Serializable {
    public double angle;
    public String name;
    public int ID;
    
    public Spaceship() {
        x = 0;
        y = 0;
        angle = 0;
        name = "";
        ID = 0;
    }
    
    public Spaceship(org.awesometeam.gamelogic.Spaceship sp){
        x = sp.getPosition().getX();
        y = sp.getPosition().getY();
        angle = sp.getAngle();
        name = "";
        ID = 0;
    }
}
