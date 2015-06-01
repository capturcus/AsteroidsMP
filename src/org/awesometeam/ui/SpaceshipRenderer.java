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
    private final static float THRUST = 60;

    public static Image img;
    public static Image thrust;

    public static void init(GameContainer gc, StateBasedGame sbg, String path) throws SlickException {
        img = new Image(path);
        thrust = new Image("res/img/thrust.png");
    }

    public static void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs, Spaceship s) throws SlickException {
        if (s.thrust) {
            thrust.setRotation((float) Math.toDegrees(s.angle) + 180);
            thrust.draw((float) s.x - img.getWidth() / 4 + (float) Math.cos(s.angle - Math.PI / 2) * THRUST,
                    (float) s.y - img.getHeight() / 4 + (float) Math.sin(s.angle - Math.PI / 2) * THRUST);
        }
        render(gc, sbg, grphcs, s, (float) SCALE, (float) s.angle, img);
//        System.out.println("CLIENT SIDE: " + s);
        grphcs.drawString(s.name + " K: " + s.kills + " D: " + s.deaths + " S: " + s.score,
                (float) s.x - img.getWidth() / 2, (float) s.y - img.getHeight() / 2);
    }
}
