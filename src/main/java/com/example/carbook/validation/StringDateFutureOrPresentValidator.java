package com.example.carbook.validation;

import com.example.carbook.model.annotation.StringDateFutureOrPresent;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.DateTimeException;
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
            DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("MM/d/yyyy");
            DateTimeFormatter formatter3 = DateTimeFormatter.ofPattern("M/d/yyyy");
            try {
                LocalDate parse = LocalDate.parse(value, formatter);
                return parse.isAfter(LocalDate.now());
            } catch (DateTimeException e) {
                try {
                    LocalDate parse = LocalDate.parse(value, formatter2);
                    return parse.isAfter(LocalDate.now());
                } catch (DateTimeException e1) {
                    try {
                        LocalDate parse = LocalDate.parse(value,formatter3);
                        return parse.isAfter(LocalDate.now());
                    } catch (DateTimeException e2) {
                        return false;
                    }
                }
            }

//            LocalDate parse = LocalDate.parse(value,formatter);
//            return parse.isAfter(LocalDate.now());
        }
        return false;
    }
}
