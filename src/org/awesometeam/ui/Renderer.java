/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.awesometeam.ui;

import org.awesometeam.gamelogic.Asteroid;
import org.awesometeam.gamelogic.BoardActor;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author marcin
 */
public abstract class Renderer {

    public static void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs, BoardActor a, float scale, Image img) throws SlickException {
        Image temp = img.getScaledCopy(scale);
        temp.setRotation((float) Math.toDegrees(a.getAngle()));
        temp.draw((float) a.getPosition().x() - (temp.getWidth() / 2.f), (float) a.getPosition().y() - (temp.getHeight() / 2.f));
        //temp.draw((float) a.getPosition().x(), (float) a.getPosition().y());
    }
}
