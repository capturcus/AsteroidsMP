/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.awesometeam.servernetworking;

import org.awesometeam.clientnetworking.ClientSentData;
import org.awesometeam.gamelogic.KeyPresses;

/**
 *
 * @author michal
 */
public class SharedMemoryServerReceived {
    private ServerReceivedData DATA;
    
    private SharedMemoryServerReceived() {
        DATA = new ServerReceivedData();
    }
    
    public static SharedMemoryServerReceived getInstance() {
        return SharedMemoryClientHolder.INSTANCE;
    }
    
    private static class SharedMemoryClientHolder {

        private static final SharedMemoryServerReceived INSTANCE = new SharedMemoryServerReceived();
    }
    
    //returns a copy of data
    public synchronized ServerReceivedData getData() {
        return new ServerReceivedData(DATA);
    }
    
    //writes data valid for a SINGLE CLIENT
    public synchronized boolean writeData(int ID, ClientSentData d) {
        if(DATA.map.containsKey(ID)) {
            DATA.map.put(ID, new KeyPresses(d.getKeyPressesObject()));
            return true;
        }
        return false;
    }
    
    public synchronized void addInstance(int ID) {
        DATA.map.put(ID, new KeyPresses());
    }

    public synchronized void removeInstance(int ID) {
        DATA.map.remove(ID);
    }
}
