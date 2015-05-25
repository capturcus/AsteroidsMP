/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.awesometeam.servernetworking;

import java.util.ArrayList;

/**
 *
 * @author michal
 */
public class SharedMemoryServerSent {
    
    private ServerSentData DATA;
    
    private SharedMemoryServerSent() {
    }
    
    public static SharedMemoryServerSent getInstance() {
        return SharedMemoryServerHolder.INSTANCE;
    }
    
    private static class SharedMemoryServerHolder {

        private static final SharedMemoryServerSent INSTANCE = new SharedMemoryServerSent();
    }
    
    public synchronized ServerSentData getData() {
        return new ServerSentData(DATA);
    }
    
    public synchronized void writeData(ArrayList<org.awesometeam.gamelogic.Spaceship> sp,
            ArrayList<org.awesometeam.gamelogic.Projectile> pr,
            ArrayList<org.awesometeam.gamelogic.Asteroid> as) {
        DATA = new ServerSentData(sp, pr, as);
    }
}
