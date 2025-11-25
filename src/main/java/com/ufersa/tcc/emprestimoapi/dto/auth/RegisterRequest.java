package com.ufersa.tcc.emprestimoapi.dto.auth;

import com.ufersa.tcc.emprestimoapi.validation.EmailUfersa;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record RegisterRequest(
        @NotBlank(message = "Nome é obrigatório")
        String nome,

        @NotBlank(message = "Matrícula é obrigatória")
        String matricula,

        @NotBlank(message = "Email é obrigatório")
        @EmailUfersa(message = "Email deve ser um email institucional válido da UFERSA")
        String email,

        @NotBlank(message = "CPF é obrigatório")
        @Size(min = 11, max = 11, message = "CPF deve ter 11 caracteres")
        String cpf,

        @NotBlank(message = "Senha é obrigatória")
        @Size(min = 6, message = "Senha deve ter no mínimo 6 caracteres")
        String senha
) {}