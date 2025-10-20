package com.ufersa.tcc.emprestimoapi.controller;

import com.ufersa.tcc.emprestimoapi.dto.UsuarioDTO;
import com.ufersa.tcc.emprestimoapi.dto.UsuarioRegistroDTO;
import com.ufersa.tcc.emprestimoapi.repository.UserRepository;
import com.ufersa.tcc.emprestimoapi.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UserService userService;
    private final UserRepository userRepository;

    @PostMapping
    public ResponseEntity<UsuarioDTO> registrarUsuario(@Valid @RequestBody UsuarioRegistroDTO dto) {
        UsuarioDTO criado = userService.registrarUsuario(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(criado);
    }

    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> listarTodosUsuarios() {
        List<UsuarioDTO> usuarios = userService.listarTodosUsuarios();
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/{matricula}")
    public ResponseEntity<UsuarioDTO> buscarPorMatricula(@PathVariable String matricula) {
        UsuarioDTO usuario = userService.buscarPorMatricula(matricula);
        return ResponseEntity.ok(usuario);
    }

    @PutMapping("/{matricula}")
    public ResponseEntity<UsuarioDTO> atualizarUsuario(@PathVariable String matricula, @Valid @RequestBody UsuarioRegistroDTO dto) {
        UsuarioDTO usuarioAtualizado = userService.atualizarUsuario(matricula, dto);
        return ResponseEntity.ok(usuarioAtualizado);
    }

    @DeleteMapping("/{matricula}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable String matricula) {
        userService.deletarUsuario(matricula);
        return ResponseEntity.noContent().build();
    }

}
