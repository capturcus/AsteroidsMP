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
public class Projectile extends ThinActor{ 
    public double angle;
    
    public Projectile() {
        x = 0;
        y = 0;
        angle = 0;
    }
    
    public Projectile(org.awesometeam.gamelogic.Projectile pr) {
        x = pr.getPosition().getX();
        y = pr.getPosition().getY();
        angle = pr.getAngle();
    }
}
