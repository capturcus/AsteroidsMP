package org.awesometeam.gamelogic;

public class KeyPresses {

	static final int NUMBER_OF_KEYS = 5;
	boolean keyPresses[];
	
	public KeyPresses() {
		keyPresses = new boolean[NUMBER_OF_KEYS];
	}
	
	public void setKeyPresses(boolean newKeyPresses) {
		System.arraycopy(newKeyPresses, 0, keyPresses, 0, NUMBER_OF_KEYS);
	}
	
}
