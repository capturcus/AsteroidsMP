/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.awesometeam.servernetworking;

import java.net.Inet4Address;
import java.net.InetAddress;

/**
 *
 * @author michal
 */
public class ClientData {
    public InetAddress address;
    public int port;
    public int ID;
    public int timer;
    
    String nickName;

    
    public ClientData() {
        address = null;
        port = 0;
        ID = 0;
        nickName = "lol";
        timer = 0;
    }
    
    public ClientData(InetAddress a, int p, int i, String n) {
        address = a;
        port = p;
        ID = i;
        nickName = n;
        timer = 0;
    }
    
    public ClientData(ClientData cd) {
        address = cd.address;
        port = cd.port;
        ID = cd.ID;
        nickName = cd.nickName;
        timer = cd.timer;
    }
    
    public int increaseTimer() {
        timer += 1;
        return timer;
    }
    
    public void resetTimer() {
        timer = 0;
    }
}
