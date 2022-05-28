package com.maxpri.common.commands;

import com.maxpri.common.controllers.CommandManager;
import com.maxpri.common.entities.Person;
import com.maxpri.common.entities.PersonLoader;
import com.maxpri.common.network.Response;

import java.io.IOException;
import java.util.Collections;



public class AddIfMinCommand extends AbstractCommand {

    public AddIfMinCommand() {
        super("add_if_min", "adds new person if minimal value", 0);
    }

    @Override
    public Object[] readArgs(Object[] args) {
        try {
            Person personToAdd = PersonLoader.loadPerson();
            return new Object[]{personToAdd};
        } catch (IOException e) {
            System.out.println("Input error.");
            return null;
        }
    }

    @Override
    public Response execute(Object[] args) {

        Person newPerson = (Person) args[0];
        if (newPerson.compareTo(Collections.min(CommandManager.getCollectionManager().getPersons())) < 0) {
            CommandManager.getCollectionManager().add(newPerson);
            return new Response("New person successfully created!");
        } else {
            return new Response("Given person is not minimal.");
        }

    }
}
