package com.ufersa.tcc.emprestimoapi.dto;


import com.ufersa.tcc.emprestimoapi.model.enums.PerfilUsuario;
import com.ufersa.tcc.emprestimoapi.validation.EmailUfersa;
import com.ufersa.tcc.emprestimoapi.validation.SenhaMatches;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;


@Builder
@SenhaMatches
public record UsuarioRegistroDTO(
        @NotBlank(message = "O nome não pode estar em branco")
        @Size(max = 100)
        String nome,

        @NotBlank(message = "O CPF não pode estar em branco")
        @Pattern(regexp = "\\d{11}", message = "CPF deve ter 11 dígitos")
        String cpf,

        @NotBlank(message = "O email não pode estar em branco")
        @Email(message = "Email deve ser válido")
        @EmailUfersa
        String email,

        @NotBlank(message = "A senha não pode estar em branco")
        @Size(min = 6, message = "Senha deve ter no mínimo 6 caracteres")
        String senha,

        @NotBlank(message = "Confirmação de senha é obrigatória")
        String confirmaSenha,

        @NotBlank(message = "Matrícula é obrigatória")
        @Pattern(regexp = "\\d{10}", message = "Matrícula deve ter 10 dígitos")
        String matricula,

        PerfilUsuario perfilUsuario
) {}
