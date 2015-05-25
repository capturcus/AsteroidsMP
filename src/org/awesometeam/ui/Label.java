/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.awesometeam.ui;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.GUIContext;

/**
 *
 * @author marcin
 */
public class Label extends AbstractComponent {

    int x, y;
    public String text;
    
    public Label(GUIContext container) {
        super(container);
    }

    @Override
    public void render(GUIContext guic, Graphics grphcs) throws SlickException {
        grphcs.drawString(text, x, y);
    }

    @Override
    public void setLocation(int i, int i1) {
        x = i;
        y = i1;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public int getWidth() {
        return 0;
    }

    @Override
    public int getHeight() {
        return 0;
    }
}
