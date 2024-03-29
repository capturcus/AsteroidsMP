package org.awesometeam.gamelogic;

import math.geom2d.Point2D;
import static java.lang.Math.max;
import java.util.ArrayList;
import math.geom2d.Vector2D;

public class Spaceship extends BoardActor {
    
    public final static double FORWARD_ACCELERATION = 300;
    public final static double BACKWARD_ACCELERATION = 100;
    public final static double MAX_VELOCITY = 400;
    
    public final static double ATTACK_INTERVAL = 0.3;
    public final static int START_HP = 100;
    public final static int DAMAGE = 10;
    public final static double ANGULAR_VELOCITY = 0.2;
    
    public final static int SCORE_FOR_NOT_SMALL_ASTEROID = 1;
    public final static int SCORE_FOR_SMALL_ASTEROID = 2;
    public final static int SCORE_FOR_SPACESHIP = 5;
    
    public final static int DEFAULT_RADIUS = 50;
    
    public final static int START_INTERSPACE = 15;
    
    private double timeToNextAttack;
    private Player player;

    public Spaceship() {
        super(new Point2D(200, 200));
    }
    
    public Spaceship(Player pl) {
        healthPoints = START_HP;
        radius = DEFAULT_RADIUS;
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
        //projectile.setVelocity(velocity.plus(orientation.normalize().times(200)));
        projectiles.add(projectile);
        
        projectile.setOwner(this);
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
        if (killer != null)
            killer.increaseKills();
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
        if (healthPoints <= 0)
            killer = asteroid;
    }
    
    @Override
    public void spaceshipCollision(Spaceship spaceship) {
        bounce(spaceship);
        injure(Spaceship.DAMAGE);
        if (healthPoints <= 0)
            killer = spaceship;
    }
    
    @Override
    public void projectileCollision(Projectile projectile) {
        injure(Projectile.DAMAGE);
        if (healthPoints <= 0)
            killer = projectile;
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
            orientation = orientation.rotate(ANGULAR_VELOCITY);
            //velocity = velocity.rotate(0.1);
        }
        if (keyPresses.isPressed(KeyPresses.LEFT)) {
            orientation = orientation.rotate(-ANGULAR_VELOCITY);
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

    @Override
    public void increaseKills() {
        player.increaseKills();
    }
    
    @Override 
    public void increaseScore(int score) {
        player.increaseScore(score);
    }

    public String getName() {
        return player.getName();
    }
    
    public int getKills() {
        return player.getKills();
    }
    
    public int getDeaths() {
        return player.getDeaths();
    }

    public int getScore() {
        return player.getScore();
    }
}
