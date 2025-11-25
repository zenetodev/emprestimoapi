package com.ufersa.tcc.emprestimoapi.service.impl;

import com.ufersa.tcc.emprestimoapi.dto.UsuarioRegistroDTO;
import com.ufersa.tcc.emprestimoapi.dto.auth.JwtResponse;
import com.ufersa.tcc.emprestimoapi.dto.auth.LoginRequest;
import com.ufersa.tcc.emprestimoapi.dto.auth.RegisterRequest;
import com.ufersa.tcc.emprestimoapi.exception.AuthenticationFailedException;
import com.ufersa.tcc.emprestimoapi.exception.InvalidPrincipalException;
import com.ufersa.tcc.emprestimoapi.model.User;
import com.ufersa.tcc.emprestimoapi.model.enums.PerfilUsuario;
import com.ufersa.tcc.emprestimoapi.repository.UserRepository;
import com.ufersa.tcc.emprestimoapi.security.JwtUtil;
import com.ufersa.tcc.emprestimoapi.security.UserPrincipal;
import com.ufersa.tcc.emprestimoapi.service.AuthService;
import com.ufersa.tcc.emprestimoapi.service.UserQueryService;
import com.ufersa.tcc.emprestimoapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final UserQueryService userQueryService;

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

    @Override
    public JwtResponse register(RegisterRequest registerRequest) {
        if (userQueryService.existsByMatricula(registerRequest.matricula())) {
            throw new RuntimeException("Já existe um usuário com esta matrícula");
        }
        if (userQueryService.existsByEmail(registerRequest.email())) {
            throw new RuntimeException("Já existe um usuário com este email");
        }
        if (userQueryService.existsByCpf(registerRequest.cpf())) {
            throw new RuntimeException("Já existe um usuário com este CPF");
        }

        User user = User.builder()
                .matricula(registerRequest.matricula())
                .nome(registerRequest.nome())
                .cpf(registerRequest.cpf())
                .email(registerRequest.email())
                .senha(passwordEncoder.encode(registerRequest.senha()))
                .perfilUsuario(PerfilUsuario.ROLE_ALUNO)
                .dataCriacao(LocalDateTime.now())
                .build();

        User savedUser = userRepository.save(user);

        UserPrincipal userPrincipal = new UserPrincipal(savedUser);
        String token = jwtUtil.generateJwtTokenFromUserPrincipal(userPrincipal);

        return JwtResponse.builder()
                .token(token)
                .type("Bearer ")
                .email(savedUser.getEmail())
                .nome(savedUser.getNome())
                .matricula(savedUser.getMatricula())
                .perfil(savedUser.getPerfilUsuario().name())
                .build();
    }
}
