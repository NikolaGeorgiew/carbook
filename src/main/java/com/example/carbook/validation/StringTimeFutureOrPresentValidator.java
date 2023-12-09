package com.example.carbook.validation;

import com.example.carbook.model.annotation.StringDateFutureOrPresent;
import com.example.carbook.model.annotation.StringTimeFutureOrPresent;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.hibernate.validator.constraintvalidation.HibernateConstraintValidatorContext;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class StringTimeFutureOrPresentValidator implements ConstraintValidator<StringTimeFutureOrPresent, String> {
    private String pickUpDateField;
    @Override
    public void initialize(StringTimeFutureOrPresent constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value != null && !value.isBlank()) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mma");
            LocalTime parse = LocalTime.parse(value.toUpperCase(), formatter);
            return true;
        }
        return false;
    }
}
