package org.awesometeam;

import java.util.ArrayList;
import math.geom2d.Point2D;
import org.awesometeam.gamelogic.Asteroid;
import org.awesometeam.gamelogic.BoardActor;
import org.awesometeam.gamelogic.Projectile;
import org.awesometeam.gamelogic.Spaceship;
import org.awesometeam.ui.AsteroidRenderer;
import org.awesometeam.ui.SpaceshipRenderer;
import org.awesometeam.ui.ProjectileRenderer;
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
    ArrayList<BoardActor> arr = new ArrayList<>();

    Asteroid ass;

    public GameState() throws SlickException {
        this.net = new ClientSideNetworking();
    }

    @Override
    public int getID() {
        return AsteroidsMP.GAMESTATE;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        AsteroidRenderer.init(gc, sbg, "res/img/asteroid.png");
        SpaceshipRenderer.init(gc, sbg, "res/img/ship.png");
        ProjectileRenderer.init(gc, sbg, "res/img/projectile.png");
        ass = new Asteroid();
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
        for (Asteroid a : GameLogic.getAsteroids()) {
            AsteroidRenderer.render(gc, sbg, grphcs, a);
        }
        for (Spaceship s : GameLogic.getSpaceships()) {
            SpaceshipRenderer.render(gc, sbg, grphcs, s);
        }
        for (Projectile s : GameLogic.getProjectiles()) {
            ProjectileRenderer.render(gc, sbg, grphcs, s);
        }
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        keyPresses[0] = gc.getInput().isKeyDown(Input.KEY_LEFT);
        keyPresses[1] = gc.getInput().isKeyDown(Input.KEY_RIGHT);
        keyPresses[2] = gc.getInput().isKeyDown(Input.KEY_UP);
        keyPresses[3] = gc.getInput().isKeyDown(Input.KEY_DOWN);
        keyPresses[4] = gc.getInput().isKeyDown(Input.KEY_SPACE);
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
