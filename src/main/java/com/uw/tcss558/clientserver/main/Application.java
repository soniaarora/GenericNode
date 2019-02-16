package com.uw.tcss558.clientserver.main;

import com.uw.tcss558.clientserver.client.Client;
import com.uw.tcss558.clientserver.client.TcpClient;
import com.uw.tcss558.clientserver.client.UdpClient;
import com.uw.tcss558.clientserver.server.Server;
import com.uw.tcss558.clientserver.server.TcpServer;
import com.uw.tcss558.clientserver.server.UdpServer;

import java.io.IOException;
import java.util.Arrays;

public class Application {

    public static void main(String[] args) throws IOException {

        //if 1st argument is tc or uc execute the client
        //otherwise execute server
        if (args[0].equals("tc") || args[0].equals("uc")) {

            String hostName = args[1];
            String port = args[2];
            String command = String.join(" ", Arrays.copyOfRange(args, 3, args.length));
            if(args[0].equals("tc")) {
                Client tcpClient = new TcpClient(hostName, port, command);
                tcpClient.execute();
            }
            else {
                Client udpClient = new UdpClient(hostName, port, command);
                udpClient.execute();
            }

        } else if(args[0].equals("ts") || args[0].equals("us")) {
            String port = args[1];
            if(args[0].equals("ts")) {
                Server tcpServer = new TcpServer(port);
                tcpServer.execute();
            }
            else {
                Server udpServer = new UdpServer(port);
                udpServer.execute();
            }

        }
    }

}
