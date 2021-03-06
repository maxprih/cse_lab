package com.maxpri.common.entities.validators;

import com.maxpri.common.entities.Coordinates;
import com.maxpri.common.entities.Location;
import com.maxpri.common.entities.Person;
import com.maxpri.common.entities.User;
import com.maxpri.common.entities.enums.Color;
import com.maxpri.common.entities.enums.Country;
import com.maxpri.common.exceptions.ValidationException;
import com.maxpri.common.utils.DataNormalizer;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public final class PersonValidator {

    private static final int MAX_NAME_LENGTH = 1000;

    private PersonValidator() {

    }

    public static String getValidatedName(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Name field can not be empty.");
        }
        if (args.length > 1) {
            throw new IllegalArgumentException("Provide one argument, use \"\" for several words.");
        }
        if (args[0].length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException("String is too long.");
        }
        return args[0];
    }

    public static boolean isNameValid(String name) {
        return name != null;
    }

    public static Coordinates getValidatedCoordinates(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Coordinates field can not be empty");
        }
        if (args.length != 2) {
            throw new IllegalArgumentException("Coordinates field must have 2 arguments.");
        }
        Coordinates coordinates = new Coordinates(CoordinatesValidator.getValidatedX(args[0]), CoordinatesValidator.getValidatedY(args[1]));
        if (isCoordinatesValid(coordinates)) {
            return coordinates;
        } else {
            throw new IllegalArgumentException("Invalid coordinates value");
        }
    }

    public static boolean isCoordinatesValid(Coordinates coordinates) {
        return coordinates != null
                && CoordinatesValidator.isXValid(coordinates.getX())
                && CoordinatesValidator.isYValid(coordinates.getY());
    }

    public static long getValidatedHeight(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Height field can not be empty.");
        } else if (args.length != 1) {
            throw new IllegalArgumentException("Height implies 1 number.");
        }
        try {
            long height = Long.parseLong(args[0]);
            if (isHeightValid(height)) {
                return height;
            } else {
                throw new IllegalArgumentException("Invalid height value");
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid input of height.");
        }
    }

    public static boolean isHeightValid(long height) {
        return height > 0;
    }

    public static Color getValidatedColor(String[] args) {
        if (args.length == 0) {
            return null;
        }
        if (args.length != 1) {
            throw new IllegalArgumentException("Color implies 1 value.");
        }
        return ColorValidator.getValidatedColor(args[0].toUpperCase());
    }

    public static Country getValidatedCountry(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Country can not be empty");
        }
        if (args.length != 1) {
            throw new IllegalArgumentException("Country implies 1 value.");
        }
        return CountryValidator.getValidatedColor(args[0]);
    }

    public static boolean isCountryValid(Country country) {
        return country != null;
    }

    public static Location getValidatedLocation(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Location field can not be empty.");
        }
        if (args.length != Location.class.getDeclaredFields().length) {
            throw new IllegalArgumentException("Location implies 3 values.");
        }
        return new Location(LocationValidator.getValidatedX(args[0]),
                LocationValidator.getValidatedY(args[1]),
                LocationValidator.getValidatedZ(args[2]));
    }

    public static boolean isLocationValid(Location location) {
        return location != null;
    }

    public static boolean isPersonValid(Person person) {
        return isNameValid(person.getName())
                && isHeightValid(person.getHeight())
                && isCountryValid(person.getNationality())
                && isCoordinatesValid(person.getCoordinates())
                && isLocationValid(person.getLocation());
    }

    public static Person validatePersonFromString(String name, String xCoordinates, String yCoordinates, String sHeight, Color eyeColor, Color hairColor, Country nationality, String xLocation, String yLocation, String zLocation) throws ValidationException {
        List<String> validationErrorsList = new ArrayList<>();
        Person person = new Person();
        try {
            name = getValidatedName(DataNormalizer.normalize(name));
            person.setName(name);
            validationErrorsList.add(null);
        } catch (IllegalArgumentException e) {
            validationErrorsList.add(e.getMessage());
        }

        double x = 0, y;
        try {
            x = CoordinatesValidator.getValidatedX(xCoordinates);
            validationErrorsList.add(null);
        } catch (IllegalArgumentException e) {
            validationErrorsList.add(e.getMessage());
        }

        try {
            y = CoordinatesValidator.getValidatedY(yCoordinates);
            Coordinates coordinates = new Coordinates(x, y);
            if (isCoordinatesValid(coordinates)) {
                person.setCoordinates(coordinates);
                validationErrorsList.add(null);
            }
        } catch (IllegalArgumentException e) {
            validationErrorsList.add(e.getMessage());
        }

        try {
            long height = getValidatedHeight(DataNormalizer.normalize(sHeight));
            person.setHeight(height);
            validationErrorsList.add(null);
        } catch (IllegalArgumentException e) {
            validationErrorsList.add(e.getMessage());
        }

        person.setEyeColor(eyeColor);
        person.setHairColor(hairColor);

        try {
            if (nationality == null) {
                throw new IllegalArgumentException("You have to choose an country");
            }
            person.setNationality(nationality);
            validationErrorsList.add(null);
        } catch (IllegalArgumentException e) {
            validationErrorsList.add(e.getMessage());
        }

        double xl = 0;
        long yl = 0;
        float zl;
        try {
            xl = LocationValidator.getValidatedX(xLocation);
            validationErrorsList.add(null);
        } catch (IllegalArgumentException e) {
            validationErrorsList.add(e.getMessage());
        }

        try {
            yl = LocationValidator.getValidatedY(yLocation);
            validationErrorsList.add(null);
        } catch (IllegalArgumentException e) {
            validationErrorsList.add(e.getMessage());
        }

        try {
            zl = LocationValidator.getValidatedZ(zLocation);
            validationErrorsList.add(null);
            Location location = new Location(xl, yl, zl);
            person.setLocation(location);
            validationErrorsList.add(null);
        } catch (IllegalArgumentException e) {
            validationErrorsList.add(e.getMessage());
        }

//        try {
//            Location location = getValidatedLocation(new String[] {xLocation, yLocation, zLocation});
//            person.setLocation(location);
//            validationErrorsList.add(null);
//        } catch (IllegalArgumentException e) {
//            validationErrorsList.add(e.getMessage());
//        }

        if (validationErrorsList.stream().anyMatch(Objects::nonNull)) {
            throw new ValidationException(validationErrorsList);
        }

        return person;
    }
}
