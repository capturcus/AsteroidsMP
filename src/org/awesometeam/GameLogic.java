package org.awesometeam;

import java.util.ArrayList;
import java.util.Timer;

import org.awesometeam.gamelogic.ActorManager;
import org.awesometeam.gamelogic.GameUpdater;
import org.awesometeam.gamelogic.Spaceship;
import org.awesometeam.gamelogic.Asteroid;
import org.awesometeam.gamelogic.Projectile;

/**
 *
 * @author NoSpacesName
 */
public class GameLogic extends Thread {

    final static int PLAYER_COUNT = 1;
    private static ActorManager game;

    @Override
    public void run() {

        //System.out.println("test");
        game = new ActorManager(PLAYER_COUNT);
        //System.out.println(game.getActorList().toString());

        Timer time = new Timer(); // Instantiate Timer Object
        GameUpdater gu = new GameUpdater(game, 50); // Instantiate
        // SheduledTask class
        time.schedule(gu, 0, 50); // Create Repetitively task for every 0.5 secs

    }
}
