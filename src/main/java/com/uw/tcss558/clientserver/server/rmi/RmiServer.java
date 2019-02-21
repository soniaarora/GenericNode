package com.uw.tcss558.clientserver.server.rmi;

import com.uw.tcss558.clientserver.datastore.DataStore;
import com.uw.tcss558.clientserver.datastore.InMemoryDataStore;

import java.rmi.RemoteException;

public class RmiServer implements Rmi
{
    private DataStore dataStore = InMemoryDataStore.getInstance();

    @Override
    public String execute(String cmd)  throws RemoteException {
        return parseAndExecuteCommand(cmd);
    }

    private String parseAndExecuteCommand(String cmd) {
        String output = "";
        String[] commandsList = cmd.split(" ");
        String operation = commandsList[0];
        String key = commandsList.length > 1 ? commandsList[1] : null;
        String value = commandsList.length > 2 ? commandsList[2] : null;
        switch (operation.toLowerCase()) {
            case "get":
                output = dataStore.get(key);
                break;
            case "put":
                output = dataStore.put(key, value);
                break;
            case "del":
                output = dataStore.delete(key);
                break;
            case "store":
                output = dataStore.store();
                break;
        }

        return output;
    }
}
