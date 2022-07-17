package com.maxpri.common.commands;

import com.maxpri.common.controllers.CommandManager;
import com.maxpri.common.network.Response;

public class GroupCountingByHeightCommand extends AbstractCommand {

    public GroupCountingByHeightCommand(CommandManager commandManager) {
        super(commandManager, "group_counting_by_height", "outputs the number of group members", 0);
    }

    @Override
    public Response execute(Object[] args) {
        getCommandManager().getCollectionManager().makeGroupsByHeight();
        return new Response(getCommandManager().getCollectionManager().outputGroupsByHeight(), true);
    }
}
