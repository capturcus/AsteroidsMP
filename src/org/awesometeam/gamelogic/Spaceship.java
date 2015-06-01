package org.awesometeam.gamelogic;

import math.geom2d.Point2D;
import static java.lang.Math.max;
import java.util.ArrayList;
import math.geom2d.Vector2D;

public class Spaceship extends BoardActor {
    
    public final static double FORWARD_ACCELERATION = 200;
    public final static double BACKWARD_ACCELERATION = 100;
    public final static double MAX_VELOCITY = 400;
    
    public final static double ATTACK_INTERVAL = 0.4;
    public final static int START_HP = 10000;
    public final static int DAMAGE = 10;
    
    private double timeToNextAttack;
    private Player player;

    public Spaceship() {
        super(new Point2D(200, 200));
    }
    
    public Spaceship(Player pl) {
        healthPoints = START_HP;
        radius = 50;
        player = pl;
        timeToNextAttack = 0;
    }

    @Override
    public ArrayList<Projectile> attack(/*Projectile weapon*/) {
        
        timeToNextAttack = ATTACK_INTERVAL;
        ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
        Projectile projectile = new Projectile();
        projectile.setPosition(position.plus(orientation.normalize().times(10 + radius + projectile.getRadius())));
        projectile.setOrientation(orientation);
        projectile.setVelocity(velocity.plus(orientation.normalize().times(400)));
        projectile.setVelocity(velocity.plus(orientation.normalize().times(200)));
        projectiles.add(projectile);
        
        actorLists.addProjectile(projectile);
        
        return projectiles;
    }
    
    @Override
    public boolean isAttacking() {
        if (timeToNextAttack > 0)
            return false;
        return player.getKeyPresses().isPressed(KeyPresses.ACTION);
    }

    @Override
    public void die() {
        state = State.DEAD;
        player.die();
        actorLists.removeSpaceship(this);
    }

    @Override
    public void collision(BoardActor other) {
        other.spaceshipCollision(this);
    }
    
    @Override
    public void asteroidCollision(Asteroid asteroid) {
        bounce(asteroid);
        injure(Asteroid.DAMAGE);
    }
    
    public void spaceshipCollision(Spaceship spaceship) {
        bounce(spaceship);
        injure(Spaceship.DAMAGE);
    }
    
    public void projectileCollision(Projectile projectile) {
        injure(Projectile.DAMAGE);
    }
    
    @Override
    public void move(double timeInterval) {
        timeToNextAttack = max(0, timeToNextAttack - timeInterval);
        //
        //if (player.getId() != 0)
         //   return;
        //
        
        KeyPresses keyPresses = player.getKeyPresses();
        if (keyPresses.isPressed(KeyPresses.UP)) {
            Vector2D newVelocity = velocity.plus(/*velocity*/orientation.normalize().times(FORWARD_ACCELERATION * timeInterval));
            if (newVelocity.norm() < MAX_VELOCITY)
                velocity = newVelocity;
        }
        if (keyPresses.isPressed(KeyPresses.DOWN)) {
            Vector2D newVelocity = velocity.minus(/*velocity*/orientation.normalize().times(BACKWARD_ACCELERATION * timeInterval));
            if (newVelocity.norm() < MAX_VELOCITY)
                velocity = newVelocity;
        }
        if (keyPresses.isPressed(KeyPresses.RIGHT)) {
            orientation = orientation.rotate(0.1);
            //velocity = velocity.rotate(0.1);
        }
        if (keyPresses.isPressed(KeyPresses.LEFT)) {
            orientation = orientation.rotate(-0.1);
            //velocity = velocity.rotate(-0.1);
        }

        Vector2D vector = actorLists.getPhyscics().getMove(mass, velocity, timeInterval);
        position = actorLists.getBoard().getNewPosition(position, vector);
    }
    
    public void start() {
        player.start();
    }

    public int getID() {
        return player.getId();
    }
}
