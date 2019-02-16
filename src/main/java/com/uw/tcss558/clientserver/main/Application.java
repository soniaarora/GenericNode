package com.uw.tcss558.clientserver.main;

import com.uw.tcss558.clientserver.client.Client;
import com.uw.tcss558.clientserver.client.TcpClient;
import com.uw.tcss558.clientserver.client.UdpClient;
import com.uw.tcss558.clientserver.server.Server;
import com.uw.tcss558.clientserver.server.ServerFactory;

import java.io.IOException;
import java.util.Arrays;

public class Application {

    public static void main(String[] args) throws IOException {
        
        if(args.length == 0) {
            printHelpMessage();
            return;
        }
        ServerFactory serverFactory = new ServerFactory();
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
                Server tcpServer =  serverFactory.getServer(args[0], port);                
                tcpServer.execute();
            }
            else {
                Server udpServer = serverFactory.getServer(args[0], port);
                udpServer.execute();
            }

        }
    }
    
    private static void printHelpMessage() {
        StringBuilder buf = new StringBuilder();

        buf.append("\nUsage:");
        buf.append("\nClient:");
        buf.append("\nuc/tc <address> <port> put <key> <msg> UDP/TCP CLIENT: Put an object into store");
        buf.append("\nuc/tc <address> <port> get <key> UDP/TCP CLIENT: Get an object from store by key");
        buf.append("\nuc/tc <address> <port> del <key> UDP/TCP CLIENT: Delete an object from storeby key");
        buf.append("\nuc/tc <address> <port> store UDP/TCP CLIENT: Display object store");
        buf.append("\nuc/tc <address> <port> exit UDP/TCP CLIENT: Shutdown server\n");
        buf.append("\nServer:");
        buf.append("\nus/ts <port> UDP/TCP/TCP-and-UDP SERVER: run server on <port>.");     
        System.out.println(buf.toString());
    }
}
