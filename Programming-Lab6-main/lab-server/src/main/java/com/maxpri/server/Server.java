package com.maxpri.server;

import com.maxpri.common.controllers.CollectionManager;
import com.maxpri.common.controllers.CommandListener;
import com.maxpri.common.controllers.CommandManager;
import com.maxpri.server.collection.CollectionManagerImpl;
import com.maxpri.server.config.IOConfig;

import java.io.File;
import java.io.IOException;

public final class Server {

    private Server() {
    }

    public static void main(String[] args) {
        File inputFile = new File(args[0]);
        if (IOConfig.configure(inputFile)) {
            CollectionManager collectionManager = CollectionManagerImpl.initFromFile(IOConfig.COLLECTION_FILE_READER,
                    IOConfig.getInputFile());
            CommandManager.setCollectionManager(collectionManager);
            try {
                ConnectionHandler connectionHandler = new ConnectionHandler();
                Thread commandListenerThread = new Thread(new CommandListener());
                commandListenerThread.start();
                connectionHandler.run();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

}
