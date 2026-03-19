package com.portfolio.Carlos.Service;

import com.portfolio.Carlos.model.Perfil;
import com.portfolio.Carlos.repository.PerfilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PerfilService {

    @Autowired
    private PerfilRepository repository;

    public List<Perfil> listarTodos() {
        return repository.findAll();
    }

    public Perfil buscarPorId(Long id) {
        return repository.findById(id).orElseThrow();
    }

    public Perfil salvar(Perfil perfil) {
        return repository.save(perfil);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}