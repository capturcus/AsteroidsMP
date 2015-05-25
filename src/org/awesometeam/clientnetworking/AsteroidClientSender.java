package org.awesometeam.clientnetworking;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

import org.awesometeam.GameState;

public class AsteroidClientSender implements Runnable {

    private Thread thread;
    private InetAddress serverIP;
    private int serverPort;
    private final int FPS = 60;
    private final int waitTime = 1000 / FPS;
    private DatagramSocket socket;

    public AsteroidClientSender(String serverIP, int serverPort, DatagramSocket socket) throws UnknownHostException {
        this.serverIP = InetAddress.getByName(serverIP);
        this.serverPort = serverPort;
        this.socket = socket;
    }
    
    public void run() {
        try {
            boolean[] keyPresses = GameState.getKeyPresses();
            int x = 0;
            while (true) {
                x++;
                ClientSentData packet = new ClientSentData(keyPresses);

                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(baos);
                oos.writeObject(packet);
                //oos.flush(); //what for?
                byte[] byteKeyPresses = baos.toByteArray();

                DatagramPacket sendKeyPressesPacket = new DatagramPacket(byteKeyPresses, byteKeyPresses.length, serverIP, serverPort);
                socket.send(sendKeyPressesPacket);
                System.out.println("Object nr " + x + " sent from the client");
                oos.close();
                baos.close();
                Thread.sleep(waitTime);
            }
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {	//Thread.sleep
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void startThread() {
        if (thread == null) {
            System.out.println("Creating new thread: 'AsteroidClientSenderThread'");
            thread = new Thread(this, "AsteroidClientSenderThread");
            System.out.println("New thread: 'AsteroidClientSenderThread' created");
            System.out.println("Starting the 'AsteroidClientSenderThread' thread");
            System.out.println("-----------------------------------------------------------------------------------------------------------");
            thread.start();
        }
    }
}