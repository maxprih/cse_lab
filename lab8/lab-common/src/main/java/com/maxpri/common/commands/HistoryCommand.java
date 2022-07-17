package com.maxpri.common.commands;

import com.maxpri.common.controllers.CommandManager;
import com.maxpri.common.network.Response;

import java.util.Queue;
import java.util.stream.Collectors;

public class HistoryCommand extends AbstractCommand {

    public HistoryCommand() {
        super("history", "outputs last 10 commands", 0);
    }

    @Override
    public Response execute(Object[] args) {
        if (getCommandManager().getCommandHistory().size() == 0) {
            return new Response("History is empty.", true);
        } else {
            Queue<String> history = getCommandManager().getCommandHistory();
            return new Response(history.stream().
                    limit(history.size() - 1).
                    collect(Collectors.joining("\n")), true);
        }
    }
}
