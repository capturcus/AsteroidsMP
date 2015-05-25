/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.awesometeam.servernetworking;

import java.net.InetAddress;

/**
 *
 * @author michal
 */
public class ClientData {
    public InetAddress address;
    public int port;
    public int ID;
    
    String nickName;

    
    public ClientData() {
        address = null;
        port = 0;
        ID = 0;
        nickName = "lol";
    }
    
    public ClientData(InetAddress a, int p, int i, String n) {
        address = a;
        port = p;
        ID = i;
        nickName = n;
    }
}
