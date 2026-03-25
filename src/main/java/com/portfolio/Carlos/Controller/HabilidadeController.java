package com.portfolio.Carlos.Controller;

import com.portfolio.Carlos.Service.HabilidadeService;
import com.portfolio.Carlos.model.Habilidade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/habilidades")
public class HabilidadeController {

    @Autowired
    private HabilidadeService service;

    @GetMapping("/perfil/{perfilId}")
    public ResponseEntity<List<Habilidade>> listar(@PathVariable Long perfilId) {
        return ResponseEntity.ok(service.listarPorPerfil(perfilId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Habilidade> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Habilidade> criar(@RequestBody Habilidade habilidade,
                                             @RequestParam Long perfilId) {
        return ResponseEntity.status(201).body(service.salvar(habilidade, perfilId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Habilidade> atualizar(@PathVariable Long id,
                                                 @RequestBody Habilidade habilidade,
                                                 @RequestParam Long perfilId) {
        habilidade.setId(id);
        return ResponseEntity.ok(service.salvar(habilidade, perfilId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
