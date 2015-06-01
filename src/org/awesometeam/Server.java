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
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.awesometeam.clientnetworking.ClientSentData;
import org.awesometeam.gamelogic.Spaceship;
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

    private final Integer nextID;

    protected ServerSocket serverSocket = null;
    protected DatagramSocket datagramSocket = null;

    public Server() throws IOException {
        clientsList = new SynchronizedClientList();
        serverSocket = new ServerSocket(portNumber);
        datagramSocket = new DatagramSocket(portNumber);
        nextID = 0;
    }

    public Server(int pN) throws IOException {
        clientsList = new SynchronizedClientList();
        serverSocket = new ServerSocket(pN);
        datagramSocket = new DatagramSocket(pN);
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

                        System.out.println(name);

                        clientList.add(new ClientData(socket.getInetAddress(),
                                socket.getPort(), ID, name));
                        out.println("ACCEPT: " + ID);
                    }
                    if (input.startsWith("DISCONNECT")) {
                        int ID = Integer.parseInt(input.substring(12));
                        for (int i = 0; i < clientList.size(); ++i) {
                            if (clientList.get(i).ID == ID) {
                                clientList.remove(i);
                                SharedMemoryPlayerNameMapping.getInstance().removeByID(i);
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

        public ServerUDPSendThread(SynchronizedClientList cl, DatagramSocket ds) {
            dSocket = ds;
            clientList = cl;
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

                        if (!SharedMemoryServerSent.getInstance().getData().spaceships.isEmpty()) {
                            System.out.println(SharedMemoryServerSent.getInstance().getData().spaceships.get(0));
                        }
                        os.writeObject(SharedMemoryServerSent.getInstance().getData());

                        byte[] buf = byteStream.toByteArray();
                        DatagramPacket packet = new DatagramPacket(buf, buf.length, address, port);

                        dSocket.send(packet);

                        os.close();
                    } catch (IOException ex) {
                        Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                /*dodane dla celow testowych */
                try {
                    Thread.sleep(33);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                /*dodane dla celow testowych */
            }
        }
    }

    private class ServerUDPReceiveThread extends Thread {

        private final DatagramSocket dSocket;
        private final SynchronizedClientList clientList;

        public ServerUDPReceiveThread(SynchronizedClientList cl, DatagramSocket ds) {
            dSocket = ds;
            clientList = cl;
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

                    //System.out.println("Object received by the server: " + data);
                    SharedMemoryServerReceived.getInstance().writeData(data.ID, data);

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

        ArrayList<org.awesometeam.gamelogic.Spaceship> sp;
        ArrayList<org.awesometeam.gamelogic.Asteroid> as;
        ArrayList<org.awesometeam.gamelogic.Projectile> pr;
        sp = new ArrayList<>();
        as = new ArrayList<>();
        pr = new ArrayList<>();

//        org.awesometeam.gamelogic.Spaceship ship = new Spaceship();
        //      sp.add(ship);
        //    SharedMemoryServerSent.getInstance().writeData(sp, pr, as);
        tcpThread = new ServerTCPThread(clientsList, serverSocket, nextID);
        udpRThread = new ServerUDPReceiveThread(clientsList, datagramSocket);
        udpSThread = new ServerUDPSendThread(clientsList, datagramSocket);

        tcpThread.start();
        udpRThread.start();
        udpSThread.start();
    }
}
