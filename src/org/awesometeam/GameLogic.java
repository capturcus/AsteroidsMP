package org.awesometeam;

import java.util.ArrayList;

import org.awesometeam.gamelogic.Spaceship;
import org.awesometeam.gamelogic.Asteroid;
import org.awesometeam.gamelogic.Projectile;

/**
 *
 * @author NoSpacesName
 */
public class GameLogic extends Thread {

    @Override
    public void run() {
        ServerSideNetworking net = new ServerSideNetworking();
    }

    public static ArrayList<Spaceship> getSpaceships() {
        return new ArrayList<>();
    }

    public static ArrayList<Asteroid> getAsteroids() {
        return new ArrayList<>();
    }

    public static ArrayList<Projectile> getProjectile() {
        return new ArrayList<>();
    }
}
