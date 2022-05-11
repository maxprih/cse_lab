package client;


import Server.JsonProcessing;
import client.tools.Console;

import java.io.*;
import java.util.logging.*;


/**
 * @author Калабухов Максим
 */

public class Run {
    public static Logger log = Logger.getGlobal();
    public static void main(String[] args){
        client.tools.Console console = new Console();
        Handler userFileHandler = null;
        try {
            userFileHandler = new FileHandler("UserLogs.log");
        } catch (IOException e) {
            Run.log.warning("IOException in client");
            System.out.println("Logger problems");
        }
        log.setUseParentHandlers(false);
        log.addHandler(userFileHandler);
        Run.log.info("Program is running.");
        console.interactiveMod();
        Run.log.info("Program successful complete.");


    }

}
