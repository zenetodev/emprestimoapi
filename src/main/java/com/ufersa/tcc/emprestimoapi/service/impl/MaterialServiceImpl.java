package com.ufersa.tcc.emprestimoapi.service.impl;

import com.ufersa.tcc.emprestimoapi.dto.MaterialDTO;
import com.ufersa.tcc.emprestimoapi.dto.MaterialRegistroDTO;
import com.ufersa.tcc.emprestimoapi.mapper.MaterialMapper;
import com.ufersa.tcc.emprestimoapi.model.Material;
import com.ufersa.tcc.emprestimoapi.model.enums.StatusMaterial;
import com.ufersa.tcc.emprestimoapi.repository.MaterialRepository;
import com.ufersa.tcc.emprestimoapi.service.MaterialService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MaterialServiceImpl implements MaterialService {

    private final MaterialRepository materialRepository;
    private final MaterialMapper materialMapper;

    @Override
    public MaterialDTO cadastrarMaterial(MaterialRegistroDTO dto) {
        if (materialRepository.existsByCodigoPatrimonio(dto.codigoPatrimonio())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Código de patrimônio já cadastrado");
        }

        Material material = materialMapper.toEntity(dto);
        Material materialSalvo = materialRepository.save(material);
        return materialMapper.toDTO(materialSalvo);
    }

    @Override
    public List<MaterialDTO> listarTodosMateriais() {
        return materialRepository.findAll()
                .stream()
                .map(materialMapper::toDTO)
                .toList();
    }

    @Override
    public MaterialDTO buscarMaterialPorCodigo(String codigoPatrimonio) {
        Material material = materialRepository.findByCodigoPatrimonio(codigoPatrimonio)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Material não encontrado"));
        return materialMapper.toDTO(material);
    }

    @Override
    public MaterialDTO atualizarMaterial(String codigoPatrimonio, MaterialRegistroDTO dto) {
        Material materialExistente = materialRepository.findByCodigoPatrimonio(codigoPatrimonio)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Material não encontrado"));

        materialExistente.setNomeMaterial(dto.nomeMaterial());
        materialExistente.setDescricao(dto.descricao());
        materialExistente.setCategoria(dto.categoria());

        Material materialAtualizado = materialRepository.save(materialExistente);
        return materialMapper.toDTO(materialAtualizado);
    }

    @Override
    public void deletarMaterial(String codigoPatrimonio) {
        if (!materialRepository.existsByCodigoPatrimonio(codigoPatrimonio)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Material não encontrado");
        }
        materialRepository.deleteById(codigoPatrimonio);
    }

    @Override
    public List<MaterialDTO> listarMateriaisDisponiveis() {
        return materialRepository.findByStatus(StatusMaterial.DISPONIVEL)
                .stream()
                .map(materialMapper::toDTO)
                .toList();
    }
}
