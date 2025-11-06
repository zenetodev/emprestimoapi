package com.ufersa.tcc.emprestimoapi.dto;

import com.ufersa.tcc.emprestimoapi.model.enums.CategoriaMaterial;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record MaterialRegistroDTO(
        @NotBlank(message = "Código do patrimônio é obrigatório")
        String codigoPatrimonio,
        @NotBlank(message = "Nome do material é obrigatório")
        String nomeMaterial,
        String descricao,
        @NotNull(message = "Categoria é obrigatória")
        CategoriaMaterial categoria,
        @NotNull(message = "Quantidade total é obrigatória")
        @Min(value = 1, message = "Quantidade total deve ser maior ou igual a 1")
        Integer quantidadeTotal
) {
}
