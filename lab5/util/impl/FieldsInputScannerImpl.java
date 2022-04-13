package util.impl;

import controller.Client;
import pojo.Album;
import pojo.Coordinates;
import pojo.MusicGenre;
import util.FieldsInputScanner;
import util.StringParser;
import util.Validator;

/**
 * Class responsible for scanning fields of object from user input
 */
public class FieldsInputScannerImpl implements FieldsInputScanner {

    private final StringParser stringParser = new StringParserImpl();
    private final Validator validator = new ValidatorImpl();

    /**
     * Methods for each field of object
     *
     * @return relevant field value or null
     */
    @Override
    public String scanName() {
        String name;
        do {
            System.out.println("Enter name of music band:");
            name = Client.scanner.nextLine();
            name = validator.validateName(name);
        } while (name.equals(""));
        return name;
    }

    @Override
    public Coordinates scanCoordinates() {
        Coordinates coord = null;
        do {
            System.out.println("Enter coordinates x and y divided by space (X - float, Y - long):");
            String coordinates = Client.scanner.nextLine();
            String [] xy = coordinates.trim().split(" ");
            if (xy.length != 2) continue; //123123
            try {
                Float x = stringParser.parseFloat(xy[0]);
                Long y = stringParser.parseLong(xy[1]);
                if (x==null) { continue; }
                coord = validator.validateCoordinates(new Coordinates(x, y));
            }
            catch (NumberFormatException e) {
                continue;
            }

        } while (coord == null);
        return coord;
    }

    @Override
    public Long scanNumberOfParticipants() {
        Long nop;
        do {
            System.out.println("Enter number of participants (Long):");
            String str = Client.scanner.nextLine();
            nop = stringParser.parseLong(str);
            nop = validator.validateNumberOfParticipants(nop);
        } while (nop == null);
        return nop;
    }

    @Override
    public MusicGenre scanGenre() {
        MusicGenre genre;
        do {
            System.out.println("Choose one of music genres: PSYCHEDELIC_ROCK\n" +
                    "POST_ROCK,\n" +
                    "BRIT_POP");
            String str = Client.scanner.nextLine();
            genre = stringParser.parseMusicGenre(str);
        } while (genre == null);
        return genre;
    }

    @Override
    public Album scanAlbum() {
        String albName;
        do {
            System.out.println("Enter name of best album:");
            albName = Client.scanner.nextLine();
            albName = validator.validateName(albName);
        } while (albName.equals(""));
        Long tracks;
        do {
            System.out.println("Enter number of tracks in album (Long):");
            String str = Client.scanner.nextLine();
            tracks = stringParser.parseLong(str);
            tracks = validator.validateTracksOrSales(tracks);
        } while (tracks == null);
        Long sales;
        do {
            System.out.println("Enter number of album sales (Long):");
            String str = Client.scanner.nextLine();
            sales = stringParser.parseLong(str);
            sales = validator.validateTracksOrSales(sales);
        } while (sales == null);
        return new Album(albName, tracks, sales);
    }

}
