package com.ufersa.tcc.emprestimoapi.controller;

import com.ufersa.tcc.emprestimoapi.dto.EmprestimoDTO;
import com.ufersa.tcc.emprestimoapi.dto.EmprestimoRequestDTO;
import com.ufersa.tcc.emprestimoapi.service.EmprestimoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/emprestimos")
@RequiredArgsConstructor
public class EmprestimoController {

    private final EmprestimoService emprestimoService;

    @PostMapping
    public ResponseEntity<EmprestimoDTO> solicitarEmprestimo(@Valid @RequestBody EmprestimoRequestDTO dto) {
        EmprestimoDTO emprestimoCriado = emprestimoService.solicitarEmprestimo(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(emprestimoCriado);
    }

    @GetMapping
    public ResponseEntity<List<EmprestimoDTO>> listarTodosEmprestimos() {
        List<EmprestimoDTO> emprestimos = emprestimoService.listarTodosEmprestimos();
        return ResponseEntity.ok(emprestimos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmprestimoDTO> buscarEmprestimoPorId(@PathVariable Long id) {
        EmprestimoDTO emprestimo = emprestimoService.buscarEmprestimoPorId(id);
        return ResponseEntity.ok(emprestimo);
    }

    @GetMapping("/usuario/{matricula}")
    public ResponseEntity<List<EmprestimoDTO>> listarEmprestimosPorUsuario(@PathVariable String matricula) {
        List<EmprestimoDTO> emprestimos = emprestimoService.listarEmprestimosPorUsuario(matricula);
        return ResponseEntity.ok(emprestimos);
    }

    @PutMapping("/{id}/devolucao")
    public ResponseEntity<EmprestimoDTO> registrarDevolucao(@PathVariable Long id) {
        EmprestimoDTO emprestimo = emprestimoService.registrarDevolucao(id);
        return ResponseEntity.ok(emprestimo);
    }

    @DeleteMapping("/{id}/cancelar")
    public ResponseEntity<Void> cancelarEmprestimo(@PathVariable Long id) {
        emprestimoService.cancelarEmprestimo(id);
        return ResponseEntity.noContent().build();
    }
}
