package com.maxpri.common.commands;



import com.maxpri.common.controllers.CommandManager;
import com.maxpri.common.network.Response;


public class ExitCommand extends AbstractCommand {

    public ExitCommand(CommandManager commandManager) {
        super(commandManager, "exit", "closes the app", 0);
    }

    @Override
    public Response execute(Object[] args) {
        getCommandManager().getPerformanceState().switchPerformanceStatus();
        return new Response("Shutting down.");
    }

}
