package com.ufersa.tcc.emprestimoapi.service;

import com.ufersa.tcc.emprestimoapi.dto.auth.JwtResponse;
import com.ufersa.tcc.emprestimoapi.dto.auth.LoginRequest;

public interface AuthService {
    JwtResponse authenticate(LoginRequest loginRequest);
}
