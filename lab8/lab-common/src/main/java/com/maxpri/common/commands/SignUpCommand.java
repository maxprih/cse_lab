package com.maxpri.common.commands;

import com.maxpri.common.controllers.CommandManager;
import com.maxpri.common.entities.User;
import com.maxpri.common.entities.UserLoader;
import com.maxpri.common.exceptions.EndOfStreamException;
import com.maxpri.common.network.Response;

import java.io.IOException;

public class SignUpCommand extends AbstractCommand {

    public SignUpCommand() {
        super("sign_up", "sign up user", 0);
    }

    public SignUpCommand(CommandManager commandManager) {
        super(commandManager, "sign_up", "sign up user", 0);
    }

    @Override
    public Object[] readArgs(Object[] args) throws EndOfStreamException {
        try {
            UserLoader userLoader = new UserLoader();
            User newUser = userLoader.loadUser();
            return new Object[]{newUser};
        } catch (IOException e) {
            return null;
        }
    }

    @Override
    public Response execute(Object[] args) {
        User user = (User) args[0];
        long result = getCommandManager().getDBWorker().addUser(user);
        if (result <= 0) {
            return new Response("Failed to create a user, this login is already occupied.", false);
        }
        return new Response("Signed up.", user, true);
    }

}
