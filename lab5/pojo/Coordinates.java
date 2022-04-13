package pojo;

import java.util.Objects;

public class Coordinates {

    private float x; //Максимальное значение поля: 550
    private Long y; //Максимальное значение поля: 635, Поле не может быть null

    public Coordinates() {}

    public Coordinates(float x, Long y) {
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public Long getY() {
        return y;
    }

    public void setY(Long y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Coordinates)) return false;
        Coordinates that = (Coordinates) o;
        return Float.compare(that.getX(), getX()) == 0 && getY().equals(that.getY());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getX(), getY());
    }

    @Override
    public String toString() {
        return "Coordinates[" +
                "x = " + x +
                ", y = " + y +
                ']';
    }

}
