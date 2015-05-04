/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.awesometeam.ui;

import math.geom2d.Point2D;
import org.awesometeam.gamelogic.Asteroid;
import org.newdawn.slick.BigImage;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author Marcin
 */
public class AsteroidRenderer {

    private final static double SCALE = .2d;
    private static Image img;

    private static float sizeToCoefficient(int size) {
        return .2f * size + .2f;
    }

    public static void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        img = new Image("res/img/asteroid.png");
    }

    public static void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs, Asteroid a) throws SlickException {
        float finalScale = (float) (SCALE * sizeToCoefficient(a.getSize()));
        Image temp = img.getScaledCopy(finalScale);
        temp.setRotation((float) Math.toDegrees(a.getAngle()));
        //temp.draw((float) a.getPosition().x() + (img.getWidth() / 2.f) * (1 - finalScale), (float) a.getPosition().y() + (img.getHeight() / 2.f) * (1 - finalScale));
        temp.draw((float) a.getPosition().x(), (float) a.getPosition().y());
    }
}
