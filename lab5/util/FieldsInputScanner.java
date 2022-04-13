package util;

import pojo.Album;
import pojo.Coordinates;
import pojo.MusicGenre;

public interface FieldsInputScanner {

    String scanName();
    Coordinates scanCoordinates();
    Long scanNumberOfParticipants();
    MusicGenre scanGenre();
    Album scanAlbum();

}
