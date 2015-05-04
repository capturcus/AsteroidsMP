package org.awesometeam.gamelogic;

import java.util.Random;

public class Asteroid extends Obstacle {
	
	private int size;
	public static final Random generator = new Random();
	
	public Asteroid() {
		size = generator.nextInt(3);
	}
	
	//
	public int getSize() {
		//return size;
            return 3;
	}
}
