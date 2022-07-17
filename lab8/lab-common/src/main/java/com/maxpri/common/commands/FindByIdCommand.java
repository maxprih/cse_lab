package com.maxpri.common.commands;

import com.maxpri.common.controllers.CommandManager;
import com.maxpri.common.entities.Person;
import com.maxpri.common.entities.User;
import com.maxpri.common.network.Response;

import java.util.Objects;

public class FindByIdCommand extends AbstractCommand {

    public FindByIdCommand(CommandManager commandManager) {
        super(commandManager, "", "", 0);
    }

    @Override
    public Response execute(Object[] args) {
        User user = (User) args[1];
        Person foundPerson = getCommandManager().getCollectionManager().findById((Long) args[0]);
        if (foundPerson.getId() == -1) {
            return new Response("Person was not found.", null, false);
        }
        if (!Objects.equals(foundPerson.getAuthor(), user.getLogin())) {
            return new Response("You do not have rights to the person with such id.", false);
        }
        return new Response(foundPerson.currentValues(), user, true);
    }

}
