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
public class Projectile {
    public Point2D pos; 
    public double angle;
    
    public Projectile() {
        pos = new Point2D(0, 0);
        angle = 0;
    }
    
    public Projectile(org.awesometeam.gamelogic.Projectile pr) {
        pos = pr.getPosition();
        angle = pr.getAngle();
    }
}
