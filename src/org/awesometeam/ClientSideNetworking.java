package org.awesometeam;

import java.util.ArrayList;
import org.awesometeam.ui.Asteroid;
import org.awesometeam.ui.Projectile;
import org.awesometeam.ui.Spaceship;

/**
 *
 * @author NoSpacesName
 */
public class ClientSideNetworking {

    private static ArrayList<Spaceship> spaceships = new ArrayList<>();
    private static ArrayList<Projectile> projectiles = new ArrayList<>();
    private static ArrayList<Asteroid> asteroids = new ArrayList<>();

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
