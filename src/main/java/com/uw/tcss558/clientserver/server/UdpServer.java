package com.uw.tcss558.clientserver.server;

import com.uw.tcss558.clientserver.datastore.DataStore;
import com.uw.tcss558.clientserver.datastore.InMemoryDataStore;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UdpServer implements  Server {

    private String portNumber;
    private DataStore dataStore = InMemoryDataStore.getInstance();

    public UdpServer() {
        
    }
    
    public UdpServer(String portNumber) {
        this.portNumber = portNumber;
    }
    
    public void setPortNumber(String portNumber) {
        this.portNumber = portNumber;
    }

    public void execute() throws IOException {

        DatagramSocket serverSocket = new DatagramSocket(Integer.parseInt(portNumber));
        byte[] receiveData = new byte[2056];

        while (true) {
            try {

                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                serverSocket.receive(receivePacket);
                UdpClientHandler handler = new UdpClientHandler(serverSocket, receivePacket, dataStore);
                Thread t = new Thread(handler);
                t.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
