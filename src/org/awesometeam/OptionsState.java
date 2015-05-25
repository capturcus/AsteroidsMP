/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.awesometeam;

import java.util.ArrayList;
import org.awesometeam.ui.Button;
import org.awesometeam.ui.Label;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author marcin
 */
public class OptionsState extends BasicGameState {

    Image background = null;
    ArrayList<AbstractComponent> components;
    public static int serverPort = 13100;

    @Override
    public int getID() {
        return AsteroidsMP.OPTIONSSTATE;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        components = new ArrayList<>();
        background = new Image("res/img/gears.jpg");
        TextField textField = new TextField(gc, new TrueTypeFont(new java.awt.Font("Times New Roman", java.awt.Font.PLAIN, 24), false),
                260, 100, 100, 40);
        textField.setText(String.valueOf(serverPort));
        components.add(textField);
        components.add(new Label(gc, 100, 110, "host server port: "));
        Button b = new Button(gc, "res/img/back.png", gc.getWidth() - 150, gc.getWidth() - 400, 100, 100, AsteroidsMP.OPTIONSSTATE,
                () -> {
                    serverPort = Integer.valueOf(textField.getText());
                    sbg.enterState(AsteroidsMP.MENUSTATE);
                }
        );
        b.init(gc, sbg);
        components.add(b);
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
        background.draw(0, 0, gc.getWidth(), gc.getHeight());
        for (AbstractComponent component : components) {
            component.render(gc, grphcs);
        }
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
    }

}
