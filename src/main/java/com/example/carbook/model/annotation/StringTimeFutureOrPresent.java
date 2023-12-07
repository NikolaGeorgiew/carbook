package com.example.carbook.model.annotation;

import com.example.carbook.validation.StringDateFutureOrPresentValidator;
import com.example.carbook.validation.StringTimeFutureOrPresentValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(FIELD)
@Retention(RUNTIME)
@Constraint(validatedBy = StringTimeFutureOrPresentValidator.class)
public @interface StringTimeFutureOrPresent {
    String message() default "Time is not in future or present!";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
