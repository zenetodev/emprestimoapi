package com.ufersa.tcc.emprestimoapi.service;

import com.ufersa.tcc.emprestimoapi.dto.EmprestimoDTO;
import com.ufersa.tcc.emprestimoapi.dto.EmprestimoRequestDTO;

import java.util.List;

public interface EmprestimoService {
    EmprestimoDTO solicitarEmprestimo(EmprestimoRequestDTO dto);
    List<EmprestimoDTO> listarTodosEmprestimos();
    EmprestimoDTO buscarEmprestimoPorId(Long id);
    List<EmprestimoDTO> listarEmprestimosPorUsuario(String matricula);
    EmprestimoDTO registrarDevolucao(Long emprestimoId);
    void cancelarEmprestimo(Long emprestimoId);
}
