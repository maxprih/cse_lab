package service;

import controller.Client;
import pojo.Album;
import pojo.Coordinates;
import pojo.MusicBand;
import pojo.MusicGenre;
import util.FieldsInputScanner;
import util.impl.FieldsInputScannerImpl;

/**
 * Adding a new object to collection
 */
public class Add extends Command {

    private final FieldsInputScanner scanner = new FieldsInputScannerImpl();

    @Override
    public void execute(String[] args) {
        String name = scanner.scanName();
        Coordinates coordinates = scanner.scanCoordinates();
        Long nop = scanner.scanNumberOfParticipants();
        MusicGenre genre = scanner.scanGenre();
        Album album = scanner.scanAlbum();
        MusicBand band = new MusicBand(name, coordinates, nop, genre, album);
        band.setId();
        Client.bands.add(band);
        System.out.println("Music band added successfully");
    }

    @Override
    public String getCommandName() {
        return "add";
    }

}
