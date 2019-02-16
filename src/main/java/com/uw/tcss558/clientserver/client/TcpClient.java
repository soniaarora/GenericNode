package com.uw.tcss558.clientserver.client;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class TcpClient implements Client{

    private String serverAddress;
    private String portNumber;
    private String command;

    public TcpClient(String add, String port, String cmd) {
        this.serverAddress = add;
        this.portNumber = port;
        this.command = cmd;
    }

    public void execute() throws IOException{
        int port = Integer.parseInt(portNumber);

        // getting localhost ip
        //InetAddress ip = InetAddress.getByName("localhost");

        // establish the connection with server port 5056
        Socket s = new Socket(serverAddress,   port);

        // obtaining input and out streams
        DataInputStream dis = new DataInputStream(s.getInputStream());
        DataOutputStream dos = new DataOutputStream(s.getOutputStream());
        try {

            dos.writeUTF(command);
            if(command.toLowerCase().equals("exit")) {
                System.exit(1);
            }
            String received = dis.readUTF();
            System.out.println("Server response:\r\n"+received);

        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            // closing resources
            dis.close();
            dos.close();
            s.close();
        }
    }

}



