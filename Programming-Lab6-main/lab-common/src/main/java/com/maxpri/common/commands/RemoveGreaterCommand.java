package com.maxpri.common.commands;

import com.maxpri.common.controllers.CommandManager;
import com.maxpri.common.entities.Person;
import com.maxpri.common.entities.PersonLoader;
import com.maxpri.common.network.Response;

import java.io.IOException;

public class RemoveGreaterCommand extends AbstractCommand {

    public RemoveGreaterCommand() {
        super("remove_greater", "remove all elements greater than given", 0);
    }

    @Override
    public Object[] readArgs(Object[] args) {
        try {
            Person comparedPerson = PersonLoader.loadPerson();
            return new Object[]{comparedPerson};
        } catch (IOException e) {
            System.out.println("Input error.");
        }
        return null;
    }

    @Override
    public Response execute(Object[] args) {
        int collectionLen = CommandManager.getCollectionManager().getPersons().size();
        CommandManager.getCollectionManager().getPersons().removeIf(person -> person.compareTo((Person) args[0]) > 0);
        return new Response((collectionLen - CommandManager.getCollectionManager().getPersons().size()) + " object(s) was deleted.");
    }
}
