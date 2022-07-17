package com.maxpri.client.listeners;

import com.maxpri.client.ClientDispatcher;
import com.maxpri.client.ConnectionHandler;
import com.maxpri.common.network.NetworkListener;
import com.maxpri.common.network.Request;
import com.maxpri.common.network.Response;
import com.maxpri.common.network.Serializer;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

public final class ClientNetworkListener implements NetworkListener {

    private static final int TIMEOUT = 10000;
    private final ConnectionHandler connectionHandler;
    private final Reader reader = new InputStreamReader(System.in);

    public ClientNetworkListener(ConnectionHandler connectionHandler) {
        this.connectionHandler = connectionHandler;
    }

    @Override
    public Response listen(Request request) {
        Response response = null;
        if (!connectionHandler.isOpen()) {
            connectionHandler.openConnection();
        }
        if (connectionHandler.isOpen()) {
            try {
                Serializer serializer = new Serializer();
                ClientDispatcher clientDispatcher = new ClientDispatcher(serializer);
                clientDispatcher.send(request, connectionHandler.getOutputStream());
                connectionHandler.getSocket().setSoTimeout(TIMEOUT);
                response = clientDispatcher.receive(connectionHandler.getInputStream(), connectionHandler.getSocket().getReceiveBufferSize());
            } catch (IOException e) {
                System.out.println(e.getMessage());
                connectionHandler.close();
            }
        }
        return response;
    }

}
