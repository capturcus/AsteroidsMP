package org.awesometeam.gamelogic;

import math.geom2d.Vector2D;

public class Spaceship extends BoardActor {
	
	private Player player;
	
	public Spaceship(Player pl) {
		player = pl;
	}
	
	public void attack(Projectile weapon) {
		
	}
	
	@Override
	public void die() {
		super.die();
		player.die();
	}
        
        @Override
        public void move(Physics physics, Board board, int timeInterval) {
                KeyPresses keyPresses = player.getKeyPresses();
                
                orientation = orientation.rotate(0.1);
                
                velocity.rotate(1);
		Vector2D vector = physics.getMove(mass, velocity, timeInterval);
		position = board.getNewPosition(position, vector);
	}
}
