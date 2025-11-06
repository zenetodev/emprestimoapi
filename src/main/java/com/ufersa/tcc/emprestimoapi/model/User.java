package com.ufersa.tcc.emprestimoapi.model;

import com.ufersa.tcc.emprestimoapi.model.enums.PerfilUsuario;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;


@Entity
@Table(name = "usuarios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = "senha")
@EntityListeners(AuditingEntityListener.class)
public class User {

    @Id
    @NotBlank(message = "Matrícula é obrigatória")
    @Column(name = "id", length = 10, nullable = false, unique = true)
    private String matricula;

    @Column(name = "nome", length = 100, nullable = false)
    private String nome;

    @NotBlank(message = "CPF é obrigatório")
    @Column(name = "cpf", nullable = false, unique = true)
    @Size(min = 11, max = 11)
    private String cpf;

    @NotBlank(message = "Email é obrigatório")
    @Email(message = "Email deve ser válido")
    @Column(name = "email", length = 100, nullable = false, unique = true)
    private String email;

    @NotBlank(message = "Senha é obrigatória")
    @Size(min = 6, message = "Senha deve ter no mínimo 6 caracteres")
    @Column(name = "senha", length = 255, nullable = false)
    private String senha;

    @NotNull(message = "Perfil do usuário é obrigatório")
    @Enumerated(EnumType.STRING)
    @Column(name = "perfil_usuario", length = 20, nullable = false)
    private PerfilUsuario perfilUsuario;

    @Column(name = "data_criacao", nullable = false, updatable = false)
    private LocalDateTime dataCriacao;

    @Column(name = "data_atualizacao")
    private LocalDateTime dataAtualizacao;
}
