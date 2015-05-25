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
    public synchronized void writeData(int ID, ClientSentData d) {
        DATA.map.put(ID, new KeyPresses(d.getKeyPressesObject()));
    }
}
