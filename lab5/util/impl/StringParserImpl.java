package util.impl;

import pojo.MusicGenre;
import util.StringParser;

/**
 * Class responsible for parsing string values from input to needed type
 */
public class StringParserImpl implements StringParser {

    /**
     * Methods for each of data types
     *
     * @param str from user input
     * @return parsed value of needed type
     */
    @Override
    public Integer parseInt(String str) {
        int arg;
        try {
            arg = Integer.parseInt(str);
        } catch (NumberFormatException e) {
            System.out.println("Argument must be int");
            return null;
        }
        return arg;
    }

    @Override
    public Long parseLong(String str) {
        long arg;
        try {
            arg = Long.parseLong(str);
        } catch (NumberFormatException e) {
            System.out.println("Argument must be Long");
            return null;
        }
        return arg;
    }

    @Override
    public Float parseFloat(String str) {
        float arg;
        try {
            arg = Float.parseFloat(str);
        } catch (NumberFormatException e) {
            System.out.println("Argument must be float");
            return null;
        }
        return arg;
    }

    @Override
    public MusicGenre parseMusicGenre(String str) {
        MusicGenre genre;
        try {
            genre = MusicGenre.valueOf(str);
        } catch (IllegalArgumentException e) {
            System.out.println("Argument must be one of: PSYCHEDELIC_ROCK,\n" +
                    "    POST_ROCK,\n" +
                    "    BRIT_POP");
            return null;
        }
        return genre;
    }
}
