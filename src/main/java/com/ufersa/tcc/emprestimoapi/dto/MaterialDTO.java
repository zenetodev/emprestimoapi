package com.ufersa.tcc.emprestimoapi.dto;

import com.ufersa.tcc.emprestimoapi.model.enums.CategoriaMaterial;
import com.ufersa.tcc.emprestimoapi.model.enums.StatusMaterial;

public record MaterialDTO(
        String codigoPatrimonio,
        String nomeMaterial,
        String descricao,
        CategoriaMaterial categoria,
        Integer quantidadeTotal,
        StatusMaterial status) {
}
