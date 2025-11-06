package com.ufersa.tcc.emprestimoapi.repository;

import com.ufersa.tcc.emprestimoapi.model.Material;
import com.ufersa.tcc.emprestimoapi.model.enums.StatusMaterial;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MaterialRepository extends JpaRepository <Material, String> {

    Optional<Material> findByCodigoPatrimonio(String codigoPatrimonio);
    List<Material> findByStatus(StatusMaterial status);
    boolean existsByCodigoPatrimonio(String codigoPatrimonio);
    List<Material> findByCategoria(String categoria);
}
