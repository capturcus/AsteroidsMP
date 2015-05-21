package org.awesometeam;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.ArrayList;


public class AsteroidServerSender implements Runnable {
	private int port;
	
	public AsteroidServerSender(int port){
		this.port = port;
	}
	
	@Override
	public void run() {
		PacketKeyPresses[] packets = new PacketKeyPresses[10];
		
		boolean[] test = new boolean[5];
		
		for(int i = 0 ; i < 10; i++){
			packets[i] = new PacketKeyPresses(test);
		}
		
		//PacketType2 testPacket = new PacketType2(packets);
		byte[] buffer = new byte[1024];
		
		try {
			DatagramSocket socket = new DatagramSocket(port);
			
			while(true){
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				ObjectOutputStream oos = new ObjectOutputStream(baos);
				
				DatagramPacket sendPacket = new DatagramPacket(buffer,buffer.length);
				
			}
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e){
			e.printStackTrace();
		}
	}

}
