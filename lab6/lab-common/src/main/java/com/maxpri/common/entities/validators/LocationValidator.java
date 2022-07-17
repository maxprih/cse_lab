package com.maxpri.common.entities.validators;


public final class LocationValidator {

    private LocationValidator() {

    }

    public static double getValidatedX(String xArg) {
        try {
            double x = Double.parseDouble(xArg);
            if (Double.isInfinite(x)) {
                throw new IllegalArgumentException("Incorrect value for x argument");
            }
            return x;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid location's argument value.");
        }
    }

    public static long getValidatedY(String yArg) {
        try {
            return Long.parseLong(yArg);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid location's argument value.");
        }
    }

    public static float getValidatedZ(String zArg) {
        try {
            float z = Float.parseFloat(zArg);
            if (Float.isInfinite(z)) {
                throw new IllegalArgumentException("Incorrect value for z argument");
            }
            return z;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid location's argument value.");
        }
    }
}
