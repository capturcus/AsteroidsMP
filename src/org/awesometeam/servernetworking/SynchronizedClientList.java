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
public class SynchronizedClientList {
    private final ArrayList<ClientData> list;
    
    public SynchronizedClientList() {
        list = new ArrayList<>();
    }

    public synchronized int size() {
        return list.size();
    }

    public synchronized ClientData get(int i) {
        return new ClientData(list.get(i));
    }

    public synchronized void add(ClientData clientData) {
        list.add(clientData);
    }

    public synchronized void remove(int i) {
        list.remove(i);
    }
}
