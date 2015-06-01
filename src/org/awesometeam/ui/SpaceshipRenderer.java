/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.awesometeam.ui;

import math.geom2d.Point2D;
import org.awesometeam.servernetworking.Spaceship;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author Marcin
 */
public class SpaceshipRenderer extends Renderer {

    private final static double SCALE = .6d;

    public static Image img;

    public static void init(GameContainer gc, StateBasedGame sbg, String path) throws SlickException {
        img = new Image(path);
    }

    public static void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs, Spaceship s) throws SlickException {
        render(gc, sbg, grphcs, s, (float) SCALE, (float) s.angle, img);
        grphcs.drawString(s.name, (float) s.x, (float) s.y);
        System.out.println("name" + s.name);
    }
}
