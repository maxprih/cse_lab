package com.maxpri.client.listeners;

import com.maxpri.client.ClientDispatcher;
import com.maxpri.client.ConnectionHandler;
import com.maxpri.common.network.NetworkListener;
import com.maxpri.common.network.Request;
import com.maxpri.common.network.Response;
import com.maxpri.common.network.Serializer;
import com.maxpri.common.state.PerformanceState;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

public final class ClientNetworkListener implements NetworkListener {

    private static final int TIMEOUT = 20000;
    private final ConnectionHandler connectionHandler;
    private final Reader reader = new InputStreamReader(System.in);

    private ClientNetworkListener(ConnectionHandler connectionHandler) {
        this.connectionHandler = connectionHandler;
    }


    private static class NetworkListenerHolder {
        public static final NetworkListener HOLDER_INSTANCE = new ClientNetworkListener(
                ConnectionHandler.getInstance());
    }


    public static NetworkListener getInstance() {
        return NetworkListenerHolder.HOLDER_INSTANCE;
    }

    @Override
    public synchronized Response listen(Request request) throws IOException {
        if (!connectionHandler.isOpen()) {
            connectionHandler.openConnection("localhost",
                    3132);
        }

        try {
            Serializer serializer = new Serializer();
            ClientDispatcher clientDispatcher = new ClientDispatcher(serializer);
            clientDispatcher.send(request, connectionHandler.getOutputStream());
            connectionHandler.getSocket().setSoTimeout(TIMEOUT);
            return clientDispatcher.receive(connectionHandler.getInputStream(), connectionHandler.getSocket().getReceiveBufferSize());
        } catch (IOException e) {
            connectionHandler.close();
            throw e;
        }

    }

    @Override
    public PerformanceState getPerformanceState() {
        return connectionHandler.getPerformanceState();
    }
}
