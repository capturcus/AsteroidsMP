package org.awesometeam.gamelogic;

import math.geom2d.Vector2D;

public class Spaceship extends BoardActor {
    
    
    private Player player;

    public Spaceship(Player pl) {
        radius = 30;
        player = pl;
    }

    @Override
    public boolean isAttacking() {
        return player.getKeyPresses().isPressed(KeyPresses.ACTION);
    }

    @Override
    public void die() {
        super.die();
        player.die();
    }

    @Override
    public void collision(BoardActor other) {
        other.spaceshipCollision(this);
    }
    
    public void asteroidCollision(Asteroid asteroid) {
        state = BoardActor.State.DYING;
    }
    
    @Override
    public void move(Physics physics, Board board, int timeInterval) {
        KeyPresses keyPresses = player.getKeyPresses();
        if (keyPresses.isPressed(KeyPresses.UP)) {
            if (velocity.norm() > 0) {
            velocity = velocity.plus(velocity.normalize().times(2));
            } else {
                velocity = orientation.normalize().times(2);
            }
        }
        if (keyPresses.isPressed(KeyPresses.DOWN)) {
            if (velocity.norm() > 1 * 2) {
                velocity = velocity.minus(velocity.normalize().times(2));
            } else {
                velocity = new Vector2D(0, 0);
            }
        }
        if (keyPresses.isPressed(KeyPresses.RIGHT)) {
            orientation = orientation.rotate(0.1);
            velocity = velocity.rotate(0.1);
        }
        if (keyPresses.isPressed(KeyPresses.LEFT)) {
            orientation = orientation.rotate(-0.1);
            velocity = velocity.rotate(-0.1);
        }
        Vector2D vector = physics.getMove(mass, velocity, timeInterval);
        position = board.getNewPosition(position, vector);
    }
}
