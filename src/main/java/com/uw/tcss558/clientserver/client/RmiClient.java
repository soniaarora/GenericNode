package com.uw.tcss558.clientserver.client;

import com.uw.tcss558.clientserver.server.rmi.Rmi;

import java.io.IOException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RmiClient implements Client{
    private String serverAddress;
    private String command;

    public RmiClient(String add, String cmd) {
        this.serverAddress = add;
        this.command = cmd;
    }

    public void execute()  {

        try {
            Registry registry = LocateRegistry.getRegistry(serverAddress);
            Rmi stub = (Rmi) registry.lookup("Rmi");
            String response = stub.execute(command);
            System.out.println("Server response:\r\n" + response.trim());
        } catch (Exception e) {
            //System.err.println("Client exception: " + e.toString());
        }
    }
}
