package org.awesometeam.ui;

import org.awesometeam.servernetworking.Projectile;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author NoSpacesName
 */
public class ProjectileRenderer extends Renderer {

    private final static double SCALE = .2d;
    public static Image img;

    public static void init(GameContainer gc, StateBasedGame sbg, String path) throws SlickException {
        img = new Image(path);
    }

    public static void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs, Projectile p) throws SlickException {
        Image temp = img.getScaledCopy((float) SCALE);
        temp.setRotation((float) Math.toDegrees(p.angle) - 90);
        temp.draw((float) p.x - (temp.getWidth() / 2.f), (float) p.y - (temp.getHeight() / 2.f));
    }
}
