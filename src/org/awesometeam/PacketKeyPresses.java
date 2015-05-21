package org.awesometeam;

import java.io.Serializable;

public class PacketKeyPresses implements Serializable {
	private boolean[] keypresses;
	
	public PacketKeyPresses(boolean[] keypresses){
		this.keypresses = keypresses;
	}
	
	public boolean[] getKeyPresses(){
		return keypresses;
	}
	
	public String toString(){
		String txt = "KEY-PRESSES: ";
		for(int i = 0; i < keypresses.length; i++){
			txt += keypresses[i] + " ";
		}
		txt += "\n";
		return txt;
	}
}
