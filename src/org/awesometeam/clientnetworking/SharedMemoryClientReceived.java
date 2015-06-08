/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.awesometeam.clientnetworking;

import org.awesometeam.servernetworking.ServerSentData;

/**
 *
 * @author michal
 */
public class SharedMemoryClientReceived {
    private ServerSentData DATA;
    
    private SharedMemoryClientReceived() {
        DATA = new ServerSentData();
    }
    
    public static SharedMemoryClientReceived getInstance() {
        return SharedMemoryClientReceivedHolder.INSTANCE;
    }
    
    private static class SharedMemoryClientReceivedHolder {

        private static final SharedMemoryClientReceived INSTANCE = new SharedMemoryClientReceived();
    }
    
    public synchronized ServerSentData getData() {
        return new ServerSentData(DATA);
    }
    
    public synchronized void writeData(ServerSentData d) {
        DATA = new ServerSentData(d);
    }
}
