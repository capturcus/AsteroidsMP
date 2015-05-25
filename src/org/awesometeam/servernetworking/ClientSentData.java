/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.awesometeam.servernetworking;

import java.io.Serializable;
import org.awesometeam.gamelogic.KeyPresses;

/**
 *
 * @author michal
 */
public class ClientSentData implements Serializable {
    public KeyPresses keyPresses;
    
    public ClientSentData() {
        keyPresses = new KeyPresses();
    }
    
    public ClientSentData(ClientSentData csd) {
        keyPresses = new KeyPresses(csd.keyPresses);
    }
    
    public ClientSentData(KeyPresses kp) {
        keyPresses = new KeyPresses(kp);
    }
}
