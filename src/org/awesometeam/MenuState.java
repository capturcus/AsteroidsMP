package org.awesometeam;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.awesometeam.clientnetworking.AsteroidClientMain;
import org.awesometeam.ui.Button;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.gui.GUIContext;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author NoSpacesName
 */
public class MenuState extends BasicGameState {

    Image menu, name, server, stripe1, stripe2;
    Button joinButton, hostButton, optionsButton, exitButton;
    private TextField serverField, nameField;

    public static boolean timeout = false;

    @Override
    public int getID() {
        return AsteroidsMP.MENUSTATE;
    }

    private void beginConnection(GameContainer gc, StateBasedGame sbg, String address) {
        try {
            sbg.enterState(AsteroidsMP.LOADINGSTATE);
            AsteroidClientMain.getInstance().setServerIPToConnect(address);
            AsteroidClientMain.getInstance().startSending(nameField.getText());
            sbg.enterState(AsteroidsMP.GAMESTATE);
        } catch (IOException ex) {
            sbg.enterState(AsteroidsMP.MENUSTATE);
        }
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {

        menu = new Image("res/img/menu.png");
        name = new Image("res/img/name.png");
        server = new Image("res/img/server.png");

        stripe1 = new Image("res/img/pasek1.png");
        stripe2 = new Image("res/img/pasek2.png");

        nameField = new TextField(gc, new TrueTypeFont(new java.awt.Font("Times New Roman", java.awt.Font.PLAIN, 24), false),
                333, 250, 400, 40);

        nameField.setBackgroundColor(Color.transparent);
        nameField.setBorderColor(Color.transparent);

        int step = gc.getHeight() / 13;

        //uwielbiam ten kod <3
        joinButton = new Button(gc, "res/img/joingame.png", 200, step * 2, 200, step * 2, AsteroidsMP.MENUSTATE,
                (() -> {
                    beginConnection(gc, sbg, serverField.getText());
                })
        );
        joinButton.init(gc, sbg);

        hostButton = new Button(gc, "res/img/hostgame.png", 200, step * 5, 200, step * 2, AsteroidsMP.MENUSTATE,
                (() -> {
                    System.out.println("host!");
                    try {
                        (new Server(OptionsState.serverPort)).start();
                        (new GameLogic()).start();
                    } catch (IOException ex) {
                        Logger.getLogger(MenuState.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    beginConnection(gc, sbg, "localhost");
                })
        );
        hostButton.init(gc, sbg);

        optionsButton = new Button(gc, "res/img/options.png", 200, step * 8, 200, step * 2, AsteroidsMP.MENUSTATE,
                (() -> {
                    System.out.println("options!");
                    sbg.enterState(AsteroidsMP.OPTIONSSTATE);
                })
        );
        optionsButton.init(gc, sbg);

        exitButton = new Button(gc, "res/img/gtfo.png", 200, step * 11, 200, step * 2, AsteroidsMP.MENUSTATE,
                (() -> {
                    System.out.println("gtfo!");
                    System.exit(0);
                })
        );
        exitButton.init(gc, sbg);
        serverField = new TextField(gc, new TrueTypeFont(new java.awt.Font("Times New Roman", java.awt.Font.PLAIN, 24), false),
                333, (int) (step * 1.5), 400, 40,
                ((x) -> {
                    beginConnection(gc, sbg, serverField.getText());
                })
        );

        serverField.setBackgroundColor(Color.transparent);
        serverField.setBorderColor(Color.transparent);
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
        menu.draw(0, 0, sbg.getContainer().getWidth(), sbg.getContainer().getHeight());
        joinButton.render(gc, sbg, grphcs);
        hostButton.render(gc, sbg, grphcs);
        optionsButton.render(gc, sbg, grphcs);
        exitButton.render(gc, sbg, grphcs);
        stripe2.draw(327, 59.f * 1.5f - 5);
        stripe1.draw(327, 242);
        serverField.render(gc, grphcs);
        nameField.render(gc, grphcs);
        name.draw(323, 200);
        server.draw(350, 40);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        checkTimeout();
    }

    public static void checkTimeout() {
        if (timeout) {
            timeout = false;
            throw new NullPointerException("timeout");
        }
    }
}
