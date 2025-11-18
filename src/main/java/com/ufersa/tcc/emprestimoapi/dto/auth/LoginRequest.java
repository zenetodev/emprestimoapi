package com.ufersa.tcc.emprestimoapi.dto.auth;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record LoginRequest(
        @NotBlank(message = "Matrícula é obrigatória")
        String matricula,

        @NotBlank(message = "Senha é obrigatória")
        String senha
) {}