package com.portfolio.Carlos.Controller;


import com.portfolio.Carlos.Service.HabilidadeService;
import com.portfolio.Carlos.model.Habilidade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("api/habilidades")
public class HabilidadeController {

    @Autowired
    private HabilidadeService service;

    @GetMapping("/perfil/{perfilId}")
    public ResponseEntity<List<Habilidade>> listar (@PathVariable Long perfilId){
        return ResponseEntity.ok(service.listarPorPerfil(perfilId));
    }

    @PostMapping
    public ResponseEntity<Habilidade> criar(@RequestBody Habilidade habilidade){
        return ResponseEntity.status(201).body(service.salvar(habilidade));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }

}
