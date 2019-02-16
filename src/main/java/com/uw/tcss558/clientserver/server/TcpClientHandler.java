package com.uw.tcss558.clientserver.server;

import com.uw.tcss558.clientserver.datastore.DataStore;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class TcpClientHandler extends ClientHandler implements  Runnable {
    final DataInputStream dis;
    final DataOutputStream dos;
    final Socket s;
//    final DataStore dataStore;

    // Constructor
    public TcpClientHandler(Socket s, DataInputStream dis, DataOutputStream dos, DataStore dataStore) {
        super(dataStore);
        this.s = s;
        this.dis = dis;
        this.dos = dos;

    }

    @Override
    public void run() {
        String received;
        String toReturn;
        try {

            // receive the answer from client
            received = dis.readUTF();

            if (received.equals("exit")) {

                System.out.println("Client " + this.s + " sends exit...");
                System.out.println("Closing this connection.");
                this.s.close();
                System.out.println("Connection closed");
                System.exit(1);
            }

            // write on output stream based on the
            // answer from the client
            toReturn = parseAndExecuteCommand(received);
            dos.writeUTF(toReturn.trim());
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            // closing resources
            this.dis.close();
            this.dos.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}