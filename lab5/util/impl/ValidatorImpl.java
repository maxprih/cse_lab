package util.impl;

import pojo.Coordinates;
import pojo.MusicBand;
import pojo.MusicGenre;
import util.Validator;

/**
 * Class responsible for validating values of fields (depending on their limits of absence)
 */
public class ValidatorImpl implements Validator {

    /**
     * Method for validity check of the haul object
     *
     * @param band for check
     * @return valid object or null
     */
    @Override
    public MusicBand validateMusicBand(MusicBand band) {
        if (validateName(band.getName()).equals("") || validateCoordinates(band.getCoordinates()) == null
                || validateNumberOfParticipants(band.getNumberOfParticipants()) == null
                || validateGenre(band.getGenre()) == null || validateName(band.getBestAlbum().getName()).equals("")
                || validateTracksOrSales(band.getBestAlbum().getTracks()) == null
                || validateTracksOrSales(band.getBestAlbum().getSales()) == null) return null;
        return band;
    }

    /**
     * Methods for validation of each field of object
     *
     * @param name field for validation
     * @return valid value or null
     */
    @Override
    public String validateName(String name) {
        if (name.equals("")) {
            System.out.println("Name cannot be empty");
            return "";
        }
        return name;
    }

    @Override
    public Coordinates validateCoordinates(Coordinates coordinates) {
        if (coordinates.getY() == null) {
            System.out.println("Coordinate Y cannot be null");
            return null;
        }
        if (coordinates.getX() > 550 || coordinates.getY() > 635) {
            System.out.println("Limits: x < 551, y < 636");
            return null;
        }
        return coordinates;
    }

    @Override
    public Long validateNumberOfParticipants(Long number) {
        if (number == null) {
            System.out.println("Number of participants cannot be null");
            return null;
        }
        if (number <= 0) {
            System.out.println("Number of participants must be positive");
            return null;
        }
        return number;
    }

    @Override
    public MusicGenre validateGenre(MusicGenre genre) {
        if (genre == null) {
            System.out.println("Music genre cannot be null");
            return null;
        }
        return genre;
    }

    @Override
    public Long validateTracksOrSales(Long value) {
        if (value == null) {
            System.out.println("Tracks/sales cannot be null");
            return null;
        }
        if (value <= 0) {
            System.out.println("Tracks/sales must be positive");
            return null;
        }
        return value;
    }

}
