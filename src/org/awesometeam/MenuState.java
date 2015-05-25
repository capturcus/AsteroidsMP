package org.awesometeam;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.awesometeam.ui.Button;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author NoSpacesName
 */
public class MenuState extends BasicGameState {

    Image menu;
    Button joinButton, hostButton, optionsButton, exitButton;
    private TextField textField;

    @Override
    public int getID() {
        return AsteroidsMP.MENUSTATE;
    }

    private void beginConnection(GameContainer gc, StateBasedGame sbg, String address) {
        sbg.enterState(AsteroidsMP.LOADINGSTATE);
        AsteroidClientMain.getInstance().setServerIPToConnect(address);
        AsteroidClientMain.getInstance().startSending();
        sbg.enterState(AsteroidsMP.GAMESTATE);
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {

        menu = new Image("res/img/menu.jpg");

        int step = gc.getHeight() / 13;

        //uwielbiam ten kod <3
        joinButton = new Button("res/img/join.jpg", 200, step * 2, 200, step * 2,
                (() -> {
                    beginConnection(gc, sbg, textField.getText());
                })
        );
        joinButton.init(gc, sbg);

        hostButton = new Button("res/img/host.jpg", 200, step * 5, 200, step * 2,
                (() -> {
                    System.out.println("host!");
                    try {
                        (new Server()).start();
                    } catch (IOException ex) {
                        Logger.getLogger(MenuState.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    beginConnection(gc, sbg, "localhost");
                })
        );
        hostButton.init(gc, sbg);

        optionsButton = new Button("res/img/options.jpg", 200, step * 8, 200, step * 2,
                (() -> {
                    System.out.println("options!");
                    sbg.enterState(AsteroidsMP.OPTIONSSTATE);
                })
        );
        optionsButton.init(gc, sbg);

        exitButton = new Button("res/img/quit.jpg", 200, step * 11, 200, step * 2,
                (() -> {
                    System.out.println("gtfo!");
                    System.exit(0);
                })
        );
        exitButton.init(gc, sbg);
        textField = new TextField(gc, new TrueTypeFont(new java.awt.Font("Times New Roman", java.awt.Font.PLAIN, 24), false),
                333, (int) (step * 1.5), 400, 40,
                ((x) -> {
                    beginConnection(gc, sbg, textField.getText());
                })
        );
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
        menu.draw(0, 0, sbg.getContainer().getWidth(), sbg.getContainer().getHeight());
        joinButton.render(gc, sbg, grphcs);
        hostButton.render(gc, sbg, grphcs);
        optionsButton.render(gc, sbg, grphcs);
        exitButton.render(gc, sbg, grphcs);
        textField.render(gc, grphcs);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
    }

}
