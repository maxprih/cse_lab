package com.maxpri.common.commands;

import com.maxpri.common.controllers.CommandManager;
import com.maxpri.common.entities.Person;
import com.maxpri.common.entities.PersonLoader;
import com.maxpri.common.entities.User;
import com.maxpri.common.exceptions.EndOfStreamException;
import com.maxpri.common.network.Request;
import com.maxpri.common.network.Response;
import com.maxpri.common.utils.DataNormalizer;

import java.io.IOException;
import java.util.Objects;

public class UpdateCommand extends AbstractCommand {

    public UpdateCommand() {
        super( "update", "updates person value", 1);
    }

    public UpdateCommand(CommandManager commandManager) {
        super(commandManager, "update", "updates person value", 1);
    }

    @Override
    public Object[] readArgs(Object[] args) throws EndOfStreamException {
        try {
            long id = Long.parseLong((String) args[0]);
            User user = (User) args[1];
            Response response = getCommandManager().getNetworkListener()
                    .listen(new Request(new FindByIdCommand(getCommandManager()), new Object[]{id, user}));
            if (response.isStatus()) {
                Person updatedPerson = PersonLoader.loadPersonWithCurrentValues(DataNormalizer.normalize(response.getMessage()));
                updatedPerson.setId(id);
                return new Object[]{updatedPerson, user};
            }
            if (response.getUser() == null) {
                System.out.println("You do not have rights to update this person.");
                return null;
            }
            System.out.println("No person found with such id.");
            return null;
        } catch (IOException e) {
            System.out.println("Input error.");
        } catch (NumberFormatException e) {
            System.out.println("Invalid format of id.");
        }
        return null;
    }

    @Override
    public Response execute(Object[] args) {
        Person updatedPerson = (Person) args[0];
        User user = (User) args[1];
        updatedPerson.setAuthor(user.getLogin());
        Person foundPerson = getCommandManager().getCollectionManager().findById((Long) args[0]);
        if (!Objects.equals(foundPerson.getAuthor(), user.getLogin())) {
            return new Response("You do not have rights to the person with such id.", false);
        }
        if (getCommandManager().getDBWorker().updatePerson(updatedPerson) <= 0) {
            return new Response("Could not update person because of DB problems.", false);
        }
        getCommandManager().getCollectionManager().update(updatedPerson);
        return new Response("Person successfully updated!", true);
    }
}
