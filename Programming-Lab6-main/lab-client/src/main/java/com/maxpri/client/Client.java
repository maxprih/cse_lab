package com.maxpri.client;

import com.maxpri.common.controllers.CommandListener;
import com.maxpri.common.controllers.CommandManager;


public final class Client {

    private Client() {

    }

    public static void main(String[] args) {
        ConnectionHandler connectionHandler = new ConnectionHandler();
        ClientNetworkListener clientListener = new ClientNetworkListener(connectionHandler);
        CommandManager.setNetworkListener(clientListener);
        CommandListener commandListener = new CommandListener();
        CommandListener.setOnClient();
        System.out.println("Start...");
        connectionHandler.openConnection();
        commandListener.run();
    }

}
