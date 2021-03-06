package com.maxpri.server.request;

import com.maxpri.common.controllers.CommandManager;
import com.maxpri.common.network.Request;
import com.maxpri.common.network.Response;


public class RequestExecutor {

    private final CommandManager commandManager;

    public RequestExecutor(CommandManager commandManager) {
        this.commandManager = commandManager;
    }

    public Response execute(Request request) {
        request.getCommand().setCommandManager(commandManager);
        if (commandManager.getCommandsWithoutAuth().containsKey(request.getCommand().getName())) {
            return commandManager.executeCommand(request.getCommand(), request.getArgs());
        }
        long checkUserResult = commandManager.getDBWorker().checkUser(request.getUser());
        if (checkUserResult < 0) {
            return new Response("DB problems, try again later.");
        }
        if (checkUserResult == 0) {
            return new Response("Sign in/up first, call \"help\" to see list of commands.");
        }
        return commandManager.executeCommand(request.getCommand(), request.getArgs());
    }

}
