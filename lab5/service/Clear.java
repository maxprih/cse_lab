package service;

import controller.Client;
import pojo.MusicBand;

/**
 * Clearing collection of objects
 */
public class Clear extends Command {

    @Override
    public void execute(String[] args) {
        Client.bands.clear();
        MusicBand.setCount(0);
        System.out.println("Collection is clear");
    }

    @Override
    public String getCommandName() {
        return "clear";
    }

}
