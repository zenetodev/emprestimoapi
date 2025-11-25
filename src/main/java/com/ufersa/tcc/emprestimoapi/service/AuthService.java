package com.ufersa.tcc.emprestimoapi.service;

import com.ufersa.tcc.emprestimoapi.dto.UsuarioRegistroDTO;
import com.ufersa.tcc.emprestimoapi.dto.auth.JwtResponse;
import com.ufersa.tcc.emprestimoapi.dto.auth.LoginRequest;
import com.ufersa.tcc.emprestimoapi.dto.auth.RegisterRequest;
import com.ufersa.tcc.emprestimoapi.model.User;

public interface AuthService {
    JwtResponse authenticate(LoginRequest loginRequest);
    JwtResponse register(RegisterRequest registerRequest);
}
