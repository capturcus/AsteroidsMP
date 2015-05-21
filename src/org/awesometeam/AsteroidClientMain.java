package org.awesometeam;

import java.net.UnknownHostException;

public class AsteroidClientMain {
	private AsteroidClientSender acs;
	private String serverIP;
	private final int serverPort;
	
	//private AsteroidClientReceiver acr;
	
	public AsteroidClientMain() {
		acs = null;
		serverIP = "";
		serverPort = 9876;
	}
	
	public void startSending(){
		try{
			acs = new AsteroidClientSender(serverIP, serverPort);
			acs.startThread();
		}catch(UnknownHostException e){
			LoadingState.message("Wrong server address");
		}
		//acr.startThread();
	}
	
	public void stopSending(){
		
	}
	
	//choose IP server to which you want to connect
	public void setServerIPToConnect(String serverIP){
		this.serverIP = serverIP;
	}

}
