package org.awesometeam.clientnetworking;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

import org.awesometeam.SharedMemoryClientReceived;
import org.awesometeam.servernetworking.*;

public class AsteroidClientReceiver implements Runnable {

    private Thread thread;
    private DatagramSocket socket;

    public void interruptThread(){
    	thread.interrupt();
    }
    
    public AsteroidClientReceiver(DatagramSocket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        byte[] dataBuffer = new byte[1024];
        while (!Thread.currentThread().isInterrupted()) {
            try {
                DatagramPacket incomingPacket = new DatagramPacket(dataBuffer, dataBuffer.length);
                socket.receive(incomingPacket);
                dataBuffer = incomingPacket.getData();
                ByteArrayInputStream bais = new ByteArrayInputStream(dataBuffer);
                ObjectInputStream ois = new ObjectInputStream(bais);
                ServerSentData serverPacket = (ServerSentData) ois.readObject();
                System.out.println("Client received ServerSentData object: \n" + serverPacket);
                SharedMemoryClientReceived.getInstance().writeData(serverPacket);

                //zapisywanie otrzymanego obiektu do shared memory
            } catch (ClassNotFoundException e) {
                System.out.println("Wrong class name of sent object");
            } catch (IOException e) {
                System.out.println("IO Exception");
                e.printStackTrace();
            }

        }
    }

    public void startThread() {
        System.out.println("Starting new Thread: Asteroid Client Receiver");
        if (thread == null) {
            thread = new Thread(this, "AsteroidClientReceiverThread");
            System.out.println("New thread 'AsteroidClientReceiverThread' started");
            System.out.println("Calling the run method...");
            System.out.println("-----------------------------------------------------------------------------------------------------------");
            thread.start();
        }
    }
}
