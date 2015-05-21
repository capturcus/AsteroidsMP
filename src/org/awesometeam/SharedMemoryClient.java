/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.awesometeam;

/**
 *
 * @author michal
 */
public class SharedMemoryClient {
    ClientSentData DATA;
    
    private SharedMemoryClient() {
    }
    
    public static SharedMemoryClient getInstance() {
        return SharedMemoryClientHolder.INSTANCE;
    }
    
    private static class SharedMemoryClientHolder {

        private static final SharedMemoryClient INSTANCE = new SharedMemoryClient();
    }
    
    public synchronized ClientSentData getData() {
        return DATA; //TODO check what exactly game logic needs
    }
    
    public synchronized void writeData(ClientSentData d) {
        DATA = d; //should work, d is not changed by the server and thus I can assign reference
    }
}
