package com.maxpri.common.commands;

import com.maxpri.common.controllers.CommandManager;
import com.maxpri.common.exceptions.EndOfStreamException;
import com.maxpri.common.network.Response;

import java.io.Serializable;



public abstract class AbstractCommand implements Serializable {

    private final String name;
    private final String description;
    private final int inlineArgsCount;
    private transient CommandManager commandManager;

    public AbstractCommand(CommandManager commandManager, String name, String description, int inlineArgsCount) {
        this.name = name;
        this.description = description;
        this.inlineArgsCount = inlineArgsCount;
        this.commandManager = commandManager;
    }

    public void setCommandManager(CommandManager commandManager) {
        this.commandManager = commandManager;
    }


    public abstract Response execute(Object[] args);

    public CommandManager getCommandManager() {
        return commandManager;
    }

    public Object[] readArgs(Object[] args) throws EndOfStreamException {
        return new Object[]{args[0]};
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getInlineArgsCount() {
        return inlineArgsCount;
    }
}
