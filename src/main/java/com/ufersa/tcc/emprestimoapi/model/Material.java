package com.ufersa.tcc.emprestimoapi.model;

import com.ufersa.tcc.emprestimoapi.model.enums.CategoriaMaterial;
import com.ufersa.tcc.emprestimoapi.model.enums.StatusMaterial;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "materiais")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Material {

    @Id
    @NotBlank(message = "Código do material é obrigatório")
    @Column(name = "codigo_patrimonio", length = 50, nullable = false, unique = true)
    private String codigoPatrimonio;

    @NotBlank(message = "Nome do material é obrigatório")
    @Column(length = 100, nullable = false)
    private String nomeMaterial;

    @Column(name = "descricao")
    private String descricao;

    @NotNull(message = "Categoria do material é obrigatória")
    @Enumerated(EnumType.STRING)
    @Column(name = "categoria", length = 30, nullable = false)
    private CategoriaMaterial categoria;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 20, nullable = false)
    private StatusMaterial status;

    @NotNull
    @Column(name = "quantidade_total", nullable = false)
    private Integer quantidadeTotal = 1;

    @NotNull
    @Column(name = "quantidade_disponivel", nullable = false)
    private Integer quantidadeDisponivel = 1;
}
