package com.ufersa.tcc.emprestimoapi.service;

import com.ufersa.tcc.emprestimoapi.dto.MaterialDTO;
import com.ufersa.tcc.emprestimoapi.dto.MaterialRegistroDTO;

import java.util.List;

public interface MaterialService {
    MaterialDTO cadastrarMaterial(MaterialRegistroDTO dto);
    List<MaterialDTO> listarTodosMateriais();
    MaterialDTO buscarMaterialPorCodigo(String codigoPatrimonio);
    MaterialDTO atualizarMaterial(String codigoPatrimonio, MaterialRegistroDTO dto);
    void deletarMaterial(String codigoPatrimonio);
    List<MaterialDTO> listarMateriaisDisponiveis();
}
