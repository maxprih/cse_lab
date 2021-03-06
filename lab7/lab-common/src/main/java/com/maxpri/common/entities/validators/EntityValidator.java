package com.maxpri.common.entities.validators;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import java.util.Set;

public final class EntityValidator {

    private static final ValidatorFactory FACTORY;
    private static final Validator VALIDATOR;

    private EntityValidator() {

    }

    static {
        FACTORY = Validation.buildDefaultValidatorFactory();
        VALIDATOR = FACTORY.getValidator();
    }

    public static <T> Set<ConstraintViolation<T>> validateEntity(T entity) {
        return VALIDATOR.validate(entity);
    }

    public static <T> boolean isEntityValid(T entity) {
        Set<ConstraintViolation<T>> constraintViolations = validateEntity(entity);
        return constraintViolations.isEmpty();
    }

    public static <T> Set<ConstraintViolation<T>> validateField(T entity, String fieldName) {
        return VALIDATOR.validateProperty(entity, fieldName);
    }

    public static <T> boolean isFieldValid(T entity, String fieldName) {
        Set<ConstraintViolation<T>> constraintViolations = validateField(entity, fieldName);
        return constraintViolations.isEmpty();
    }

}
