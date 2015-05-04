package org.awesometeam;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.awesometeam.ui.Button;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author NoSpacesName
 */
public class MenuState extends BasicGameState {

    Image menu;
    Button joinButton, hostButton, optionsButton, exitButton;

    @Override
    public int getID() {
        return AsteroidsMP.MENUSTATE;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {

        menu = new Image("res/img/menu.jpg");

        int step = gc.getHeight() / 13;

        //uwielbiam ten kod <3
        joinButton = new Button("res/img/join.jpg", gc.getWidth() / 2, step * 2, 200, step * 2,
                (() -> {
                    sbg.enterState(AsteroidsMP.LOADINGSTATE);
                    (new Thread() {
                        @Override
                        public void run() {
                            for (int i = 0; i < 0; i++) {
                                LoadingState.message("loading... " + (i + 1));
                                try {
                                    Thread.sleep(1000);
                                } catch (InterruptedException ex) {
                                    Logger.getLogger(MenuState.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                            sbg.enterState(AsteroidsMP.GAMESTATE);
                        }
                    }).start();
                    //this Thread is just a test, actually here will be a simple call to the networking class
                    //to start connecting
                    System.out.println("join!");
                })
        );
        joinButton.init(gc, sbg);

        hostButton = new Button("res/img/host.jpg", gc.getWidth() / 2, step * 5, 200, step * 2,
                (() -> {
                    System.out.println("host!");
                    //TODO change to networking
                    GameLogic gl = new GameLogic();
                    gl.start();
                })
        );
        hostButton.init(gc, sbg);

        optionsButton = new Button("res/img/options.jpg", gc.getWidth() / 2, step * 8, 200, step * 2,
                (() -> {
                    System.out.println("options!");
                })
        );
        optionsButton.init(gc, sbg);

        exitButton = new Button("res/img/quit.jpg", gc.getWidth() / 2, step * 11, 200, step * 2,
                (() -> {
                    System.out.println("gtfo!");
                    System.exit(0);
                })
        );
        exitButton.init(gc, sbg);
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
        menu.draw(0, 0, sbg.getContainer().getWidth(), sbg.getContainer().getHeight());
        joinButton.render(gc, sbg, grphcs);
        hostButton.render(gc, sbg, grphcs);
        optionsButton.render(gc, sbg, grphcs);
        exitButton.render(gc, sbg, grphcs);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
    }

}
