package service;

import controller.Client;
import pojo.MusicBand;
import pojo.MusicGenre;
import util.StringParser;
import util.impl.StringParserImpl;

/**
 * Counting objects with genre of requested value
 */
public class CountByGenre extends Command {

    private final StringParser parser = new StringParserImpl();

    @Override
    public void execute(String[] args) throws IllegalArgumentException {
        if (args.length != 1 ) {
            throw new IllegalArgumentException("One argument must be for count_by_genre");
        }
        MusicGenre genre = parser.parseMusicGenre(args[0]);
        if (genre == null) return;
        int count = 0;
        for (MusicBand b: Client.bands) {
            if (b.getGenre().equals(genre)) count++;
        }
        System.out.println("Number of bands with genre " + genre + " is " + count);
    }

    @Override
    public String getCommandName() {
        return "count_by_genre";
    }

}
