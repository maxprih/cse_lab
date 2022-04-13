package pojo;

import java.util.Objects;

public class Album {

    private String name; //Поле не может быть null, Строка не может быть пустой
    private Long tracks; //Поле не может быть null, Значение поля должно быть больше 0
    private Long sales; //Поле может быть null, Значение поля должно быть больше 0

    public Album() {}

    public Album(String name, Long tracks, Long sales) {
        this.name = name;
        this.tracks = tracks;
        this.sales = sales;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getTracks() {
        return tracks;
    }

    public void setTracks(Long tracks) {
        this.tracks = tracks;
    }

    public Long getSales() {
        return sales;
    }

    public void setSales(Long sales) {
        this.sales = sales;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Album)) return false;
        Album album = (Album) o;
        return getName().equals(album.getName())
                && getTracks().equals(album.getTracks())
                && getSales().equals(album.getSales());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getTracks(), getSales());
    }

    @Override
    public String toString() {
        return "Album[" +
                "name = '" + name + '\'' +
                ", tracks = " + tracks +
                ", sales = " + sales +
                ']';
    }
}
