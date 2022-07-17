package com.maxpri.common.entities;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Objects;

public class Coordinates implements Serializable, Comparable<Coordinates> {
    public static final double X_MAX_VALUE = 867;
    public static final double Y_MIN_VALUE = -73;

    @Max((long) X_MAX_VALUE)
    private double x; //Максимальное значение поля: 867

    @NotNull
    @Min((long) Y_MIN_VALUE)
    private Double y; //Значение поля должно быть больше -73, Поле не может быть null

    public Coordinates(double x, Double y) {
        if (x <= X_MAX_VALUE) {
            this.x = x;
        } else {
            throw new IllegalArgumentException("Coordinates' x max value is 867.");
        }
        if (y > Y_MIN_VALUE) {
            this.y = y;
        } else {
            throw new IllegalArgumentException("Coordinates' y value must be greater than -73.");
        }
    }

    public double getX() {
        return x;
    }

    public Double getY() {
        return y;
    }

    @Override
    public String toString() {
        return x + "; " + y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinates that = (Coordinates) o;
        return Double.compare(that.x, x) == 0 && y.equals(that.y);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public int compareTo(Coordinates otherCoordinates) {
        return Comparator.comparing(Coordinates::getX).thenComparing(Coordinates::getY).compare(this, otherCoordinates);
    }
}
