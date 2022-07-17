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
import com.maxpri.common.commands.ShowCommand;
import com.maxpri.common.commands.SignInCommand;
import com.maxpri.common.commands.SignUpCommand;
import com.maxpri.common.commands.SumOfHeightCommand;
import com.maxpri.common.commands.UpdateCommand;
import com.maxpri.common.db.DBWorker;
import com.maxpri.common.entities.User;
import com.maxpri.common.exceptions.EndOfStreamException;
import com.maxpri.common.network.NetworkListener;
import com.maxpri.common.network.Request;
import com.maxpri.common.network.Response;
import com.maxpri.common.state.PerformanceState;
import com.maxpri.common.utils.DataNormalizer;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public final class CommandManager {

    private static final int HISTORY_LENGTH = 10;
    private final Queue<String> commandHistory = new ConcurrentLinkedQueue<>();
    private final Map<String, AbstractCommand> clientCommands = new HashMap<>();
    private final Map<String, AbstractCommand> serverCommands = new HashMap<>();
    private final Map<String, AbstractCommand> commandsSendingWithoutSending = new HashMap<>();
    private final Map<String, AbstractCommand> commandsWithoutAuth = new HashMap<>();
    private final ReadWriteLock lock = new ReentrantReadWriteLock(true);
    private NetworkListener networkListener;
    private CollectionManager collectionManager;
    private DBWorker dbWorker;
    private PerformanceState performanceState;

    {
        performanceState = PerformanceState.getInstance();
    }

    public CommandManager(CollectionManager collectionManager, DBWorker dbWorker) {
        this.collectionManager = collectionManager;
        this.dbWorker = dbWorker;
    }

    public CommandManager(NetworkListener networkListener) {
        this.networkListener = networkListener;
    }

    {
        serverCommands.put("exit", new ExitCommand(this));

        commandsSendingWithoutSending.putAll(serverCommands);
        commandsSendingWithoutSending.put("execute_script", new ExecuteScriptCommand(this));

        commandsWithoutAuth.putAll(serverCommands);
        commandsWithoutAuth.put("sign_in", new SignInCommand(this));
        commandsWithoutAuth.put("sign_up", new SignUpCommand(this));
        commandsWithoutAuth.put("help", new HelpCommand(this));

        clientCommands.putAll(serverCommands);
        clientCommands.putAll(commandsSendingWithoutSending);
        clientCommands.putAll(commandsWithoutAuth);
        clientCommands.put("add", new AddCommand(this));
        clientCommands.put("add_if_min", new AddIfMinCommand(this));
        clientCommands.put("clear", new ClearCommand(this));
        clientCommands.put("history", new HistoryCommand());
        clientCommands.put("info", new InfoCommand(this));
        clientCommands.put("remove_any_by_height", new RemoveAnyByHeightCommand(this));
        clientCommands.put("remove_by_id", new RemoveByIdCommand(this));
        clientCommands.put("remove_greater", new RemoveGreaterCommand());
        clientCommands.put("sum_of_height", new SumOfHeightCommand(this));
        clientCommands.put("update", new UpdateCommand(this));
        clientCommands.put("group_counting_by_height", new GroupCountingByHeightCommand(this));
        clientCommands.put("show", new ShowCommand());
    }

    public PerformanceState getPerformanceState() {
        if (networkListener == null) {
            return performanceState;
        }
        return networkListener.getPerformanceState();
    }

    public synchronized Map<String, AbstractCommand> getCommandsWithoutAuth() {
        return commandsWithoutAuth;
    }

    public DBWorker getDBWorker() {
        return dbWorker;
    }

    public CollectionManager getCollectionManager() {
        return collectionManager;
    }

    public NetworkListener getNetworkListener() {
        return networkListener;
    }

    public Map<String, AbstractCommand> getCommands() {
        return clientCommands;
    }

    public Queue<String> getCommandHistory() {
        return commandHistory;
    }

    public void addCommandToHistory(String commandName) {
        Lock writeLock = lock.writeLock();
        try {
            writeLock.lock();
            if (!Objects.equals(commandName, "show")) {
                commandHistory.add(commandName);
            }
            if (commandHistory.size() > HISTORY_LENGTH) {
                commandHistory.poll();
            }
        } finally {
            writeLock.unlock();
        }
    }

    public Response onCommandReceived(String inputData, boolean fromClient, User user) {
        Map<String, AbstractCommand> commands = serverCommands;
        if (fromClient) {
            commands = clientCommands;
        }
        String[] commandWithRawArgs = DataNormalizer.normalize(inputData);
        String commandName = commandWithRawArgs[0].toLowerCase(Locale.ROOT);
        String[] rawArgs = Arrays.copyOfRange(commandWithRawArgs, 1, commandWithRawArgs.length);
        if (commands.containsKey(commandName)) {
            AbstractCommand command = commands.get(commandName);
            try {
                return processCommand(command, rawArgs, user);
            } catch (EndOfStreamException e) {
                getPerformanceState().switchPerformanceStatus();
            }

        }
        return new Response("No such command, call \"help\" to see the list of commands.", false);
    }

    public Response processCommand(AbstractCommand command, String[] rawArgs, User user) throws EndOfStreamException {
        if (rawArgs.length == command.getInlineArgsCount()) {
            Object[] rawArgsAndUser = new Object[rawArgs.length + 1];
            System.arraycopy(rawArgs, 0, rawArgsAndUser, 0, rawArgs.length);
            rawArgsAndUser[rawArgsAndUser.length - 1] = user;

            Object[] commandArgs = command.readArgs(rawArgsAndUser);
            if (commandArgs != null) {
                if (commandsSendingWithoutSending.containsKey(command.getName())) {
                    return executeCommand(command, commandArgs);
                } else {
                    try {
                        return networkListener.listen(new Request(command, commandArgs));
                    } catch (IOException e) {
                        return null;
                    }
                }
            }

        } else {
            return new Response("Wrong number of arguments.", false);
        }
        return null;
    }



    public Response executeCommand(AbstractCommand command, Object[] args) {
        addCommandToHistory(command.getName());
        return command.execute(args);
    }

}
