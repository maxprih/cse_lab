package service;

import controller.Client;
import pojo.MusicBand;

/**
 * Showing all the objects from collection
 */
public class Show extends Command {

    @Override
    public void execute(String[] args) {
        if (Client.bands.isEmpty()) {
            System.out.println("Collection is empty");
            return;
        }
        for (MusicBand b: Client.bands) {
            System.out.println(b);
        }
    }

    @Override
    public String getCommandName() {
        return "show";
    }

}
