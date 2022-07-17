package com.maxpri.common.network;

import com.maxpri.common.commands.AbstractCommand;

import java.io.Serializable;
import java.util.Arrays;

public class Request implements Serializable {

    private AbstractCommand command;
    private Object[] args;

    public Request(AbstractCommand command, Object[] args) {
        this.command = command;
        this.args = args;
    }

    public AbstractCommand getCommand() {
        return command;
    }

    public void setCommand(AbstractCommand command) {
        this.command = command;
    }

    public Object[] getArgs() {
        return args;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }

    @Override
    public String toString() {
        return "Request{"
                + "command=" + command
                + ", args=" + Arrays.toString(args)
                + '}';
    }
}
