package com.maxpri.common.commands;

import com.maxpri.common.controllers.CommandManager;
import com.maxpri.common.entities.User;
import com.maxpri.common.network.Response;

public class ClearCommand extends AbstractCommand {

    public ClearCommand() {
        super( "clear", "deletes all collection elements", 0);
    }

    public ClearCommand(CommandManager commandManager) {
        super(commandManager, "clear", "deletes all collection elements", 0);
    }

    @Override
    public Object[] readArgs(Object[] args) {
        return new Object[]{args[0]};
    }

    @Override
    public Response execute(Object[] args) {
        User user = (User) args[0];
        if (getCommandManager().getDBWorker().deletePersonsByUser(user) < 0) {
            return new Response("Could not clear persons, db problems.", false);
        }
        getCommandManager().getCollectionManager().clear(user.getLogin());
        return new Response("Your persons has been deleted.", true);
    }

}
