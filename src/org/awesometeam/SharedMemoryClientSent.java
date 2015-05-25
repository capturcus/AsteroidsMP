/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.awesometeam;

import org.awesometeam.clientnetworking.ClientSentData;
import org.awesometeam.gamelogic.KeyPresses;


/**
 *
 * @author michal
 */
public class SharedMemoryClientSent {
    private ClientSentData DATA;
    
    private SharedMemoryClientSent() {
        DATA = new ClientSentData();
    }
    
    public static SharedMemoryClientSent getInstance() {
        return SharedMemoryClientSentHolder.INSTANCE;
    }
    
    private static class SharedMemoryClientSentHolder {

        private static final SharedMemoryClientSent INSTANCE = new SharedMemoryClientSent();
    }
    
    public synchronized ClientSentData getData() {
        return new ClientSentData(DATA);
    }
    
    public synchronized void writeData(KeyPresses d) {
        DATA = new ClientSentData(d);
    }
}
