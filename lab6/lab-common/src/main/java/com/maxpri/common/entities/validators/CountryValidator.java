package com.maxpri.common.entities.validators;

import com.maxpri.common.entities.enums.Country;


public final class CountryValidator {

    private CountryValidator() {

    }

    public static Country getValidatedColor(String colorArg) {
        try {
            return Country.valueOf(colorArg.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid country.");
        }
    }
}
