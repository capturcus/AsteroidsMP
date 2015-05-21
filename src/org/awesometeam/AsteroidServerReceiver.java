package org.awesometeam;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;


public class AsteroidServerReceiver implements Runnable {
	private Thread thread;
	
	public void run(){
		DatagramSocket socket = null;
		int port = 9876;
		try {
			socket = new DatagramSocket(port);
			byte[] dataBuffer = new byte[1024];
			while(true){
				DatagramPacket incomingPacket = new DatagramPacket(dataBuffer, dataBuffer.length);
				socket.receive(incomingPacket);
				dataBuffer = incomingPacket.getData();
				ByteArrayInputStream bais = new ByteArrayInputStream(dataBuffer);
				ObjectInputStream ois = new ObjectInputStream(bais);
				
				try {
					PacketKeyPresses packetTest = (PacketKeyPresses) ois.readObject();
					System.out.println("Server received PacketKeyPresses object: \n" + packetTest);
					System.out.println();
				} catch (ClassNotFoundException e) {
					System.out.println("ClassNotFound Exception, the sent packet could not have been cast to PacketNet2 class.");
				}

			}
			

		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e){
			e.printStackTrace();
		}
		

	}
	
	public void startThread(){
		System.out.println("Starting new Thread: Asteroid Server Receiver");
		if(thread == null){
			thread = new Thread(this, "AsteroidServerReceiverThread");
			System.out.println("New thread 'AsteroidServerReceiverThread' started");
			System.out.println("Calling the run method...");
			System.out.println("-----------------------------------------------------------------------------------------------------------");
			thread.start();
		}
	}
}
