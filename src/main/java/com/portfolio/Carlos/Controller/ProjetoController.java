package com.portfolio.Carlos.Controller;

import com.portfolio.Carlos.Service.ProjetoService;
import com.portfolio.Carlos.model.Projeto;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProjetoController {

    @Autowired
    private ProjetoService service;

    @GetMapping("/perfil/{perfilId}")
    public ResponseEntity<List<Projeto>> listar (@PathVariable Long perfilId){
        return ResponseEntity.ok(service.listarporPerfil(perfilId));
    }

    @PostMapping
    public ResponseEntity<Projeto> criar(@RequestBody Projeto projeto){
        return ResponseEntity.status(201).body(service.salvar(projeto));
    }

    @DeleteMapping
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
