package org.awesometeam.gamelogic;

public class Player {

    public enum State {

        ALIVE, DEAD, LEAVING, WAITING_FOR_RESURRECTION, WAITING_FOR_POSITION
    }
    public final static double DEFAULT_TIME_TO_RESURRECTION = 3;
    private double timeToResurrection;
    
    private int id;
    private KeyPresses keyPresses;
    private State state;

    public Player(int index) {
        id = index;
        state = State.WAITING_FOR_RESURRECTION;
        timeToResurrection = DEFAULT_TIME_TO_RESURRECTION;
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
        timeToResurrection = DEFAULT_TIME_TO_RESURRECTION;
    }

    public State getState() {
        return state;
    }

    public void leaveGame() {
        state = State.LEAVING;
    }

    public int getId() {
        return id;
    }
    
    public void waitForPosition(double timeInterval) {
        timeToResurrection -= timeInterval;
        if (timeToResurrection <= 0)
            state = State.WAITING_FOR_POSITION;
    }
    
    public void start() {
        state = State.ALIVE;
    }
}
