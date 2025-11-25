package com.ufersa.tcc.emprestimoapi.repository;

import com.ufersa.tcc.emprestimoapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
    boolean existsByMatricula(String matricula);
    boolean existsByCpf(String cpf);
}
