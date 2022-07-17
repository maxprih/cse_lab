package com.maxpri.client;

import com.maxpri.client.listeners.ClientCommandListener;
import com.maxpri.client.listeners.ClientNetworkListener;
import com.maxpri.common.controllers.CommandManager;
import com.maxpri.common.state.PerformanceState;


public final class Client {

    private Client() {

    }

    public static void main(String[] args) {
        PerformanceState performanceState = new PerformanceState();
        ConnectionHandler connectionHandler = new ConnectionHandler(performanceState);
        ClientNetworkListener clientListener = new ClientNetworkListener(connectionHandler);
        CommandManager commandManager = new CommandManager(clientListener, performanceState);
        ClientCommandListener commandListener = new ClientCommandListener(commandManager);
        System.out.println("Start...");
        connectionHandler.openConnection();
        commandListener.launch();
        connectionHandler.close();
    }

}
