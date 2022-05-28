package com.maxpri.common.entities;

import java.io.Serializable;

public class Location implements Serializable {
    private double x;
    private long y;
    private float z;

    public Location(double x, long y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public String toString() {
        return "[x: " + x + ", y: " + y + ", z: " + z + "]";
    }
}
