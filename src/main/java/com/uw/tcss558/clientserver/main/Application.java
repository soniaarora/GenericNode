package com.uw.tcss558.clientserver.main;

import com.uw.tcss558.clientserver.client.Client;
import com.uw.tcss558.clientserver.client.RmiClient;
import com.uw.tcss558.clientserver.client.TcpClient;
import com.uw.tcss558.clientserver.client.UdpClient;
import com.uw.tcss558.clientserver.server.Server;
import com.uw.tcss558.clientserver.server.ServerFactory;
import com.uw.tcss558.clientserver.server.rmi.Rmi;
import com.uw.tcss558.clientserver.server.rmi.RmiServer;

import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Arrays;
import java.util.Scanner;

public class Application {

    public static void main(String[] args) throws IOException {

        if(args.length == 0) {
            printHelpMessage();
            return;
        }

        ServerFactory serverFactory = new ServerFactory();
        //if 1st argument is tc or uc or rmic execute the client
        //otherwise execute server
        if (args[0].equals("tc") || args[0].equals("uc") || args[0].equals("rmic")) {

            if(args[0].equals("tc")) {
                String hostName = args[1];
                String port = args[2];
                String command = String.join(" ", Arrays.copyOfRange(args, 3, args.length));
                Client tcpClient = new TcpClient(hostName, port, command);
                tcpClient.execute();
            }
            else if (args[0].equals("uc")){
                String hostName = args[1];
                String port = args[2];
                String command = String.join(" ", Arrays.copyOfRange(args, 3, args.length));
                Client udpClient = new UdpClient(hostName, port, command);
                udpClient.execute();
            }
            else {
                String hostName = args[1];
                String command = String.join(" ", Arrays.copyOfRange(args, 2, args.length));
                Client rmiClient = new RmiClient(hostName, command);
                rmiClient.execute();
            }

        } else if(args[0].equals("ts") || args[0].equals("us") || args[0].equals("rmis")) {
            if(args[0].equals("ts")) {
                String port = args[1];
                Server tcpServer =  serverFactory.getServer(args[0], port);                
                tcpServer.execute();
            }
            else if (args[0].equals("us")){
                String port = args[1];
                Server udpServer = serverFactory.getServer(args[0], port);
                udpServer.execute();
            }
            else {
                runRegistry();
            }
        }
    }

    private static void runRegistry() {
        try {
            RmiServer obj = new RmiServer();
            Rmi stub = (Rmi) UnicastRemoteObject.exportObject(obj, 0);

            // Bind the remote object's stub in the registry
            Registry registry = LocateRegistry.getRegistry();
            registry.bind("Rmi", stub);

            System.err.println("Server ready");
            Scanner scanner = new Scanner(System.in);
            scanner.nextLine();
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
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
