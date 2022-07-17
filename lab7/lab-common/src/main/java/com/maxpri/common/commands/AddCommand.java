package com.maxpri.common.commands;

import com.maxpri.common.controllers.CollectionManager;
import com.maxpri.common.controllers.CommandManager;
import com.maxpri.common.db.DBWorker;
import com.maxpri.common.entities.Person;
import com.maxpri.common.entities.PersonLoader;
import com.maxpri.common.entities.User;
import com.maxpri.common.exceptions.EndOfStreamException;
import com.maxpri.common.network.Response;

import java.io.IOException;



public class AddCommand extends AbstractCommand {

    public AddCommand(CommandManager commandManager) {
        super(commandManager, "add", "adds element to collection", 0);
    }

    @Override
    public Object[] readArgs(Object[] args) throws EndOfStreamException {
        try {
            Person personToAdd = PersonLoader.loadPerson();
            return new Object[]{personToAdd, args[0]};
        } catch (IOException e) {
            System.out.println("Input error.");
            return null;
        }
    }

    @Override
    public Response execute(Object[] args) {
        Person personToAdd = (Person) args[0];
        User user = (User) args[1];
        DBWorker dbWorker = getCommandManager().getDBWorker();
        CollectionManager collectionManager = getCommandManager().getCollectionManager();

        if (dbWorker.addPerson(personToAdd, user) <= 0) {
            return new Response("Couldn't create person.");
        }

        collectionManager.add(personToAdd);
        return new Response("New person successfully created!");
    }
}
