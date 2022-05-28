package com.maxpri.common.commands;


import com.maxpri.common.controllers.CommandManager;
import com.maxpri.common.network.Response;

public class ShowCommand extends AbstractCommand {

    public ShowCommand() {
        super("show", "outputs all collection elements", 0);
    }


    @Override
    public Object[] readArgs(Object[] args) {
        return new Object[0];
    }

    @Override
    public Response execute(Object[] args) {
        return new Response(CommandManager.getCollectionManager().show());
    }

}
