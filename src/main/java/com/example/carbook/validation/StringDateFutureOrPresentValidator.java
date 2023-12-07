package com.example.carbook.validation;

import com.example.carbook.model.annotation.StringDateFutureOrPresent;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class StringDateFutureOrPresentValidator implements ConstraintValidator<StringDateFutureOrPresent, String> {
    @Override
    public void initialize(StringDateFutureOrPresent constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value != null && !value.isBlank())  {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
            LocalDate parse = LocalDate.parse(value,formatter);
            return parse.isAfter(LocalDate.now()) || parse.isEqual(LocalDate.now());
        }
        return false;
    }
}
