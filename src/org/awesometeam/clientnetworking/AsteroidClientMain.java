package org.awesometeam.clientnetworking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
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
    private int id;
    private int clientPort;

    public AsteroidClientMain() {
        acs = null;
        serverIP = "";
        serverPort = 13100;
    }

    public static AsteroidClientMain getInstance() {
        if (instance == null) {
            instance = new AsteroidClientMain();
        }
        return instance;
    }

    public void startSending(String nickname) throws UnknownHostException, IOException {
        this.nickname = nickname;

        System.out.println("Server ip: " + serverIP + " , serverPort: " + serverPort);
        socketTCP = new Socket(serverIP, serverPort);
        try {

            clientPort = socketTCP.getLocalPort();
            System.out.println("clientPort: " + socketTCP.getLocalPort());
            PrintWriter out = new PrintWriter(socketTCP.getOutputStream(), true);
            out.println("REQUEST: " + nickname);
            LoadingState.message("Request sent");

            /*
             OutputStream os = socketTCP.getOutputStream();
             ObjectOutputStream oos = new ObjectOutputStream(os);
             oos.writeObject("REQUEST: " + nickname);
             oos.close();
             os.close();
             */
            /*
             InputStream is = socketTCP.getInputStream();
             ObjectInputStream ois = new ObjectInputStream(is);
             */
            BufferedReader in = new BufferedReader(new InputStreamReader(socketTCP.getInputStream()));
            while (true) {
                String s = in.readLine();
                if (s.startsWith("ACCEPT")) {
                    String[] subs = s.split(" ");
                    id = Integer.parseInt(subs[1]);
                    System.out.println("Server accepted our request, our id: " + id);
                    break;
                } else {
                    System.out.println("Server has not accepted request or timed out");
                    break;
                }
            }

            socket = new DatagramSocket(clientPort);

            acs = new AsteroidClientSender(serverIP, serverPort, socket, id);
            acr = new AsteroidClientReceiver(socket);
            acs.startThread();
            acr.startThread();

        } catch (UnknownHostException e) {
            LoadingState.message("Wrong server address");
        } catch (SocketException e) {
            System.out.println("Socket creation error!");
            e.printStackTrace();
        } catch (IOException e) {
            LoadingState.message("IO EXCEPTION");
        }
    }

    public void stopSending() {
        try {
            OutputStream os = socketTCP.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(os);
            oos.writeObject("DISCONNECT: " + id);
            acs.interruptThread();
            acr.interruptThread();
        } catch (IOException e) {
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
