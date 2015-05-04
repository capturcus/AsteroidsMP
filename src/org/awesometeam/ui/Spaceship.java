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
public class Spaceship extends Actor {

    int id;
    int hp;
    boolean isMe;

    private final static double SCALE = .6d;

    public Spaceship(int id, int hp, boolean isMe, Point2D pos, double angle) throws SlickException {
        super(pos, angle, new Image("res/img/ship.png"));
        this.id = id;
        this.hp = hp;
        this.isMe = isMe;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
        img.setRotation((float) Math.toDegrees(angle));
        img.draw((float) pos.x(), (float) pos.y(), (float) SCALE);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
    }

}
