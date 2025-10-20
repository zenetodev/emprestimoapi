package com.ufersa.tcc.emprestimoapi.dto;

import com.ufersa.tcc.emprestimoapi.model.PerfilUsuario;
import com.ufersa.tcc.emprestimoapi.model.User;

import java.time.LocalDateTime;


public record UsuarioDTO(String matricula,
                         String nome,
                         String cpf,
                         String email,
                         PerfilUsuario perfilUsuario,
                         LocalDateTime dataCriacao,
                         LocalDateTime dataAtualizacao) {

    public static UsuarioDTO fromEntity(User user) {
        return new UsuarioDTO(
                user.getMatricula(),
                user.getNome(),
                user.getCpf(),
                user.getEmail(),
                user.getPerfilUsuario(),
                user.getDataCriacao(),
                user.getDataAtualizacao()
        );
    }
}
