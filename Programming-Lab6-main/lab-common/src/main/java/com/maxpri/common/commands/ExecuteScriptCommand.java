package com.maxpri.common.commands;

import com.maxpri.common.controllers.CommandListener;
import com.maxpri.common.network.Response;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;


public class ExecuteScriptCommand extends AbstractCommand {

    private static final Set<String> FILE_HISTORY = new HashSet<>();

    public ExecuteScriptCommand() {
        super("execute_script", "executes the script with commands", 1);
    }

    @Override
    public Object[] readArgs(Object[] args) {
        return args;
    }

    @Override
    public Response execute(Object[] args) {
        String fileName = (String) args[0];
        if (FILE_HISTORY.contains(fileName)) {
            return new Response("There is a problem: script will loop.");
        } else {
            try {
                CommandListener listenerFromFile = new CommandListener(new FileReader(fileName));
                FILE_HISTORY.add(fileName);
                listenerFromFile.run();
            } catch (IOException e) {
                return new Response(e.getMessage());
            }
            FILE_HISTORY.remove(fileName);
        }
        return new Response("Exiting from " + fileName);
    }
}
