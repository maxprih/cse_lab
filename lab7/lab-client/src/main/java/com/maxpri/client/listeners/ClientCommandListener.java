package com.maxpri.client.listeners;

import com.maxpri.common.controllers.CommandListener;
import com.maxpri.common.controllers.CommandManager;
import com.maxpri.common.entities.User;

public class ClientCommandListener extends CommandListener {

    public ClientCommandListener(CommandManager commandManager) {
        super(commandManager, true);
    }

    public void launch() {
        setUser(new User());
        run();
    }

    @Override
    public void outputUserName() {
        System.out.print(getUser().getLogin() + "$ " + ANSI_RESET);
    }

}
