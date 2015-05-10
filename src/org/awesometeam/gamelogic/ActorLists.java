package org.awesometeam.gamelogic;

import java.util.ArrayList;

public class ActorLists {
    
    private ArrayList<BoardActor> actorList;
    private ArrayList<Asteroid> asteroidList;
    private ArrayList<Spaceship> spaceshipList;
    private ArrayList<Projectile> projectileList;
    
    public ActorLists() {
        actorList = new ArrayList<BoardActor>();
        asteroidList = new ArrayList<Asteroid>();
        spaceshipList = new ArrayList<Spaceship>();
        projectileList = new ArrayList<Projectile>();
    }

    public ArrayList<BoardActor> getActorList() {
        return actorList;
    }

    public ArrayList<Asteroid> getAsteroidList() {
        return asteroidList;
    }

    public ArrayList<Spaceship> getSpaceshipList() {
        return spaceshipList;
    }

    public ArrayList<Projectile> getProjectileList() {
        return projectileList;
    }
    
    public void addAsteroid(Asteroid asteroid) {
        actorList.add(asteroid);
        asteroidList.add(asteroid);
        asteroid.setActorList(this);
    }
    
    public void addSpaceship(Spaceship spaceship) {
        actorList.add(spaceship);
        spaceshipList.add(spaceship);
        spaceship.setActorList(this);
    }
    
    public void addProjectile(Projectile projectile) {
        actorList.add(projectile);
        projectileList.add(projectile);
        projectile.setActorList(this);
    }

    public void removeAsteroid(Asteroid asteroid) {
        actorList.remove(asteroid);
        asteroidList.remove(asteroid);
    }

    public void removeSpaceship(Spaceship spaceship) {
        actorList.remove(spaceship);
        spaceshipList.remove(spaceship);
    }
    
    public void removeProjectile(Projectile projectile) {
        actorList.remove(projectile);
        projectileList.remove(projectile);
    }
}
