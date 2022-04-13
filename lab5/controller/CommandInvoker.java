package controller;

import service.Command;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * Class responsible for invocation of commands
 */
public class CommandInvoker {

    private final HashMap<String, Command> commands = new HashMap<>();
    /**
     * Last 15 commands executed
     */
    private final LinkedList<String> history = new LinkedList<>();

    private boolean scriptOn;

    /**
     * Registering command in command map
     */
    public void register(Command command) {
        commands.put(command.getCommandName(), command);
    }

    /**
     * Invocation of executing command if exists
     *
     * @param args of command if needed
     */
    public void execute(String commandName, String [] args) {
        if (commandName.equals("history")) {
            System.out.println("Command history:");
            for (String s: history) System.out.println(s);
            return;
        }
        if (commandName.equals("execute_script")) {
            if (scriptOn) {
                System.out.println("Cannot execute script from another execute script file");
                return;
            }
            try {
                executeScript(args);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            return;
        }
        Command command = commands.get(commandName);
        if (command == null) throw new IllegalStateException("No command registered for " + commandName);
        command.execute(args);
        if (history.size() > 15) history.removeFirst();
        history.addLast(commandName);
    }

    /**
     * Reading execute script and passing command execution to invoker
     *
     * @param args filename of script
     * @throws FileNotFoundException
     */
    private void executeScript(String [] args) throws FileNotFoundException {
        if (args.length != 1) throw new IllegalArgumentException("execute_scripts needs filename as an only argument");
        File script = new File(args[0]);
        if (!script.exists() || !script.canRead()) throw new SecurityException("Cannot read execute script file");
        scriptOn = true;
        Scanner scanner = new Scanner(script);
        String [] line;
        while (scanner.hasNextLine()) {
            try {
                line = scanner.nextLine().trim().split(" ");
                if (line.length < 1) continue;
                if (line.length == 1) execute(line[0], new String[0]);
                if (line.length > 1) execute(line[0], Arrays.copyOfRange(line, 1, line.length));
            } catch (IllegalStateException | IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        scriptOn = false;
        scanner.close();
    }

}
