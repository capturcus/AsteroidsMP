package org.awesometeam.clientnetworking;

import java.net.DatagramSocket;
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

    public AsteroidClientMain() {
        acs = null;
        serverIP = "";
        serverPort = 9876;
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

    public void startSending() {
        try {
            acs = new AsteroidClientSender(serverIP, serverPort, socket);
            acr = new AsteroidClientReceiver(socket);
            acs.startThread();
            acr.startThread();
        } catch (UnknownHostException e) {
            LoadingState.message("Wrong server address");
        }
        //acr.startThread();
    }

    public void stopSending() {

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
