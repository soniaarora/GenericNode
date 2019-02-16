package com.uw.tcss558.clientserver.server;

import com.uw.tcss558.clientserver.datastore.DataStore;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;

public class UdpClientHandler extends ClientHandler implements Runnable{
    DatagramSocket socket = null;
    DatagramPacket packet = null;


    public UdpClientHandler(DatagramSocket socket, DatagramPacket packet, DataStore dataStore) {
        super(dataStore);
        this.socket = socket;
        this.packet = packet;
    }

    @Override
    public void run() {
        byte[] sendData = new byte[65000];
        try {

            String command = new String( packet.getData(), 0, packet.getLength(), "UTF-8");
            System.out.println("RECEIVED: " + command);
            if(command.equals("exit"))
            {
                System.exit(1);
            }
            InetAddress IPAddress = packet.getAddress();
            int port =  packet.getPort();
            String result = parseAndExecuteCommand(command);
            int length = result.getBytes().length;
            System.out.println("Response length - " + length);
            if(length > 65000) {
                result = "TRIMMED: " + result;
            }

            sendData = result.getBytes();
            DatagramPacket sendPacket =
                    new DatagramPacket(sendData, sendData.length, IPAddress, port);
            socket.send(sendPacket);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
