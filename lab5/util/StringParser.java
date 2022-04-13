package util;

import pojo.MusicGenre;

public interface StringParser {

    Integer parseInt(String str);
    Long parseLong(String str);
    Float parseFloat(String str);
    MusicGenre parseMusicGenre(String str);

}
