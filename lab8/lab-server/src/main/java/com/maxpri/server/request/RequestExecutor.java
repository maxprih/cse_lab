package com.maxpri.server.request;

import com.maxpri.common.commands.AddCommand;
import com.maxpri.common.controllers.CommandManager;
import com.maxpri.common.network.Request;
import com.maxpri.common.network.Response;
import com.maxpri.common.network.Serializer;

import java.util.Objects;


public class RequestExecutor {

    private final CommandManager commandManager;

    public RequestExecutor(CommandManager commandManager) {
        this.commandManager = commandManager;
    }

    public Response execute(byte[] bytesOfRequest) {
        Serializer serializer = new Serializer();
        Request request = (Request) serializer.deserialize(bytesOfRequest);
        request.getCommand().setCommandManager(commandManager);
        if (commandManager.getCommandsWithoutAuth().containsKey(request.getCommand().getName())) {
            return commandManager.executeCommand(request.getCommand(), request.getArgs());
        }
        long checkUserResult = commandManager.getDBWorker().checkUser(request.getUser());
        if (checkUserResult < 0) {
            return new Response("DB problems, try again later.", false);
        }
        if (checkUserResult == 0) {
            return new Response("Sign in/up first, call \"help\" to see list of commands.", false);
        }

        return commandManager.executeCommand(request.getCommand(), request.getArgs());
    }

}
