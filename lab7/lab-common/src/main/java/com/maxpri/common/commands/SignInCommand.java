package com.maxpri.common.commands;

import com.maxpri.common.controllers.CommandManager;
import com.maxpri.common.entities.User;
import com.maxpri.common.entities.UserLoader;
import com.maxpri.common.exceptions.EndOfStreamException;
import com.maxpri.common.network.Response;

import java.io.IOException;

public class SignInCommand extends AbstractCommand {

    public SignInCommand(CommandManager commandManager) {
        super(commandManager, "sign_in", "sign in user", 0);
    }

    @Override
    public Object[] readArgs(Object[] args) throws EndOfStreamException {
        try {
            UserLoader userLoader = new UserLoader();
            User user = userLoader.loadUser();
            return new Object[]{user};
        } catch (IOException e) {
            return null;
        }

    }

    @Override
    public Response execute(Object[] args) {
        User user = (User) args[0];
        long result = getCommandManager().getDBWorker().checkUser(user);
        if (result <= 0) {
            return new Response("Wrong credentials.");
        }
        user.setId(result);
        return new Response("Signed in.", user);
    }


}
