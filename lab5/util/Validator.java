package util;

import pojo.Coordinates;
import pojo.MusicBand;
import pojo.MusicGenre;

public interface Validator {

    MusicBand validateMusicBand(MusicBand band);
    String validateName(String name);
    Coordinates validateCoordinates(Coordinates coordinates);
    Long validateNumberOfParticipants(Long number);
    MusicGenre validateGenre(MusicGenre genre);
    Long validateTracksOrSales(Long value);
}
