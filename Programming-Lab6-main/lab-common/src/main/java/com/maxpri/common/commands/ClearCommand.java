package com.maxpri.common.commands;


import com.maxpri.common.controllers.CommandManager;
import com.maxpri.common.network.Response;


public class ClearCommand extends AbstractCommand {

    public ClearCommand() {
        super("clear", "deletes all collection elements", 0);
    }

    @Override
    public Object[] readArgs(Object[] args) {
        return new Object[0];
    }

    @Override
    public Response execute(Object[] args) {
        CommandManager.getCollectionManager().clear();
        return new Response("Collection has been cleared.");
    }

}
