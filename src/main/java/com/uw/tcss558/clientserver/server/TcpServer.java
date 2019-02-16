package com.uw.tcss558.clientserver.server;

import com.uw.tcss558.clientserver.datastore.DataStore;
import com.uw.tcss558.clientserver.datastore.InMemoryDataStore;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpServer implements  Server{

    private String portNumber;
    private final DataStore dataStore = new InMemoryDataStore();

    public TcpServer(String portNumber) {
        this.portNumber = portNumber;
    }

    public void execute() throws IOException {
        int port = Integer.parseInt(portNumber);

        ServerSocket ss = new ServerSocket(port);

        // running infinite loop for getting
        // client request
        while (true) {
            Socket s = null;
            try {
                // socket object to receive incoming client requests
                s = ss.accept();

                System.out.println("A new client is connected : " + s);

                // obtaining input and out streams
                DataInputStream dis = new DataInputStream(s.getInputStream());
                DataOutputStream dos = new DataOutputStream(s.getOutputStream());

                System.out.println("Assigning new thread for this client");

                // create a new thread object

                TcpClientHandler tcpClientHandler = new TcpClientHandler(s, dis, dos, dataStore);
                Thread t = new Thread(tcpClientHandler);
                // Invoking the start() method
                t.start();

            } catch (Exception e) {
                s.close();
                e.printStackTrace();
            }

        }
    }
}
