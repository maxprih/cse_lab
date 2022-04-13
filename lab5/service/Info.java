package service;

import controller.Client;

/**
 * Printing info about collection
 */
public class Info extends Command {

    @Override
    public void execute(String[] args) {
        System.out.println("Collection type: Stack\n" +
                "Date of init: " + Client.initDate + "\n" +
                "Size: " + Client.bands.size());
    }

    @Override
    public String getCommandName() {
        return "info";
    }

}
