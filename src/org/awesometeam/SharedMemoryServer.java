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
public class SharedMemoryServer {
    
    private ServerSentData DATA;
    
    private SharedMemoryServer() {
    }
    
    public static SharedMemoryServer getInstance() {
        return SharedMemoryServerHolder.INSTANCE;
    }
    
    private static class SharedMemoryServerHolder {

        private static final SharedMemoryServer INSTANCE = new SharedMemoryServer();
    }
    
    public synchronized ServerSentData getData() {
        return DATA; //TODO check what exactly game logic needs
    }
    
    public synchronized void writeData(ServerSentData d) {
        DATA = d; //should work, d is not changed by the server and thus I can just assign reference
    }
}
