package service;

import controller.Client;
import pojo.MusicBand;
import util.StringParser;
import util.impl.StringParserImpl;

/**
 * Removing object with id of requested value from collection
 */
public class RemoveById extends Command {

    private final StringParser parser = new StringParserImpl();

    @Override
    public void execute(String[] args) throws IllegalArgumentException {
        if (args.length != 1 ) {
            throw new IllegalArgumentException("One argument must be for remove_by_id");
        }
        Integer id = parser.parseInt(args[0]);
        if (id == null) return;
        MusicBand deleted = null;
        for (MusicBand b: Client.bands) {
            if (b.getId() == id) deleted = b;
        }
        if (deleted == null) {
            System.out.println("Music band with id = " + id + " doesn't exist");
            return;
        }
        Client.bands.remove(deleted);
        for (MusicBand b: Client.bands) {
            if (b.getId() > id) b.setId(b.getId() - 1);
        }
        MusicBand.setCount(MusicBand.getCount() - 1);
        System.out.println("Music band with id = " + id + " deleted successfully");
    }

    @Override
    public String getCommandName() {
        return "remove_by_id";
    }
}
