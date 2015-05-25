package org.awesometeam.clientnetworking;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.DatagramSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;

import org.awesometeam.LoadingState;
import org.awesometeam.gamelogic.Asteroid;
import org.awesometeam.gamelogic.Projectile;
import org.awesometeam.gamelogic.Spaceship;

public class AsteroidClientMain {

    private static AsteroidClientMain instance;

    private AsteroidClientSender acs;
    private AsteroidClientReceiver acr;
    private String serverIP;
    private final int serverPort;
    private DatagramSocket socket;
    private Socket socketTCP;
    private String nickname;

    public AsteroidClientMain() {
        acs = null;
        serverIP = "";
        serverPort = 13100;
        try {
			socket = new DatagramSocket();
		} catch (SocketException e) {
			System.out.println("Socket creation error!");
			e.printStackTrace();
		}
    }

    public static AsteroidClientMain getInstance() {
        if (instance == null) {
            instance = new AsteroidClientMain();
        }
        return instance;
    }

    public void startSending(String nickname) {
    	this.nickname = nickname;
        try {
        	socketTCP = new Socket(serverIP,serverPort);
        	OutputStream os = socketTCP.getOutputStream();
        	ObjectOutputStream oos = new ObjectOutputStream(os);
        	oos.writeObject(nickname);
        	
        	InputStream is = socketTCP.getInputStream();
        	ObjectInputStream ois = new ObjectInputStream(is);
        	while(true){
        		try {
					String s = (String)ois.readObject();
					if(s == "Accept"){
						System.out.println("Server accepted our request");
						break;
					}
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
        	}
            
            acs = new AsteroidClientSender(serverIP, serverPort, socket);
            acr = new AsteroidClientReceiver(socket);
            acs.startThread();
            acr.startThread();
            
        } catch (UnknownHostException e) {
            LoadingState.message("Wrong server address");
        } catch (IOException e){
        	LoadingState.message("Cannot instantiate socket object. Please make sure you entered ip correctly");
        }
    }

    public void stopSending() {
    	try{
			OutputStream os = socketTCP.getOutputStream();
	    	ObjectOutputStream oos = new ObjectOutputStream(os);
	    	oos.writeObject("Disconnect");
    	} catch(IOException e){
	    	System.out.println("IOException. Cannot instantiate outputsream for the socket");
	    }

    }

    //choose IP server to which you want to connect
    public void setServerIPToConnect(String serverIP) {
        this.serverIP = serverIP;
    }

    public ArrayList<Spaceship> getSpaceships() {
        return new ArrayList<>();
    }

    public ArrayList<Asteroid> getAsteroids() {
        return new ArrayList<>();
    }

    public ArrayList<Projectile> getProjectiles() {
        return new ArrayList<>();
    }

}
