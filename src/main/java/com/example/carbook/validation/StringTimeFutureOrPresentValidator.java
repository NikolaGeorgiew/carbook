package com.example.carbook.validation;

import com.example.carbook.model.annotation.StringDateFutureOrPresent;
import com.example.carbook.model.annotation.StringTimeFutureOrPresent;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class StringTimeFutureOrPresentValidator implements ConstraintValidator<StringTimeFutureOrPresent, String> {
    @Override
    public void initialize(StringTimeFutureOrPresent constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value != null && !value.isBlank())  {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mma");
            LocalTime parse = LocalTime.parse(value.toUpperCase(),formatter);
            return parse.isAfter(LocalTime.now()) || parse.equals(LocalTime.now());
        }
        return false;
    }
}
