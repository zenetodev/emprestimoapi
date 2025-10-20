package com.ufersa.tcc.emprestimoapi.validation;

import com.ufersa.tcc.emprestimoapi.dto.UsuarioRegistroDTO;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class SenhaMatchesValidator implements ConstraintValidator<SenhaMatches, Object> {

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context) {
        if (!(obj instanceof UsuarioRegistroDTO)) return true;
        UsuarioRegistroDTO dto = (UsuarioRegistroDTO) obj;
        if (dto.senha() == null || dto.confirmaSenha() == null) return true;
        return dto.senha().equals(dto.confirmaSenha());
    }
}
