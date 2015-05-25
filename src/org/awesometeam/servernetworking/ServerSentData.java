/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.awesometeam.servernetworking;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author michal
 */
public class ServerSentData implements Serializable {
    public ArrayList<Spaceship> spaceships;
    public ArrayList<Projectile> projectiles;
    public ArrayList<Asteroid> asteroids;
    
    public ServerSentData() {
        spaceships = new ArrayList<>();
        projectiles = new ArrayList<>();
        asteroids = new ArrayList<>();
    }
    
    public ServerSentData(ServerSentData srd) {
        spaceships = new ArrayList<>(srd.spaceships);
        projectiles = new ArrayList<>(srd.projectiles);
        asteroids = new ArrayList<>(srd.asteroids);
    }
    
    public ServerSentData(ArrayList<org.awesometeam.gamelogic.Spaceship> sp,
            ArrayList<org.awesometeam.gamelogic.Projectile> pr,
            ArrayList<org.awesometeam.gamelogic.Asteroid> as) {
        spaceships = new ArrayList<>();
        projectiles = new ArrayList<>();
        asteroids = new ArrayList<>();
        for (org.awesometeam.gamelogic.Asteroid a : as) {
            asteroids.add(new Asteroid(a));
        }
        for (org.awesometeam.gamelogic.Projectile p : pr) {
            projectiles.add(new Projectile(p));
        }
        for (org.awesometeam.gamelogic.Spaceship s : sp) {
            spaceships.add(new Spaceship(s));
        }
    }
}
