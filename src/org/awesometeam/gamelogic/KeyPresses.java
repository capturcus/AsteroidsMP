package org.awesometeam.gamelogic;

import java.io.Serializable;

public class KeyPresses implements Serializable {

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
    
    public KeyPresses(KeyPresses kp) {
        keyPresses = new boolean[NUMBER_OF_KEYS];
    	setKeyPresses(kp.getKeypressesArray());
    }

    public boolean isPressed(int key) {
        return keyPresses[key];
    }
    
    public void setKeyPresses(boolean[] newKeyPresses) {
        System.arraycopy(newKeyPresses, 0, keyPresses, 0, NUMBER_OF_KEYS);
    }
    
    public boolean[] getKeypressesArray(){
    	return keyPresses;
    }
    
    public String toString(){
    	String txt = "";
    	for(int i = 0; i < keyPresses.length; i++){
    		txt += keyPresses[i] + " ";
    	}
    	return txt;
    }

}
