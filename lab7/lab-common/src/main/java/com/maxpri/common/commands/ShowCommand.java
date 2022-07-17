package com.maxpri.common.commands;


import com.maxpri.common.controllers.CommandManager;
import com.maxpri.common.network.Response;

public class ShowCommand extends AbstractCommand {

    public ShowCommand(CommandManager commandManager) {
        super(commandManager, "show", "outputs all collection elements", 0);
    }


    @Override
    public Response execute(Object[] args) {
        return new Response(getCommandManager().getCollectionManager().show());
    }

}
