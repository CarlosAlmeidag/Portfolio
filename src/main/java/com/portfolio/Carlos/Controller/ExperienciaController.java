package com.portfolio.Carlos.Controller;

import com.portfolio.Carlos.Service.ExperienciaService;
import com.portfolio.Carlos.model.Experiencia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/experiencias")
public class ExperienciaController {

    @Autowired
    private ExperienciaService service;

    @GetMapping("/perfil/{perfilId}")
    public ResponseEntity<List<Experiencia>> listar(@PathVariable Long perfilId) {
        return ResponseEntity.ok(service.listarPorPerfil(perfilId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Experiencia> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Experiencia> criar(@RequestBody Experiencia experiencia,
                                              @RequestParam Long perfilId) {
        return ResponseEntity.status(201).body(service.salvar(experiencia, perfilId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Experiencia> atualizar(@PathVariable Long id,
                                                  @RequestBody Experiencia experiencia,
                                                  @RequestParam Long perfilId) {
        experiencia.setId(id);
        return ResponseEntity.ok(service.salvar(experiencia, perfilId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
