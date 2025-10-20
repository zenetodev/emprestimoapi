package com.ufersa.tcc.emprestimoapi.repository;

import com.ufersa.tcc.emprestimoapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
    boolean existsByEmail(String email);
    boolean existsByMatricula(String matricula);
    boolean existsByCpf(String cpf);
}
