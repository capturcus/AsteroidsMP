package org.awesometeam.ui;

import org.awesometeam.IInitUpdateRender;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.MouseListener;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author NoSpacesName
 */
public class Button implements IInitUpdateRender, MouseListener {

    Image img;
    String path;
    float x, y, width, height;
    Void2VoidFunction f;
    StateBasedGame sbg;

    public Button(String path, float x, float y, float width, float height, Void2VoidFunction f) {
        this.path = path;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.f = f;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        img = new Image(path);
        gc.getInput().addMouseListener(this);
        this.sbg = sbg;
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
        img.draw(x - width / 2, y - height / 2, width, height);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {

    }

    @Override
    public void mouseWheelMoved(int i) {
    }

    @Override
    public void mouseClicked(int button, int x, int y, int clickCount) {
        if (x > this.x - width / 2
                && y > this.y - height / 2
                && x < this.x + width / 2
                && y < this.y + height / 2
                && sbg.getCurrentStateID() == org.awesometeam.AsteroidsMP.MENUSTATE) {
            f.work();
        }
    }

    @Override
    public void mousePressed(int i, int i1, int i2) {
    }

    @Override
    public void mouseReleased(int i, int i1, int i2) {
    }

    @Override
    public void mouseMoved(int i, int i1, int i2, int i3) {
    }

    @Override
    public void mouseDragged(int i, int i1, int i2, int i3) {
    }

    @Override
    public void setInput(Input input) {
    }

    @Override
    public boolean isAcceptingInput() {
        return true;
    }

    @Override
    public void inputEnded() {
    }

    @Override
    public void inputStarted() {
    }

}
