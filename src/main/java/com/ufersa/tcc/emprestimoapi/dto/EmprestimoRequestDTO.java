package com.ufersa.tcc.emprestimoapi.dto;

import jakarta.validation.constraints.NotBlank;

public record EmprestimoRequestDTO(
        @NotBlank(message = "Matrícula do usuário é obrigatória")
        String usuarioMatricula,

        @NotBlank(message = "Código do material é obrigatório")
        String materialCodigo,

        String observacoes
) {
}
