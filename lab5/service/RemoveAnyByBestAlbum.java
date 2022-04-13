package service;

import controller.Client;
import pojo.Album;
import pojo.MusicBand;
import util.FieldsInputScanner;
import util.impl.FieldsInputScannerImpl;

/**
 * Removing any object with bestAlbum of requested value from collection
 */
public class RemoveAnyByBestAlbum extends Command {

    private final FieldsInputScanner scanner = new FieldsInputScannerImpl();

    @Override
    public void execute(String[] args) {
        if (Client.bands.isEmpty()) {
            System.out.println("Collection is empty");
            return;
        }
        Album album = scanner.scanAlbum();
        MusicBand deleted = null;
        for (MusicBand b: Client.bands) {
            if (b.getBestAlbum().equals(album)) {
                deleted = b;
                break;
            }
        }
        if (deleted == null) {
            System.out.println("Music band with such album doesn't exist");
            return;
        }
        Client.bands.remove(deleted);
        System.out.println("Music band with such album deleted successfully");
    }

    @Override
    public String getCommandName() {
        return "remove_any_by_best_album";
    }

}
