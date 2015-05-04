package org.awesometeam.gamelogic;

import java.util.ArrayList;
import java.util.TimerTask;
import org.awesometeam.GameState;

public class GameUpdater extends TimerTask {

    private int timeInterval;
    private ActorManager actorManager;

    public GameUpdater(ActorManager manager, int interval) {
        actorManager = manager;
        timeInterval = interval;
        // TODO Auto-generated constructor stub
    }

    @Override
    public void run() {
		//System.out.println("update");
        // TODO Auto-generated method stub
        KeyPresses keyPresses = new KeyPresses();
        keyPresses.setKeyPresses(GameState.getKeyPresses());
        ArrayList<KeyPresses> playersKeyPresses = new ArrayList<KeyPresses>();
        playersKeyPresses.add(keyPresses);
        try {
            actorManager.update(playersKeyPresses, timeInterval);
        } catch (IncorrectListLengthAsteroidsMPGLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
//		System.out.println(actorManager.getActorList().toString());
//		System.out.println(actorManager.getAsteroidList().toString());
//		System.out.println(actorManager.getSpaceshipList().toString());
//		System.out.println(actorManager.getProjectileList().toString());
    }

    public void setTimeInterval(int interval) {
        timeInterval = interval;
    }

}
