package com.ufersa.tcc.emprestimoapi.dto;

import com.ufersa.tcc.emprestimoapi.model.enums.StatusEmprestimo;

import java.time.LocalDateTime;

public record EmprestimoDTO(
        Long id,
        String usuarioMatricula,
        String usuarioNome,
        String materialCodigo,
        String materialNome,
        LocalDateTime dataEmprestimo,
        LocalDateTime dataDevolucao,
        StatusEmprestimo status,
        String observacoes) {
}
