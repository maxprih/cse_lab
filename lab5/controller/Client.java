package controller;

import pojo.MusicBand;
import service.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

/**
 * Class responsible for logic of interactive app
 */
public class Client {

    /**
     * Collection of objects
     */
    public static Stack<MusicBand> bands;

    public static Date initDate;
    public static Scanner scanner;
    private final FileService fileService;
    private final CommandInvoker invoker;

    public Client(File file) {
        fileService = new FileService(file);
        bands = new Stack<>();
        initDate = new Date();
        scanner = new Scanner(System.in);
        invoker = new CommandInvoker();
    }

    /**
     * Registering available commands in command invoker
     */
    private void registerCommands() {
        invoker.register(new Help());
        invoker.register(new Info());
        invoker.register(new Show());
        invoker.register(new Add());
        invoker.register(new Update());
        invoker.register(new RemoveById());
        invoker.register(new Clear());
        invoker.register(new Exit());
        invoker.register(new RemoveFirst());
        invoker.register(new RemoveLast());
        invoker.register(new RemoveAnyByBestAlbum());
        invoker.register(new AverageOfNumberOfParticipants());
        invoker.register(new CountByGenre());
    }

    /**
     * Scanning command from user and pass to invoker for execution
     */
    private void scanCommand() {
        System.out.println("Enter command with(out) arguments (or help to see available commands): ");
        String [] line = scanner.nextLine().trim().split(" ");
        try {
            if (line.length == 1) {
                if (line[0].equals("save")) {
                    fileService.saveToFile();
                } else {
                    invoker.execute(line[0], new String[0]);
                    Collections.sort(bands);
                }
            }
            else if (line.length > 1) invoker.execute((line[0]),
                    Arrays.copyOfRange(line, 1, line.length));
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.out.println("Interaction with file failed (error occurred in file)");
        }
    }

    /**
     * Initializing app
     */
    public void init() {
        try {
            bands = fileService.loadFromFile();
        } catch (FileNotFoundException | SecurityException e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }
        registerCommands();
        while (true) {
            scanCommand();
        }
    }

}
