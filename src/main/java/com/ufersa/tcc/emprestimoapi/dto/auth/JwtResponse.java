package com.ufersa.tcc.emprestimoapi.dto.auth;

import lombok.Builder;

@Builder
public record JwtResponse(
        String token,
        String type,
        String matricula,
        String email,
        String perfil) {
}