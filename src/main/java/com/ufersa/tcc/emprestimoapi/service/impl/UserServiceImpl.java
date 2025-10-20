package com.ufersa.tcc.emprestimoapi.service.impl;

import com.ufersa.tcc.emprestimoapi.dto.UsuarioDTO;
import com.ufersa.tcc.emprestimoapi.dto.UsuarioRegistroDTO;
import com.ufersa.tcc.emprestimoapi.mapper.UsuarioMapper;
import com.ufersa.tcc.emprestimoapi.model.User;
import com.ufersa.tcc.emprestimoapi.repository.UserRepository;
import com.ufersa.tcc.emprestimoapi.service.UserService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UsuarioMapper mapper;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, UsuarioMapper mapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.mapper = mapper;
    }

    @Override
    @Transactional
    public UsuarioDTO registrarUsuario(UsuarioRegistroDTO dto) {

        if (userRepository.existsByMatricula(dto.matricula())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Matrícula já cadastrada");
        }

        if (userRepository.existsByCpf(dto.cpf())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "CPF já cadastrado");
        }

        if (userRepository.existsByEmail(dto.email())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email já cadastrado");
        }

        User user = mapper.toEntity(dto);
        user.setSenha(passwordEncoder.encode(dto.senha()));

        LocalDateTime agora = LocalDateTime.now();
        user.setDataCriacao(agora);

        try {
            User saved = userRepository.save(user);
            return UsuarioDTO.fromEntity(saved);
        } catch (DataIntegrityViolationException ex) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Dados duplicados");
        }
    }

    @Override
    public List<UsuarioDTO> listarTodosUsuarios() {
        List<User> usuarios = userRepository.findAll();
        return usuarios.stream()
                .map(UsuarioDTO::fromEntity)
                .toList();
    }

    @Override
    public UsuarioDTO buscarPorMatricula(String matricula) {
        User usuario = userRepository.findById(matricula)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));
        return UsuarioDTO.fromEntity(usuario);
    }

    @Override
    @Transactional
    public UsuarioDTO atualizarUsuario(String matricula, UsuarioRegistroDTO dto) {
        User usuarioExistente = userRepository.findById(matricula)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));

        if (!usuarioExistente.getEmail().equals(dto.email()) &&
            userRepository.existsByEmail(dto.email())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email já cadastrado em outro usuário!");
        }

        if (!usuarioExistente.getCpf().equals(dto.cpf()) &&
            userRepository.existsByCpf(dto.cpf())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "CPF já cadastrado em outro usuário!");
        }

        usuarioExistente.setNome(dto.nome());
        usuarioExistente.setCpf(dto.cpf());
        usuarioExistente.setEmail(dto.email());
        usuarioExistente.setPerfilUsuario(dto.perfilUsuario());

        if (dto.senha() != null && !dto.senha().isBlank()) {
            usuarioExistente.setSenha(passwordEncoder.encode(dto.senha()));
        }

        usuarioExistente.setDataAtualizacao(LocalDateTime.now());

        User usuarioAtualizado = userRepository.save(usuarioExistente);
        return UsuarioDTO.fromEntity(usuarioAtualizado);
    }

    @Override
    @Transactional
    public void deletarUsuario(String matricula) {
        if(!userRepository.existsById(matricula)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado");
        }
        userRepository.deleteById(matricula);
    }
}
