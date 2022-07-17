package com.maxpri.common.commands;

import com.maxpri.common.controllers.CommandManager;
import com.maxpri.common.network.Response;

public class InfoCommand extends AbstractCommand {

    public InfoCommand(CommandManager commandManager) {
        super(commandManager, "info", "shows the info about collection", 0);
    }

    @Override
    public Response execute(Object[] args) {
        return new Response("Collection info:" + "\n" + getCommandManager().getCollectionManager().getInfo(), true);
    }
}
