package com.ufersa.tcc.emprestimoapi.service;

import com.ufersa.tcc.emprestimoapi.dto.UsuarioDTO;
import com.ufersa.tcc.emprestimoapi.dto.UsuarioRegistroDTO;

import java.util.List;

public interface UserService {
    UsuarioDTO registrarUsuario(UsuarioRegistroDTO dto);
    List<UsuarioDTO> listarTodosUsuarios();
    UsuarioDTO buscarPorMatricula(String matricula);
    UsuarioDTO atualizarUsuario(String matricula, UsuarioRegistroDTO dto);
    void deletarUsuario(String matricula);
}
