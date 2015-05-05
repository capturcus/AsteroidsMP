package org.awesometeam.gamelogic;

public class Player {

    public enum State {

        ALIVE, DEAD, LEAVING
    }

    private int id;
    private KeyPresses keyPresses;
    private State state;

    public Player(int index) {
        id = index;
        state = State.ALIVE;
        keyPresses = new KeyPresses();
    }

    public void setKeyPresses(KeyPresses newKeyPresses) {
        keyPresses = newKeyPresses;
    }

    public KeyPresses getKeyPresses() {
        return keyPresses;
    }

    public void die() {
        state = State.DEAD;
    }

    public State getState() {
        return state;
    }

    public void leaveGame() {
        state = State.LEAVING;
    }

}
