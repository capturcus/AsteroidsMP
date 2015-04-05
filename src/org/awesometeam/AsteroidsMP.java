/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.awesometeam;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author NoSpacesName
 */
public class AsteroidsMP extends StateBasedGame {

    final static int FPS = 60;
    final static int WIDTH = 1024;
    final static int HEIGHT = 768;

    public final static int MENUSTATE = 0;
    public final static int GAMESTATE = 1;
    public final static int LOADINGSTATE = 2;

    /**
     * @param args the command line arguments
     * @throws org.newdawn.slick.SlickException
     */
    public static void main(String[] args) throws SlickException {
        AppGameContainer container = new AppGameContainer(new AsteroidsMP("Dis gaem b dopE!"), WIDTH, HEIGHT, false);
        container.setTargetFrameRate(FPS);
        container.setShowFPS(true);
        container.start();
    }

    public AsteroidsMP(String name) {
        super(name);
    }

    @Override
    public void initStatesList(GameContainer gc) throws SlickException {
        addState(new MenuState());
        addState(new GameState());
        addState(new LoadingState());
        enterState(MENUSTATE);
    }

}
