package com.maxpri.common.commands;


import com.maxpri.common.controllers.CommandListener;
import com.maxpri.common.controllers.CommandManager;
import com.maxpri.common.network.Response;
import com.maxpri.common.state.State;

import java.io.IOException;


public class ExitCommand extends AbstractCommand {

    public ExitCommand() {
        super("exit", "closes the app", 0);
    }

    @Override
    public Object[] readArgs(Object[] args) {
        return new Object[0];
    }

    @Override
    public Response execute(Object[] args) {
        State.switchPerformanceStatus();
        if (!CommandListener.isOnClient()) {
            try {
                System.out.println("Saving...");
                CommandManager.getCollectionManager().write();
            } catch (IOException e) {
                return new Response(e.getMessage());
            }
        }
        return new Response("Shut down.");
    }

}
