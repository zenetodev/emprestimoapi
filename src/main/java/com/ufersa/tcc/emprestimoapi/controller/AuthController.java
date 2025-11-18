package com.ufersa.tcc.emprestimoapi.controller;

import com.ufersa.tcc.emprestimoapi.dto.auth.JwtResponse;
import com.ufersa.tcc.emprestimoapi.dto.auth.LoginRequest;
import com.ufersa.tcc.emprestimoapi.security.JwtUtil;
import com.ufersa.tcc.emprestimoapi.security.UserPrincipal;
import com.ufersa.tcc.emprestimoapi.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        JwtResponse response = authService.authenticate(loginRequest);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/validate")
    public ResponseEntity<String> validateToken() {
        return ResponseEntity.ok("Token válido");
    }
}
