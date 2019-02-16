package com.uw.tcss558.clientserver.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

public class UdpClient implements Client{

    private String serverAddress;
    private String portNumber;
    private String command;

    public UdpClient(String add, String port, String cmd) {
        this.serverAddress = add;
        this.portNumber = port;
        this.command = cmd;
    }

    public void execute() throws IOException {

        BufferedReader inFromUser =
                new BufferedReader(new InputStreamReader(System.in));
        DatagramSocket clientSocket = new DatagramSocket();

        byte[] sendData = new byte[2056];
        byte[] receiveData = new byte[65000];
        InetAddress IPAddress = InetAddress.getByName(serverAddress);
        sendData = command.getBytes();
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress,Integer.parseInt(portNumber));
        clientSocket.send(sendPacket);
        if(command.toLowerCase().equals("exit")) {
            System.exit(1);
        }
        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
        clientSocket.receive(receivePacket);
        String response = new String(receivePacket.getData(), 0,receivePacket.getLength(), "UTF-8");
        System.out.println("Server response:\r\n" + response.trim());
        clientSocket.close();

    }
}
