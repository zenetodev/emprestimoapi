package com.ufersa.tcc.emprestimoapi.service.impl;

import com.ufersa.tcc.emprestimoapi.dto.EmprestimoDTO;
import com.ufersa.tcc.emprestimoapi.dto.EmprestimoRequestDTO;
import com.ufersa.tcc.emprestimoapi.mapper.EmprestimoMapper;
import com.ufersa.tcc.emprestimoapi.model.Emprestimo;
import com.ufersa.tcc.emprestimoapi.model.Material;
import com.ufersa.tcc.emprestimoapi.model.User;
import com.ufersa.tcc.emprestimoapi.model.enums.StatusEmprestimo;
import com.ufersa.tcc.emprestimoapi.model.enums.StatusMaterial;
import com.ufersa.tcc.emprestimoapi.repository.EmprestimoRepository;
import com.ufersa.tcc.emprestimoapi.repository.MaterialRepository;
import com.ufersa.tcc.emprestimoapi.repository.UserRepository;
import com.ufersa.tcc.emprestimoapi.service.EmprestimoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmprestimoServiceImpl implements EmprestimoService {

    private final EmprestimoRepository emprestimoRepository;
    private final UserRepository userRepository;
    private final MaterialRepository materialRepository;
    private final EmprestimoMapper emprestimoMapper;

    @Override
    @Transactional
    public EmprestimoDTO solicitarEmprestimo(EmprestimoRequestDTO dto) {
        User usuario = userRepository.findById(dto.usuarioMatricula())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));

        Material material = materialRepository.findByCodigoPatrimonio(dto.materialCodigo())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Material não encontrado"));

        if (material.getStatus() != StatusMaterial.DISPONIVEL) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Material não está disponível para empréstimo");
        }

        boolean emprestimoAtivo = emprestimoRepository.findByUsuarioAndMaterialAndStatus(
                usuario, material, StatusEmprestimo.EM_ANDAMENTO).isPresent();

        if (emprestimoAtivo) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Usuário já possui empréstimo ativo com este material");
        }

        if(material.getQuantidadeDisponivel() < 1) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Não há unidades disponíveis deste material para empréstimo");
        }

        material.setQuantidadeDisponivel(material.getQuantidadeDisponivel() - 1);

        if (material.getQuantidadeDisponivel() == 0) {
            material.setStatus(StatusMaterial.INDISPONIVEL);
        }

        Emprestimo emprestimo = Emprestimo.builder()
                .usuario(usuario)
                .material(material)
                .dataEmprestimo(LocalDateTime.now())
                .dataDevolucao(LocalDateTime.now().plusDays(15)) // 15 dias para devolução
                .status(StatusEmprestimo.EM_ANDAMENTO)
                .observacoes(dto.observacoes())
                .build();

        material.setStatus(StatusMaterial.EMPRESTADO);

        materialRepository.save(material);
        Emprestimo emprestimoSalvo = emprestimoRepository.save(emprestimo);

        return emprestimoMapper.toDTO(emprestimoSalvo);
    }

    @Override
    public List<EmprestimoDTO> listarTodosEmprestimos() {
        return emprestimoRepository.findAll()
                .stream()
                .map(emprestimoMapper::toDTO)
                .toList();
    }

    @Override
    public EmprestimoDTO buscarEmprestimoPorId(Long id) {
        Emprestimo emprestimo = emprestimoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Empréstimo não encontrado"));
        return emprestimoMapper.toDTO(emprestimo);
    }

    @Override
    public List<EmprestimoDTO> listarEmprestimosPorUsuario(String matricula) {
        User usuario = userRepository.findById(matricula)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));

        return emprestimoRepository.findByUsuario(usuario)
                .stream()
                .map(emprestimoMapper::toDTO)
                .toList();
    }

    @Override
    @Transactional
    public EmprestimoDTO registrarDevolucao(Long emprestimoId) {
        Emprestimo emprestimo = emprestimoRepository.findById(emprestimoId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Empréstimo não encontrado"));


        if (emprestimo.getStatus() != StatusEmprestimo.EM_ANDAMENTO) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Empréstimo não está em andamento");
        }

        emprestimo.setDataDevolucao(LocalDateTime.now());
        emprestimo.setStatus(StatusEmprestimo.CONCLUIDO);

        Material material = emprestimo.getMaterial();
        material.setStatus(StatusMaterial.DISPONIVEL);
        material.setQuantidadeDisponivel(material.getQuantidadeDisponivel() + 1);

        if(material.getQuantidadeDisponivel() > 0) {
            material.setStatus(StatusMaterial.DISPONIVEL);
        }

        materialRepository.save(material);
        Emprestimo emprestimoAtualizado = emprestimoRepository.save(emprestimo);

        return emprestimoMapper.toDTO(emprestimoAtualizado);
    }

    @Override
    @Transactional
    public void cancelarEmprestimo(Long emprestimoId) {
        Emprestimo emprestimo = emprestimoRepository.findById(emprestimoId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Empréstimo não encontrado"));

        if (emprestimo.getStatus() != StatusEmprestimo.EM_ANDAMENTO) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Apenas empréstimos em andamento podem ser cancelados");
        }

        Material material = emprestimo.getMaterial();
        material.setStatus(StatusMaterial.DISPONIVEL);

        emprestimo.setStatus(StatusEmprestimo.CANCELADO);

        materialRepository.save(material);
        emprestimoRepository.save(emprestimo);
    }
}