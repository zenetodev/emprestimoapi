package com.ufersa.tcc.emprestimoapi.controller;

import com.ufersa.tcc.emprestimoapi.dto.MaterialDTO;
import com.ufersa.tcc.emprestimoapi.dto.MaterialRegistroDTO;
import com.ufersa.tcc.emprestimoapi.service.MaterialService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/materiais")
@RequiredArgsConstructor
public class MaterialController {

    private final MaterialService materialService;

    @PostMapping
    public ResponseEntity<MaterialDTO> cadastrarMaterial(@Valid @RequestBody MaterialRegistroDTO dto) {
        MaterialDTO materialCriado = materialService.cadastrarMaterial(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(materialCriado);
    }

    @GetMapping
    public ResponseEntity<List<MaterialDTO>> listarTodosMateriais() {
        List<MaterialDTO> materiais = materialService.listarTodosMateriais();
        return ResponseEntity.ok(materiais);
    }

    @GetMapping("/disponiveis")
    public ResponseEntity<List<MaterialDTO>> listarMateriaisDisponiveis() {
        List<MaterialDTO> materiais = materialService.listarMateriaisDisponiveis();
        return ResponseEntity.ok(materiais);
    }

    @GetMapping("/{codigoPatrimonio}")
    public ResponseEntity<MaterialDTO> buscarMaterialPorCodigo(@PathVariable String codigoPatrimonio) {
        MaterialDTO material = materialService.buscarMaterialPorCodigo(codigoPatrimonio);
        return ResponseEntity.ok(material);
    }

    @PutMapping("/{codigoPatrimonio}")
    public ResponseEntity<MaterialDTO> atualizarMaterial(@PathVariable String codigoPatrimonio,
                                                         @Valid @RequestBody MaterialRegistroDTO dto) {
        MaterialDTO materialAtualizado = materialService.atualizarMaterial(codigoPatrimonio, dto);
        return ResponseEntity.ok(materialAtualizado);
    }

    @DeleteMapping("/{codigoPatrimonio}")
    public ResponseEntity<Void> deletarMaterial(@PathVariable String codigoPatrimonio) {
        materialService.deletarMaterial(codigoPatrimonio);
        return ResponseEntity.noContent().build();
    }
}
