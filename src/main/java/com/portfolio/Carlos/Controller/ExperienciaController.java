package com.portfolio.Carlos.Controller;

import com.portfolio.Carlos.Service.ExperienciaService;
import com.portfolio.Carlos.model.Experiencia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
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

    @PostMapping
    public ResponseEntity<Experiencia> criar(@RequestBody Experiencia experiencia) {
        return ResponseEntity.status(201).body(service.salvar(experiencia));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
