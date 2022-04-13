package service;

import controller.Client;
import pojo.MusicBand;
import util.FieldsInputScanner;
import util.StringParser;
import util.impl.FieldsInputScannerImpl;
import util.impl.StringParserImpl;

/**
 * Updating object fields values with requested id
 */
public class Update extends Command {

    private final StringParser parser = new StringParserImpl();
    private final FieldsInputScanner scanner = new FieldsInputScannerImpl();

    @Override
    public void execute(String[] args) throws IllegalArgumentException {
        if (args.length != 1 ) {
            throw new IllegalArgumentException("One argument must be for update");
        }
        Integer id = parser.parseInt(args[0]);
        if (id == null) return;
        MusicBand updated = null;
        for (MusicBand b: Client.bands) {
            if (b.getId() == id) updated = b;
        }
        if (updated == null) {
            System.out.println("Music band with id = " + id + " doesn't exist");
            return;
        }
        updated.setName(scanner.scanName());
        updated.setCoordinates(scanner.scanCoordinates());
        updated.setNumberOfParticipants(scanner.scanNumberOfParticipants());
        updated.setGenre(scanner.scanGenre());
        updated.setBestAlbum(scanner.scanAlbum());
        System.out.println("Music band with id = " + id + " updated successfully");
    }

    @Override
    public String getCommandName() {
        return "update";
    }

}
