package org.awesometeam.gamelogic;

import java.util.ArrayList;
import java.util.TimerTask;
import java.util.TreeMap;
import org.awesometeam.GameState;
import org.awesometeam.servernetworking.SharedMemoryServerReceived;
import org.awesometeam.servernetworking.SharedMemoryServerSent;
//import org.awesometeam.SharedMemoryServerReceived;

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
    //    KeyPresses keyPresses = new KeyPresses();
    //    boolean[] Presses = new boolean[2];//GameState.getKeyPresses();
    //    keyPresses.setKeyPresses(Presses);
        
        //ArrayList<KeyPresses> playersKeyPresses = new ArrayList<KeyPresses>();
        TreeMap<Integer, KeyPresses> playersKeyPresses = SharedMemoryServerReceived.getInstance().getData().map;
        //System.out.println("GameLogic                  Debug" + playersKeyPresses);
        //playersKeyPresses.put(0, keyPresses);
        
        //playersKeyPresses.add(keyPresses);
        try {
            double time = timeInterval;
            time /= 1000;
            actorManager.update(playersKeyPresses, time);
            SharedMemoryServerSent.getInstance().writeData(
                    actorManager.getActorLists().getSpaceshipList(),
                    actorManager.getActorLists().getProjectileList(),
                    actorManager.getActorLists().getAsteroidList());
            //System.out.println("GameLogic                  Debug");
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
