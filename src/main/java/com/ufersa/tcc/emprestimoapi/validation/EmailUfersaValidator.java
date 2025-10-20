package com.ufersa.tcc.emprestimoapi.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

@Component
public class EmailUfersaValidator implements ConstraintValidator<EmailUfersa, String> {

    @Override
    public void initialize(EmailUfersa constraintAnnotation) {
        // No initialization needed
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        if (email == null) return false;

        String normalized = email.trim().toLowerCase();
        return normalized.endsWith("@alunos.ufersa.edu.br") || normalized.endsWith("@ufersa.edu.br");
    }
}
