package org.awesometeam;

import java.util.ArrayList;
import org.awesometeam.ui.Actor;
import org.awesometeam.ui.Spaceship;
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

    private final static boolean[] keyPresses = new boolean[5];
    private ClientSideNetworking net;
    ArrayList<Actor> arr = new ArrayList<>();

    public GameState() throws SlickException {
        this.net = new ClientSideNetworking();
    }

    @Override
    public int getID() {
        return AsteroidsMP.GAMESTATE;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        arr.clear();
        arr.addAll(ClientSideNetworking.getAsteroids());
        arr.addAll(ClientSideNetworking.getProjectiles());
        arr.addAll(ClientSideNetworking.getSpaceships());
        for (Actor a : arr) {
            a.init(gc, sbg);
        }
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
        for (Actor a : arr) {
            a.render(gc, sbg, grphcs);
        }
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        keyPresses[0] = gc.getInput().isKeyDown(Input.KEY_LEFT);
        keyPresses[1] = gc.getInput().isKeyDown(Input.KEY_RIGHT);
        keyPresses[2] = gc.getInput().isKeyDown(Input.KEY_UP);
        keyPresses[3] = gc.getInput().isKeyDown(Input.KEY_DOWN);
        keyPresses[4] = gc.getInput().isKeyDown(Input.KEY_SPACE);
        arr.clear();
        arr.addAll(ClientSideNetworking.getAsteroids());
        arr.addAll(ClientSideNetworking.getProjectiles());
        arr.addAll(ClientSideNetworking.getSpaceships());
        for (Actor a : arr) {
            a.update(gc, sbg, i);
        }
    }

    /**
     * Returns key presses.
     *
     * @return 5 element boolean table; 0-left, 1-right, 2-up, 3-down, 4-space
     */
    public static synchronized boolean[] getKeyPresses() {
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
