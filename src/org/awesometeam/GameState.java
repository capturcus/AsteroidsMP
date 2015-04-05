package org.awesometeam;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author NoSpacesName
 */
public class GameState extends BasicGameState {

    private final boolean[] keyPresses = new boolean[5];
    private ClientSideNetworking net = new ClientSideNetworking(this);

    @Override
    public int getID() {
        return AsteroidsMP.GAMESTATE;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        keyPresses[0] = gc.getInput().isKeyDown(Input.KEY_LEFT);
        keyPresses[1] = gc.getInput().isKeyDown(Input.KEY_RIGHT);
        keyPresses[2] = gc.getInput().isKeyDown(Input.KEY_UP);
        keyPresses[3] = gc.getInput().isKeyDown(Input.KEY_DOWN);
        keyPresses[4] = gc.getInput().isKeyDown(Input.KEY_SPACE);
    }

    public boolean[] getKeyPresses() {
        return keyPresses;
    }

    @Override
    public void enter(GameContainer container, StateBasedGame game) throws SlickException {
        net.startSending();
    }

    @Override
    public void leave(GameContainer container, StateBasedGame game) throws SlickException {
        net.stopSending();
    }
}
