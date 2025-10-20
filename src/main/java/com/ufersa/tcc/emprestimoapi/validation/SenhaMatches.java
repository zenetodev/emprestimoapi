package com.ufersa.tcc.emprestimoapi.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = SenhaMatchesValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface SenhaMatches {
    String message() default "Senha e confirmação devem ser iguais";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
