package org.awesometeam.gamelogic;

import org.awesometeam.servernetworking.SharedMemoryPlayerNameMapping;

public class Player {

    public enum State {

        ALIVE, DEAD, LEAVING, WAITING_FOR_RESURRECTION, WAITING_FOR_POSITION
    }
    public final static double DEFAULT_TIME_TO_RESURRECTION = 1.5;
    private double timeToResurrection;

    private String name;
    private int id;
    private KeyPresses keyPresses;
    private State state;
    
    private int kills;
    private int deaths;
    private int score;

    public Player(int index) {
        
        kills = 0;
        deaths = 0;
        score = 0;
        
        id = index;
        name = SharedMemoryPlayerNameMapping.getInstance().getName(id);
        
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
        deaths ++;
        System.out.println("ddddddddddddddddddd                      "+deaths);
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
    
    public String getName() {
        return name;
    }

    public void increaseKills() {
        kills ++;
    }
    
    public int getKills() {
        return kills;
    }
    
    public int getDeaths() {
        return deaths;
    }
    
    public int getScore() {
        return score;
    }
    
    public void startWaiting() {
        timeToResurrection = DEFAULT_TIME_TO_RESURRECTION;
        state = State.WAITING_FOR_RESURRECTION;
    }
}