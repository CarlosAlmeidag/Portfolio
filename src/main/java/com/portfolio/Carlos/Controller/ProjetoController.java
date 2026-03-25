package com.portfolio.Carlos.Controller;

import com.portfolio.Carlos.Service.ProjetoService;
import com.portfolio.Carlos.model.Projeto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projetos")
public class ProjetoController {

    @Autowired
    private ProjetoService service;

    @GetMapping("/perfil/{perfilId}")
    public ResponseEntity<List<Projeto>> listar(@PathVariable Long perfilId) {
        return ResponseEntity.ok(service.listarporPerfil(perfilId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Projeto> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Projeto> criar(@RequestBody Projeto projeto,
                                          @RequestParam Long perfilId) {
        return ResponseEntity.status(201).body(service.salvar(projeto, perfilId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Projeto> atualizar(@PathVariable Long id,
                                              @RequestBody Projeto projeto,
                                              @RequestParam Long perfilId) {
        projeto.setId(id);
        return ResponseEntity.ok(service.salvar(projeto, perfilId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
