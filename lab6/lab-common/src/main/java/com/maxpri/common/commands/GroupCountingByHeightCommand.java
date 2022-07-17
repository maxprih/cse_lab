package com.maxpri.common.commands;

import com.maxpri.common.controllers.CommandManager;
import com.maxpri.common.network.Response;

public class GroupCountingByHeightCommand extends AbstractCommand {

    public GroupCountingByHeightCommand() {
        super("group_counting_by_height", "outputs the number of group members", 0);
    }

    @Override
    public Object[] readArgs(Object[] args) {
        return new Object[0];
    }

    @Override
    public Response execute(Object[] args) {
        CommandManager.getCollectionManager().makeGroupsByHeight();
        return new Response(CommandManager.getCollectionManager().outputGroupsByHeight());
    }
}
