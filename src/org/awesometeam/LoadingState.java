package org.awesometeam;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author NoSpacesName
 */
public class LoadingState extends BasicGameState {

    private static LoadingState instance = null;

    private static String messages = "";
    static int lines = 0;

    public static LoadingState getInstance() {
        if (instance == null) {
            instance = new LoadingState();
        }
        return instance;
    }

    public synchronized static void message(String str) {
        System.out.println(str);
        messages += str;
        messages += "\n";
        lines++;
    }

    @Override
    public int getID() {
        return AsteroidsMP.LOADINGSTATE;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        g.drawString(messages, (gc.getWidth() *.45f), (gc.getHeight()*.9f) - lines * 20);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        MenuState.checkTimeout();
    }

}
