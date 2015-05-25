package org.awesometeam.clientnetworking;


import java.io.Serializable;
import org.awesometeam.gamelogic.KeyPresses;;

public class ClientSentData implements Serializable {
	private KeyPresses keypressesObject;
	
	public ClientSentData(){
		keypressesObject = new KeyPresses();
	}
	
	public ClientSentData(ClientSentData csd){
		keypressesObject = new KeyPresses(csd.keypressesObject);
	}
	
	public ClientSentData(KeyPresses kp){
		keypressesObject = new KeyPresses(kp);
	}
	
	/*public ClientSentData(boolean[] keypressesArray){
		
		this.keypressesObject = new KeyPresses();
		keypressesObject.setKeyPresses(keypressesArray);
	}*/
	
	public KeyPresses getKeyPressesObject(){
		return keypressesObject;
	}
	
	public String toString(){
		return keypressesObject.toString();
	}
	
}
