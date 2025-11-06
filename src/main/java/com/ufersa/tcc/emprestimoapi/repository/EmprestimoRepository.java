package com.ufersa.tcc.emprestimoapi.repository;

import com.ufersa.tcc.emprestimoapi.model.Emprestimo;
import com.ufersa.tcc.emprestimoapi.model.Material;
import com.ufersa.tcc.emprestimoapi.model.User;
import com.ufersa.tcc.emprestimoapi.model.enums.StatusEmprestimo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {
    List<Emprestimo> findByUsuario(User usuario);
    List<Emprestimo> findByStatus(StatusEmprestimo status);
    Optional<Emprestimo> findByUsuarioAndMaterialAndStatus(User usuario, Material material, StatusEmprestimo status);
    List<Emprestimo> findByDataDevolucaoBeforeAndStatusNot(LocalDateTime data, StatusEmprestimo status);
}
