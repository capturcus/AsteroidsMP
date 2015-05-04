/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.awesometeam.ui;

import math.geom2d.Point2D;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author Marcin
 */
public class Asteroid extends Actor {

    int size;
    private final static double SCALE = .2d;

    public Asteroid(int size, Point2D pos, double angle) throws SlickException {
        super(pos, angle, new Image("res/img/asteroid.png"));
        this.size = size;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
    }

    private float sizeToCoefficient(int size) {
        return .2f * size + .2f;
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
        float finalScale = (float) (SCALE*sizeToCoefficient(size));
        img.setRotation((float) Math.toDegrees(angle));
        Image temp = img.getScaledCopy(finalScale);
        temp.setRotation((float) Math.toDegrees(angle));
        temp.draw((float) pos.x() + (img.getWidth() / 2.f) * (1 - finalScale), (float) pos.y() + (img.getHeight() / 2.f) * (1 - finalScale));
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
    }

}
