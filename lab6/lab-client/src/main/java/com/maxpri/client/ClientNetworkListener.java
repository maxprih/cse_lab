package com.maxpri.client;


import com.maxpri.common.network.NetworkListener;
import com.maxpri.common.network.Request;
import com.maxpri.common.network.Response;

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
                ClientDispatcher.send(request, connectionHandler.getOutputStream());
                connectionHandler.getSocket().setSoTimeout(TIMEOUT);
                response = ClientDispatcher.receive(connectionHandler.getInputStream(), connectionHandler.getSocket().getReceiveBufferSize());
            } catch (IOException e) {
                System.out.println(e.getMessage());
                connectionHandler.close();
            }
        }
        return response;
    }

}
