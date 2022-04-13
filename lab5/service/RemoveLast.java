package service;

import controller.Client;
import pojo.MusicBand;

/**
 * Removing last element of collection
 */
public class RemoveLast extends Command {

    @Override
    public void execute(String[] args) {
        if (Client.bands.isEmpty()) {
            System.out.println("Collection is empty");
            return;
        }
        int id = Client.bands.remove(Client.bands.size() - 1).getId();
        for (MusicBand b: Client.bands) {
            if (b.getId() > id) b.setId(b.getId() - 1);
        }
        MusicBand.setCount(MusicBand.getCount() - 1);
        System.out.println("Last band removed successfully");
    }

    @Override
    public String getCommandName() {
        return "remove_last";
    }

}
