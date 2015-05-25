package org.awesometeam.clientnetworking;


import java.io.Serializable;
import org.awesometeam.gamelogic.KeyPresses;

public class ClientSentData implements Serializable {
    public KeyPresses keypressesObject;
    public int ID;

    public ClientSentData(){
            keypressesObject = new KeyPresses();
            ID = -1;
    }

    public ClientSentData(ClientSentData csd){
            keypressesObject = new KeyPresses(csd.keypressesObject);
            ID = -1;
    }

    public ClientSentData(KeyPresses kp){
            keypressesObject = new KeyPresses(kp);
            ID = -1;
    }

    public KeyPresses getKeyPressesObject(){
            return keypressesObject;
    }

    public String toString(){
            return keypressesObject.toString();
    }

    public void setID(int id){
            this.ID = id;
}
}
