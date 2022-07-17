package com.maxpri.common.commands;


import com.maxpri.common.controllers.CommandManager;
import com.maxpri.common.network.Response;

import java.io.IOException;

public class SaveCommand extends AbstractCommand {

    public SaveCommand() {
        super("save", "saves collection to the file", 0);
    }

    @Override
    public Object[] readArgs(Object[] args) {
        return new Object[0];
    }

    @Override
    public Response execute(Object[] args) {
        try {
            CommandManager.getCollectionManager().write();
            return new Response("Saved successfully!");
        } catch (IOException e) {
            return new Response(e.getMessage());
        }
    }
}
