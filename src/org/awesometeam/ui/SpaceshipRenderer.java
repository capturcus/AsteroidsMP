/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.awesometeam.ui;

import math.geom2d.Point2D;
import org.awesometeam.gamelogic.Spaceship;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author Marcin
 */
public class SpaceshipRenderer {

    private final static double SCALE = .6d;
    private static Image img;

    public static void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        img = new Image("res/img/ship.png");
    }

    public static void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs, Spaceship s) throws SlickException {
        Image temp = img.getScaledCopy((float)SCALE);
        temp.setRotation((float) Math.toDegrees(s.getAngle()));
        temp.draw((float) s.getPosition().x(), (float) s.getPosition().y());
    }

}
