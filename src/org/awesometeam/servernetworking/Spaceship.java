/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.awesometeam.servernetworking;

import java.io.Serializable;
/**
 *
 * @author michal
 */
public class Spaceship extends ThinActor implements Serializable {
    public double angle;
    public String name;
    public int ID;
    public int HP;
    public int kills;
    public int deaths;
    
    public Spaceship() {
        x = 0;
        y = 0;
        angle = 0;
        name = "";
        HP = 0;
        ID = 0;
        kills = 0;
        deaths = 0;
    }
    
    public Spaceship(org.awesometeam.gamelogic.Spaceship sp){
        x = sp.getPosition().getX();
        y = sp.getPosition().getY();
        angle = sp.getAngle();
        HP = sp.getHealthPoints();
        ID = sp.getID();
        name = sp.getName();
        kills = sp.getKills();
        deaths = sp.getDeaths();
    }
    
    public String toString() {
    	return "spaceship: "+x+" "+y+" "+angle+" "+name + " " + ID + " " + kills + " " + deaths;
    }
}
