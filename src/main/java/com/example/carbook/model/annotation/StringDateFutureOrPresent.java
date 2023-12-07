package com.example.carbook.model.annotation;

import com.example.carbook.validation.StringDateFutureOrPresentValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(FIELD)
@Retention(RUNTIME)
@Constraint(validatedBy = StringDateFutureOrPresentValidator.class)
public @interface StringDateFutureOrPresent {
    String message() default "Date is not in future or present!";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
