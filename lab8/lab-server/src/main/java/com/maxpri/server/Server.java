package com.maxpri.server;

import com.maxpri.common.controllers.CollectionManager;
import com.maxpri.common.controllers.CommandListener;
import com.maxpri.common.controllers.CommandManager;
import com.maxpri.common.db.DBWorker;
import com.maxpri.common.entities.Person;
import com.maxpri.common.state.PerformanceState;
import com.maxpri.server.request.RequestExecutor;
import com.maxpri.server.collection.CollectionManagerImpl;
import com.maxpri.server.db.DBConnector;
import com.maxpri.server.db.DBInitializer;
import com.maxpri.server.db.DBWorkerImpl;
import com.maxpri.server.utils.Encryptor;
import com.maxpri.server.utils.SHA1Encryptor;

import java.io.IOException;
import java.util.Set;

public final class Server {

    private Server() {
        throw new UnsupportedOperationException("This is an utility class and can not be instantiated");
    }

    public static void main(String[] args) {

        DBConnector dbConnector = new DBConnector();
        DBInitializer dbInitializer = new DBInitializer(dbConnector);
        if (dbInitializer.init() > 0) {
            Encryptor encryptor = new SHA1Encryptor();
            DBWorker dbWorker = new DBWorkerImpl(dbConnector, encryptor);
            Set<Person> personSet = dbWorker.selectAllPersons();
            if (personSet != null) {
                CollectionManager collectionManager = new CollectionManagerImpl(personSet);
                PerformanceState performanceState = PerformanceState.getInstance();
                CommandManager commandManager = new CommandManager(collectionManager, dbWorker);
                RequestExecutor requestExecutor = new RequestExecutor(commandManager);
                try {
                    ConnectionHandler connectionHandler = new ConnectionHandler(requestExecutor, performanceState);
                    new Thread(new CommandListener(commandManager, false)).start();
                    connectionHandler.run();
                } catch (IOException e) {
                    System.out.print(e.getMessage());
                }
            }
        }
    }
}
