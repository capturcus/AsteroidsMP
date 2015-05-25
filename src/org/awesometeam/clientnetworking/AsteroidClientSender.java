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
import org.awesometeam.SharedMemoryClientSent;
import org.awesometeam.servernetworking.*; 
public class AsteroidClientSender implements Runnable {

    private Thread thread;
    private InetAddress serverIP;
    private int serverPort;
    private final int FPS = 60;
    private final int waitTime = 1000 / FPS;
    private DatagramSocket socket;
    private int id;

    public void interruptThread(){
    	thread.interrupt();
    }
    
    public AsteroidClientSender(String serverIP, int serverPort, DatagramSocket socket, int id) throws UnknownHostException {
        this.serverIP = InetAddress.getByName(serverIP);
        this.serverPort = serverPort;
        this.socket = socket;
        this.id = id;
    }
    
    public void run() {
        try {
            
            int x = 0;
            while (!Thread.currentThread().isInterrupted()) {
                x++;
                ClientSentData keyPressesPacket = SharedMemoryClientSent.getInstance().getData();
                keyPressesPacket.setID(id);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(baos);
                oos.writeObject(keyPressesPacket);
                //oos.flush(); //what for?
                byte[] byteKeyPresses = baos.toByteArray();

                DatagramPacket sendKeyPressesPacket = new DatagramPacket(byteKeyPresses, byteKeyPresses.length, serverIP, serverPort);
                socket.send(sendKeyPressesPacket);
                //System.out.println("Object nr " + x + " sent from the client");
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
