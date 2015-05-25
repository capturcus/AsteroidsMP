package org.awesometeam.ui;

import org.awesometeam.IInitUpdateRender;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.MouseListener;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.GUIContext;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author NoSpacesName
 */
public class Button extends AbstractComponent implements IInitUpdateRender, MouseListener {

    Image img;
    String path;
    float x, y, width, height;
    Void2VoidFunction f;
    StateBasedGame sbg;
    int state;

    public Button(GUIContext gc, String path, float x, float y, float width, float height, int state, Void2VoidFunction f) {
        super(gc);
        this.path = path;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.f = f;
        this.state = state;
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
    public void render(GUIContext guic, Graphics grphcs) throws SlickException {
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
                && sbg.getCurrentStateID() == state) {
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

    @Override
    public void setLocation(int i, int i1) {
        x = i;
        y = i1;
    }

    @Override
    public int getX() {
        return (int) x;
    }

    @Override
    public int getY() {
        return (int) y;
    }

    @Override
    public int getWidth() {
        return 0;
    }

    @Override
    public int getHeight() {
        return 0;
    }

}
