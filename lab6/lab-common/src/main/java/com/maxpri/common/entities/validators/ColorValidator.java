package com.maxpri.common.entities.validators;

import com.maxpri.common.entities.enums.Color;


public final class ColorValidator {

    private ColorValidator() {

    }

    public static Color getValidatedColor(String colorArg) {
        try {
            return Color.valueOf(colorArg);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid color.");
        }
    }
}
