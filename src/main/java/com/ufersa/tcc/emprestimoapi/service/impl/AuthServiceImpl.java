package com.ufersa.tcc.emprestimoapi.service.impl;

import com.ufersa.tcc.emprestimoapi.dto.auth.JwtResponse;
import com.ufersa.tcc.emprestimoapi.dto.auth.LoginRequest;
import com.ufersa.tcc.emprestimoapi.exception.AuthenticationFailedException;
import com.ufersa.tcc.emprestimoapi.exception.InvalidPrincipalException;
import com.ufersa.tcc.emprestimoapi.security.JwtUtil;
import com.ufersa.tcc.emprestimoapi.security.UserPrincipal;
import com.ufersa.tcc.emprestimoapi.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @Override
    public JwtResponse authenticate(LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.matricula(), loginRequest.senha())
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtUtil.generateJwtToken(authentication);

            Object principal = authentication.getPrincipal();
            if (!(principal instanceof UserPrincipal)) {
                throw new InvalidPrincipalException();
            }
            UserPrincipal userPrincipal = (UserPrincipal) principal;

            return JwtResponse.builder()
                    .token(jwt)
                    .type("Bearer ")
                    .matricula(userPrincipal.getUsername())
                    .email(userPrincipal.getEmail())
                    .perfil(userPrincipal.getPerfil())
                    .build();

        } catch (BadCredentialsException ex) {
            throw new AuthenticationFailedException("Matrícula ou senha inválida");
        }
    }
}
