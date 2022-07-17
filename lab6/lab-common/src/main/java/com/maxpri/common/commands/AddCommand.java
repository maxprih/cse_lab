package com.maxpri.common.commands;

import com.maxpri.common.controllers.CommandManager;
import com.maxpri.common.entities.Person;
import com.maxpri.common.entities.PersonLoader;
import com.maxpri.common.network.Response;

import java.io.IOException;


public class AddCommand extends AbstractCommand {

    public AddCommand() {
        super("add", "adds element to collection", 0);
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
        Person personToAdd = (Person) args[0];
        CommandManager.getCollectionManager().add(personToAdd);
        return new Response("New person successfully created!");
    }
}
