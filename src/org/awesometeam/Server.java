/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.awesometeam;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.awesometeam.clientnetworking.ClientSentData;

/**
 *
 * @author michal
 */
public class Server extends Thread {
    private final int portNumber = 13100;
    private String hostName;
    private ArrayList<ClientData> clientsList;
    
    protected ServerSocket serverSocket = null;
    protected DatagramSocket datagramSocket = null;
    
    public Server() throws IOException {
        serverSocket = new ServerSocket(portNumber);
        datagramSocket = new DatagramSocket(portNumber);
    }
    
    private static class ServerTCPThread extends Thread {
        private final ServerSocket sSocket;
        private final ArrayList<ClientData> clientList;
        
        public ServerTCPThread(ArrayList<ClientData> cl, ServerSocket ss) {
            sSocket = ss;
            clientList = cl;
        }
        
        @Override
        public void run() {
            while(true)
            {
                try {
                    Socket socket = sSocket.accept();
                    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    
                    String input;
                    
                    input = in.readLine();
                    
                    //if(input.)
                    
                    //TODO check needed commands
                    
                } catch (IOException ex) {
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        }
    }
    
    private static class ServerUDPSendThread extends Thread {
        private final DatagramSocket dSocket;
        private final ArrayList<ClientData> clientList;
        
        public ServerUDPSendThread(ArrayList<ClientData> cl, DatagramSocket ds)
        {
            dSocket = ds;
            clientList = cl;
        }
        
        @Override
        public void run() {
            while(true)
            {
                for(int i = 0; i < clientList.size(); ++i)
                {
                    try {
                        InetAddress address = clientList.get(i).address;
                        int port = clientList.get(i).port;
                        
                        ByteArrayOutputStream byteStream = new ByteArrayOutputStream(4096);
                        ObjectOutputStream os = new ObjectOutputStream(new BufferedOutputStream(byteStream));
                        
                        os.flush();
                        os.writeObject(SharedMemoryServerSent.getInstance().getData());
                        os.flush();
                        
                        byte[] buf = byteStream.toByteArray();
                        DatagramPacket packet = new DatagramPacket(buf, buf.length, address, port);
                        
                        dSocket.send(packet);
                        os.close();
                    } catch (IOException ex) {
                        Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
    }
    
    private class ServerUDPReceiveThread extends Thread {
        private final DatagramSocket dSocket;
        private final ArrayList<ClientData> clientList;

        public ServerUDPReceiveThread(ArrayList<ClientData> cl, DatagramSocket ds) {
            dSocket = ds;
            clientList = cl;
        }
        
        @Override
        public void run() {
            while(true)
            {
                try {
                    ClientSentData data;
                    byte[] buf = new byte[256];
                    DatagramPacket packet = new DatagramPacket(buf, buf.length);
                    dSocket.receive(packet);
                    
                    ByteArrayInputStream byteStream = new ByteArrayInputStream(buf);
                    ObjectInputStream is = new ObjectInputStream(new BufferedInputStream(byteStream));
                    data = (ClientSentData) is.readObject();
                    
                    //TODO change writeData to accomodate many clients
                    SharedMemoryServerReceived.getInstance().writeData(0, data);
                    
                    is.close();
                } catch (IOException | ClassNotFoundException ex) {
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    @Override
    public void run() {
        ServerTCPThread tcpThread;
        ServerUDPReceiveThread udpRThread;
        ServerUDPSendThread udpSThread;
        
        tcpThread = new ServerTCPThread(clientsList, serverSocket);
        udpRThread = new ServerUDPReceiveThread(clientsList, datagramSocket);
        udpSThread = new ServerUDPSendThread(clientsList, datagramSocket);
        
        tcpThread.start();
        udpRThread.start();
        udpSThread.start();
    }
}
