package com.maxpri.common.commands;

import com.maxpri.common.network.Response;

import java.io.Serializable;



public abstract class AbstractCommand implements Serializable {

    private final String name;
    private final String description;
    private final int inlineArgsCount;

    public AbstractCommand(String name, String description, int inlineArgsCount) {
        this.name = name;
        this.description = description;
        this.inlineArgsCount = inlineArgsCount;
    }


    public abstract Response execute(Object[] args);

    public abstract Object[] readArgs(Object[] args);

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
