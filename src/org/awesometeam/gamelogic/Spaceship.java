package org.awesometeam.gamelogic;

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
	
}
