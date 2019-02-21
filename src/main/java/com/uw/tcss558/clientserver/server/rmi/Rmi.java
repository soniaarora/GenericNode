package com.uw.tcss558.clientserver.server.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Rmi extends Remote {
    String execute(String search) throws RemoteException;;
}