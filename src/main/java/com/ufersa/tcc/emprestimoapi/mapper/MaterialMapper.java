package com.ufersa.tcc.emprestimoapi.mapper;

import com.ufersa.tcc.emprestimoapi.dto.MaterialDTO;
import com.ufersa.tcc.emprestimoapi.dto.MaterialRegistroDTO;
import com.ufersa.tcc.emprestimoapi.model.Material;
import com.ufersa.tcc.emprestimoapi.model.enums.StatusMaterial;
import org.springframework.stereotype.Component;

@Component
public class MaterialMapper {

    public Material toEntity(MaterialRegistroDTO dto) {
        return Material.builder()
                .codigoPatrimonio(dto.codigoPatrimonio())
                .nomeMaterial(dto.nomeMaterial())
                .descricao(dto.descricao())
                .categoria(dto.categoria())
                .quantidadeTotal(dto.quantidadeTotal())
                .quantidadeDisponivel(dto.quantidadeTotal())
                .status(StatusMaterial.DISPONIVEL)
                .build();
    }

    public MaterialDTO toDTO(Material material) {
        return new MaterialDTO(
                material.getCodigoPatrimonio(),
                material.getNomeMaterial(),
                material.getDescricao(),
                material.getCategoria(),
                material.getQuantidadeTotal(),
                material.getStatus()
        );
    }
}
