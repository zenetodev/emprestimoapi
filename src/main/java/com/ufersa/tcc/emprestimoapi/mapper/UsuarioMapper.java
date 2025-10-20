package com.ufersa.tcc.emprestimoapi.mapper;

import com.ufersa.tcc.emprestimoapi.dto.UsuarioRegistroDTO;
import com.ufersa.tcc.emprestimoapi.model.User;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {
    public User toEntity(UsuarioRegistroDTO dto) {
        if (dto == null) {
            return null;
        }
        return User.builder()
                .matricula(dto.matricula())
                .nome(dto.nome())
                .cpf(dto.cpf())
                .email(dto.email())
                .perfilUsuario(dto.perfilUsuario())
                .build();
    }
}
