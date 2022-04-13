package service;

import controller.Client;
import pojo.MusicBand;

/**
 * Removing first element of collection
 */
public class RemoveFirst extends Command {

    @Override
    public void execute(String[] args) {
        if (Client.bands.isEmpty()) {
            System.out.println("Collection is empty");
            return;
        }
        int id = Client.bands.remove(0).getId();
        for (MusicBand b: Client.bands) {
            if (b.getId() > id) b.setId(b.getId() - 1);
        }
        MusicBand.setCount(MusicBand.getCount() - 1);
        System.out.println("First band removed successfully");
    }

    @Override
    public String getCommandName() {
        return "remove_first";
    }

}
