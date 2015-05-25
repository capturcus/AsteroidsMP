/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.awesometeam.servernetworking;

import java.io.Serializable;
import java.util.TreeMap;
import org.awesometeam.gamelogic.KeyPresses;

/**
 *
 * @author michal
 */
public class ServerReceivedData implements Serializable {
    public TreeMap<Integer, KeyPresses> map;
    
    public ServerReceivedData() {
        map = new TreeMap<>();
    }
    
    public ServerReceivedData(ServerReceivedData srd) {
        map = new TreeMap<>(srd.map);
    }
}
