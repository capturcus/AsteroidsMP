/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.awesometeam.ui;

import math.geom2d.Point2D;
import org.awesometeam.IInitUpdateRender;
import org.newdawn.slick.Image;

/**
 *
 * @author Marcin
 */
public abstract class Actor implements IInitUpdateRender {

    protected Point2D pos;
    protected double angle;
    protected Image img;

    public Actor(Point2D pos, double angle, Image img) {
        this.pos = pos;
        this.angle = angle;
        this.img = img;
    }

}
