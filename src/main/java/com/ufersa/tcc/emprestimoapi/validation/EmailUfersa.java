package com.ufersa.tcc.emprestimoapi.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = EmailUfersaValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface EmailUfersa {
    String message() default "Email deve ser um email institucional da UFERSA";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
