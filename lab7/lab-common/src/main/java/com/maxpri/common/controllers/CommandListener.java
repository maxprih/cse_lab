package com.maxpri.common.controllers;


import com.maxpri.common.entities.User;
import com.maxpri.common.network.Response;
import com.maxpri.common.network.ResponseExecutor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;


public class CommandListener implements Runnable {

    public static final String ANSI_RESET = "\u001B[0m";

    private final boolean onClient;
    private final Reader reader;
    private final CommandManager commandManager;
    private User user;

    public CommandListener(Reader reader, CommandManager commandManager, boolean onClient) {
        this.reader = reader;
        this.commandManager = commandManager;
        this.onClient = onClient;
    }

    public CommandListener(CommandManager commandManager, boolean onClient) {
        this(new InputStreamReader(System.in), commandManager, onClient);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isOnClient() {
        return onClient;
    }

    public void run() {
        try (BufferedReader in = new BufferedReader(reader)) {
            while (commandManager.getPerformanceState().getPerformanceStatus()) {
                if (!(reader.getClass() == FileReader.class)) {
                    System.out.println("===========================");
                }
                outputUserName();
                String input = in.readLine();
                if (input == null) {
                    break;
                }
                if (!"".equals(input)) {
                    Response response = commandManager.onCommandReceived(input, isOnClient(), user);
                    if (response != null) {
                        ResponseExecutor responseExecutor = new ResponseExecutor(response, this);
                        responseExecutor.execute();
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Invalid i/o.");
        }

    }

    public void outputUserName() {
    }

}
