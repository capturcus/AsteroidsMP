package org.awesometeam.clientnetworking;

import java.io.Serializable;
import org.awesometeam.gamelogic.KeyPresses;;

public class ClientSentData implements Serializable {
	private KeyPresses keypressesObject;
	
	public ClientSentData(boolean[] keypressesArray){
		this.keypressesObject = new KeyPresses();
		keypressesObject.setKeyPresses(keypressesArray);
	}
	
	public KeyPresses getKeyPressesObject(){
		return keypressesObject;
	}
	
	public String toString(){
		return keypressesObject.toString();
	}
	
}
