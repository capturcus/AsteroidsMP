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
public class AsteroidRenderer extends Renderer {

    private final static double SCALE = .2d;
    public static Image img;
    
    public static void init(GameContainer gc, StateBasedGame sbg, String path) throws SlickException {
        img = new Image(path);
    }
    
    private static float sizeToCoefficient(int size) {
        return .2f * size + .2f;
    }

    public static void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs, Asteroid a) throws SlickException {
        float finalScale = (float) (SCALE * sizeToCoefficient(a.getSize()));
        render(gc, sbg, grphcs, a, finalScale, img);
    }
}
