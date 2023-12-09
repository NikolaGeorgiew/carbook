package com.example.carbook.model.annotation;

import com.example.carbook.validation.StringDateFutureValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(FIELD)
@Retention(RUNTIME)
@Constraint(validatedBy = StringDateFutureValidator.class)
public @interface StringDateFuture {
    String message() default "Date is not in future or present!";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
