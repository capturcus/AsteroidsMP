package org.awesometeam;

import org.awesometeam.gamelogic.KeyPresses;
import org.awesometeam.servernetworking.Asteroid;
import org.awesometeam.servernetworking.Projectile;
import org.awesometeam.servernetworking.ServerSentData;
import org.awesometeam.servernetworking.Spaceship;
import org.awesometeam.ui.AsteroidRenderer;
import org.awesometeam.ui.SpaceshipRenderer;
import org.awesometeam.ui.ProjectileRenderer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
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

    private Image background;
    
    public GameState() throws SlickException {

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
        
        background = new Image("res/img/background.jpg");
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
        background.draw();
        ServerSentData ssd = SharedMemoryClientReceived.getInstance().getData();
        for (Spaceship s : ssd.spaceships) {
            SpaceshipRenderer.render(gc, sbg, grphcs, s);
        }
        for (Projectile s : ssd.projectiles) {
            ProjectileRenderer.render(gc, sbg, grphcs, s);
        }
        for (Asteroid a : ssd.asteroids) {
            AsteroidRenderer.render(gc, sbg, grphcs, a);
        }
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        keyPresses[0] = gc.getInput().isKeyDown(Input.KEY_LEFT);
        keyPresses[1] = gc.getInput().isKeyDown(Input.KEY_RIGHT);
        keyPresses[2] = gc.getInput().isKeyDown(Input.KEY_UP);
//        keyPresses[3] = gc.getInput().isKeyDown(Input.KEY_DOWN);
        keyPresses[3] = false;
        keyPresses[4] = gc.getInput().isKeyDown(Input.KEY_SPACE);
        KeyPresses kp = new KeyPresses();
        kp.setKeyPresses(keyPresses);
        SharedMemoryClientSent.getInstance().writeData(kp);
        MenuState.checkTimeout();
    }

    @Override
    public void enter(GameContainer container, StateBasedGame game) throws SlickException {
    }

    @Override
    public void leave(GameContainer container, StateBasedGame game) throws SlickException {
    }
}
