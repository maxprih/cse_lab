package com.maxpri.common.controllers;

import com.maxpri.common.commands.AbstractCommand;
import com.maxpri.common.commands.AddCommand;
import com.maxpri.common.commands.AddIfMinCommand;
import com.maxpri.common.commands.ClearCommand;
import com.maxpri.common.commands.ExecuteScriptCommand;
import com.maxpri.common.commands.ExitCommand;
import com.maxpri.common.commands.GroupCountingByHeightCommand;
import com.maxpri.common.commands.HelpCommand;
import com.maxpri.common.commands.HistoryCommand;
import com.maxpri.common.commands.InfoCommand;
import com.maxpri.common.commands.RemoveAnyByHeightCommand;
import com.maxpri.common.commands.RemoveByIdCommand;
import com.maxpri.common.commands.RemoveGreaterCommand;
import com.maxpri.common.commands.SaveCommand;
import com.maxpri.common.commands.ShowCommand;
import com.maxpri.common.commands.SumOfHeightCommand;
import com.maxpri.common.commands.UpdateCommand;
import com.maxpri.common.network.NetworkListener;
import com.maxpri.common.network.Request;
import com.maxpri.common.network.Response;
import com.maxpri.common.utils.DataNormalizer;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Map;
import java.util.Queue;

public final class CommandManager {

    private static final int HISTORY_LENGTH = 10;
    private static final Queue<String> COMMAND_HISTORY = new LinkedList<>();
    private static final Map<String, AbstractCommand> CLIENT_COMMANDS = new HashMap<>();
    private static final Map<String, AbstractCommand> SERVER_COMMANDS = new HashMap<>();
    private static final Map<String, AbstractCommand> COMMANDS_EXECUTING_WITHOUT_SENDING = new HashMap<>();
    private static NetworkListener networkListener;
    private static CollectionManager collectionManager;

    private CommandManager() {

    }

    static {
        CLIENT_COMMANDS.put("add", new AddCommand());
        CLIENT_COMMANDS.put("add_if_min", new AddIfMinCommand());
        CLIENT_COMMANDS.put("clear", new ClearCommand());
        CLIENT_COMMANDS.put("exit", new ExitCommand());
        CLIENT_COMMANDS.put("history", new HistoryCommand());
        CLIENT_COMMANDS.put("info", new InfoCommand());
        CLIENT_COMMANDS.put("remove_any_by_height", new RemoveAnyByHeightCommand());
        CLIENT_COMMANDS.put("remove_by_id", new RemoveByIdCommand());
        CLIENT_COMMANDS.put("remove_greater", new RemoveGreaterCommand());
        CLIENT_COMMANDS.put("sum_of_height", new SumOfHeightCommand());
        CLIENT_COMMANDS.put("update", new UpdateCommand());
        CLIENT_COMMANDS.put("group_counting_by_height", new GroupCountingByHeightCommand());
        CLIENT_COMMANDS.put("execute_script", new ExecuteScriptCommand());
        CLIENT_COMMANDS.put("help", new HelpCommand());
        CLIENT_COMMANDS.put("show", new ShowCommand());

        SERVER_COMMANDS.put("save", new SaveCommand());
        SERVER_COMMANDS.put("exit", new ExitCommand());

        COMMANDS_EXECUTING_WITHOUT_SENDING.putAll(SERVER_COMMANDS);
        COMMANDS_EXECUTING_WITHOUT_SENDING.put("execute_script", new ExecuteScriptCommand());
    }

    public static void setCollectionManager(CollectionManager collectionManager) {
        CommandManager.collectionManager = collectionManager;
    }

    public static CollectionManager getCollectionManager() {
        return collectionManager;
    }

    public static void setNetworkListener(NetworkListener networkListener) {
        CommandManager.networkListener = networkListener;
    }

    public static NetworkListener getNetworkListener() {
        return networkListener;
    }

    public static Map<String, AbstractCommand> getCommands() {
        return CLIENT_COMMANDS;
    }

    public static Queue<String> getCommandHistory() {
        return COMMAND_HISTORY;
    }

    public static void addCommandToHistory(String commandName) {
        COMMAND_HISTORY.add(commandName);
        if (COMMAND_HISTORY.size() > HISTORY_LENGTH) {
            COMMAND_HISTORY.poll();
        }
    }

    public static Response onCommandReceived(String inputData) {
        boolean fromClient = CommandListener.isOnClient();
        Map<String, AbstractCommand> commands = SERVER_COMMANDS;
        if (fromClient) {
            commands = CLIENT_COMMANDS;
        }
        String[] commandWithRawArgs = DataNormalizer.normalize(inputData);
        String commandName = commandWithRawArgs[0].toLowerCase(Locale.ROOT);
        String[] rawArgs = Arrays.copyOfRange(commandWithRawArgs, 1, commandWithRawArgs.length);
        if (commands.containsKey(commandName)) {
            AbstractCommand command = commands.get(commandName);
            return processCommand(command, rawArgs);
        }
        return new Response("No such command, call \"help\" to see the list of commands.");
    }

    public static Response processCommand(AbstractCommand command, String[] rawArgs) {
        if (rawArgs.length == command.getInlineArgsCount()) {
            Object[] commandArgs = command.readArgs(rawArgs);
            if (commandArgs != null) {
                if (COMMANDS_EXECUTING_WITHOUT_SENDING.containsKey(command.getName())) {
                    return executeCommand(command, commandArgs);
                } else {
                    return networkListener.listen(new Request(command, commandArgs));
                }
            }
        } else {
            return new Response("Wrong number of arguments.");
        }
        return null;
    }

    public static Response executeCommand(AbstractCommand command, Object[] args) {
        CommandManager.addCommandToHistory(command.getName());
        return command.execute(args);
    }

}
