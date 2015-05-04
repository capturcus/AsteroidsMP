package org.awesometeam;

import java.util.ArrayList;
import math.geom2d.Point2D;
import org.awesometeam.ui.Asteroid;
import org.awesometeam.ui.Projectile;
import org.awesometeam.ui.Spaceship;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 *
 * @author NoSpacesName
 */
public class ClientSideNetworking {

    private static ArrayList<Spaceship> spaceships = new ArrayList<>();
    private static ArrayList<Projectile> projectiles = new ArrayList<>();
    private static ArrayList<Asteroid> asteroids = new ArrayList<>();

    public ClientSideNetworking() throws SlickException {
        spaceships.add(new Spaceship(0, 100, true, new Point2D(0, 0), 0));
        spaceships.add(new Spaceship(1, 25, false, new Point2D(300, 300), 1));
        spaceships.add(new Spaceship(2, 75, false, new Point2D(100, 200), 2));
        
        asteroids.add(new Asteroid(3, new Point2D(0, 0), 0));
        asteroids.add(new Asteroid(2, new Point2D(300, 300), 1));
        asteroids.add(new Asteroid(1, new Point2D(400, 400), 2));
        asteroids.add(new Asteroid(0, new Point2D(500, 500), 3));
    }

    public static ArrayList<Spaceship> getSpaceships() {
        return spaceships;
    }

    public static ArrayList<Projectile> getProjectiles() {
        return projectiles;
    }

    public static ArrayList<Asteroid> getAsteroids() {
        return asteroids;
    }

    public void startSending() {

    }

    public void stopSending() {

    }
}
