/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.awesometeam;

import java.io.BufferedInputStream;
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
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.awesometeam.clientnetworking.ClientSentData;
import org.awesometeam.servernetworking.ClientData;
import org.awesometeam.servernetworking.SharedMemoryPlayerNameMapping;
import org.awesometeam.servernetworking.SharedMemoryServerReceived;
import org.awesometeam.servernetworking.SharedMemoryServerSent;
import org.awesometeam.servernetworking.SynchronizedClientList;

/**
 *
 * @author michal
 */
public class Server extends Thread {

    private final int portNumber = 13100;
    private String hostName;
    private final SynchronizedClientList clientsList;
    private final Set<Integer> IDset;
    
    private final Integer nextID;

    protected ServerSocket serverSocket = null;
    protected DatagramSocket datagramSocket = null;

    public Server() throws IOException {
        clientsList = new SynchronizedClientList();
        serverSocket = new ServerSocket(portNumber);
        datagramSocket = new DatagramSocket(portNumber);
        IDset = Collections.synchronizedSet(new HashSet<Integer>());
        nextID = 0;
    }

    public Server(int pN) throws IOException {
        clientsList = new SynchronizedClientList();
        serverSocket = new ServerSocket(pN);
        datagramSocket = new DatagramSocket(pN);
        IDset = Collections.synchronizedSet(new HashSet<Integer>());
        nextID = 0;
    }

    private class ServerTCPThread extends Thread {

        private final ServerSocket sSocket;
        private final SynchronizedClientList clientList;
    
        private Integer nextID;

        public ServerTCPThread(SynchronizedClientList cl, ServerSocket ss, Integer ni) {
            sSocket = ss;
            clientList = cl;
            nextID = ni;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    Socket socket = sSocket.accept();
                    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                    String input;

                    input = in.readLine();
                    if (input.startsWith("REQUEST")) {
                        String name = input.substring(9);
                        int ID = nextID;
                        nextID += 1;

                        SharedMemoryPlayerNameMapping.getInstance().addName(ID, name);

                //      System.out.println(name);
                //      System.out.println(SharedMemoryPlayerNameMapping.getInstance().getName(0));
                        
                        clientList.add(new ClientData(socket.getInetAddress(),
                                socket.getPort(), ID, name));
                        SharedMemoryServerReceived.getInstance().addInstance(ID);
                        out.println("ACCEPT: " + ID);
                    }
                    if (input.startsWith("DISCONNECT")) {
                        int ID = Integer.parseInt(input.substring(12));
                        for (int i = 0; i < clientList.size(); ++i) {
                            if (clientList.get(i).ID == ID) {
                                clientList.remove(i);
                                IDset.remove(ID);
                                SharedMemoryServerReceived.getInstance().removeInstance(ID);
                                SharedMemoryPlayerNameMapping.getInstance().removeByID(ID);
                                System.out.println(ID);
                                break;
                            }
                        }
                    }

                    socket.close();
                    //TODO check needed commands
                } catch (IOException ex) {
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }
    }

    private class ServerUDPSendThread extends Thread {

        private final DatagramSocket dSocket;
        private final SynchronizedClientList clientList;
        private final Set<Integer> IDset;

        public ServerUDPSendThread(SynchronizedClientList cl, DatagramSocket ds, Set<Integer> is) {
            dSocket = ds;
            clientList = cl;
            IDset = is;
        }

        @Override
        public void run() {
            while (true) {
                for (int i = 0; i < clientList.size(); ++i) {
                    try {
                        InetAddress address = clientList.get(i).address;
                        int port = clientList.get(i).port;

                        ByteArrayOutputStream byteStream = new ByteArrayOutputStream(131072);
                        ObjectOutputStream os = new ObjectOutputStream(byteStream);

                        /*if (!SharedMemoryServerSent.getInstance().getData().spaceships.isEmpty()) {
                            System.out.println(SharedMemoryServerSent.getInstance().getData().spaceships.get(0));
                        }*/
                        os.writeObject(SharedMemoryServerSent.getInstance().getData());

                        byte[] buf = byteStream.toByteArray();
                        DatagramPacket packet = new DatagramPacket(buf, buf.length, address, port);

                        
                        System.out.print("Timer status for: ");
                        System.out.print(i);
                        System.out.print(" :");
                        System.out.println(clientList.increaseTimer(i));
                        
                        dSocket.send(packet);
                        if(IDset.contains(clientList.get(i).ID)) {
                            clientList.resetTimer(i);
                            IDset.remove(clientList.get(i).ID);
                        }
                        else if(clientList.increaseTimer(i) > 90) {
                            IDset.remove(clientList.get(i).ID);
                            SharedMemoryServerReceived.getInstance().removeInstance(clientList.get(i).ID);
                            SharedMemoryPlayerNameMapping.getInstance().removeByID(clientList.get(i).ID);
                            clientList.remove(i);
                            --i;
                        }
                        
                        os.close();
                    } catch (IOException ex) {
                        Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                try {
                    Thread.sleep(33);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }

    private class ServerUDPReceiveThread extends Thread {

        private final DatagramSocket dSocket;
        private final SynchronizedClientList clientList;
        private final Set<Integer> IDset;

        public ServerUDPReceiveThread(SynchronizedClientList cl, DatagramSocket ds, Set<Integer> is) {
            dSocket = ds;
            clientList = cl;
            IDset = is;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    ClientSentData data;
                    byte[] buf = new byte[16384];
                    DatagramPacket packet = new DatagramPacket(buf, buf.length);
                    dSocket.receive(packet);

                    ByteArrayInputStream byteStream = new ByteArrayInputStream(buf);
                    ObjectInputStream is = new ObjectInputStream(new BufferedInputStream(byteStream));
                    data = (ClientSentData) is.readObject();

                    //checks if the received data is from a registered client
                    if(SharedMemoryServerReceived.getInstance().writeData(data.ID, data)) {
                        IDset.add(data.ID);
                        System.out.print("Received DataPacket: ");
                        System.out.println(data.ID);
                    }

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

        tcpThread = new ServerTCPThread(clientsList, serverSocket, nextID);
        udpRThread = new ServerUDPReceiveThread(clientsList, datagramSocket, IDset);
        udpSThread = new ServerUDPSendThread(clientsList, datagramSocket, IDset);

        tcpThread.start();
        udpRThread.start();
        udpSThread.start();
    }
}
