package com.ufersa.tcc.emprestimoapi.model;

import com.ufersa.tcc.emprestimoapi.model.enums.StatusEmprestimo;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "emprestimos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Emprestimo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Usuário é obrigatório")
    @ManyToOne
    @JoinColumn(name = "usuario_matricula", nullable = false)
    private User usuario;

    @NotNull(message = "Material é obrigatório")
    @ManyToOne
    @JoinColumn(name = "material_codigo", nullable = false)
    private Material material;

    @NotNull(message = "Data de empréstimo é obrigatória")
    @Column(name = "data_emprestimo", nullable = false)
    private LocalDateTime dataEmprestimo;

    @NotNull(message = "Data de devolução é obrigatória")
    @Column(name = "data_devolucao", nullable = false)
    private LocalDateTime dataDevolucao;

    @NotNull(message = "Status do empréstimo é obrigatório")
    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 20, nullable = false)
    private StatusEmprestimo status;

    @Column(length = 500)
    private String observacoes;
}
