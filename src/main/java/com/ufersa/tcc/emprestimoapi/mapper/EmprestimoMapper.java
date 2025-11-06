package com.ufersa.tcc.emprestimoapi.mapper;

import com.ufersa.tcc.emprestimoapi.dto.EmprestimoDTO;
import com.ufersa.tcc.emprestimoapi.model.Emprestimo;
import org.springframework.stereotype.Component;

@Component
public class EmprestimoMapper {

    public EmprestimoDTO toDTO(Emprestimo emprestimo){
        return new EmprestimoDTO(
                emprestimo.getId(),
                emprestimo.getUsuario().getMatricula(),
                emprestimo.getUsuario().getNome(),
                emprestimo.getMaterial().getCodigoPatrimonio(),
                emprestimo.getMaterial().getNomeMaterial(),
                emprestimo.getDataEmprestimo(),
                emprestimo.getDataDevolucao(),
                emprestimo.getStatus(),
                emprestimo.getObservacoes()
        );
    }
}
