package com.portfolio.Carlos.Controller;

import com.portfolio.Carlos.Service.ContatoService;
import com.portfolio.Carlos.model.Contato;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/api/contatos")
public class ContatoController {

    @Autowired
    private ContatoService service;

    @GetMapping("/perfil/{perfilId}")
    public ResponseEntity<List<Contato>> listar(@PathVariable Long perfilId) {
        return ResponseEntity.ok(service.listarPorPerfil(perfilId));
    }

    @PostMapping
    public ResponseEntity<Contato> criar(@RequestBody Contato contato) {
        return ResponseEntity.status(201).body(service.salvar(contato));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
