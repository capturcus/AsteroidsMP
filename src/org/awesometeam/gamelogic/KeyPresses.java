package org.awesometeam.gamelogic;

public class KeyPresses {

    static final int NUMBER_OF_KEYS = 5;
    static final int LEFT = 0;
    static final int RIGHT = 1;
    static final int UP = 2;
    static final int DOWN = 3;
    static final int ACTION = 4;
    boolean keyPresses[];

    public KeyPresses() {
        keyPresses = new boolean[NUMBER_OF_KEYS];
    }

    public boolean isPressed(int key) {
        return keyPresses[key];
    }
    
    public void setKeyPresses(boolean[] newKeyPresses) {
        System.arraycopy(newKeyPresses, 0, keyPresses, 0, NUMBER_OF_KEYS);
    }

}
