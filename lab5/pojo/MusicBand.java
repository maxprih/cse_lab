package pojo;

import java.util.Date;
import java.util.Objects;

public class MusicBand implements Comparable<MusicBand> {

    private int id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private final Date creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Long numberOfParticipants; //Поле может быть null, Значение поля должно быть больше 0
    private MusicGenre genre; //Поле не может быть null
    private Album bestAlbum; //Поле не может быть null

    private static int count = 0;

    public MusicBand() {
        creationDate = new Date();
    }

    public MusicBand(String name, Coordinates coordinates, Long numberOfParticipants,
                     MusicGenre genre, Album bestAlbum) {
        this.name = name;
        this.coordinates = coordinates;
        creationDate = new Date();
        this.numberOfParticipants = numberOfParticipants;
        this.genre = genre;
        this.bestAlbum = bestAlbum;
    }

    public void setId() {
        count++;
        id = count;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public void setNumberOfParticipants(Long numberOfParticipants) {
        this.numberOfParticipants = numberOfParticipants;
    }

    public void setGenre(MusicGenre genre) {
        this.genre = genre;
    }

    public void setBestAlbum(Album bestAlbum) {
        this.bestAlbum = bestAlbum;
    }

    public static void setCount(int count) {
        MusicBand.count = count;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public Long getNumberOfParticipants() {
        return numberOfParticipants;
    }

    public MusicGenre getGenre() {
        return genre;
    }

    public Album getBestAlbum() {
        return bestAlbum;
    }

    public static int getCount() {
        return count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MusicBand)) return false;
        MusicBand musicBand = (MusicBand) o;
        return getId() == musicBand.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "MusicBand{" +
                "id=" + id +
                ", name = '" + name + '\'' +
                ", coordinates = " + coordinates +
                ", creationDate = " + creationDate +
                ", numberOfParticipants = " + numberOfParticipants +
                ", genre = " + genre +
                ", bestAlbum = " + bestAlbum +
                '}';
    }

    @Override
    public int compareTo(MusicBand o) {
        return this.bestAlbum.getSales().compareTo(o.bestAlbum.getSales());
    }
}
