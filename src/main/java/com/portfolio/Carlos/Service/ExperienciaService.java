package com.portfolio.Carlos.Service;

import com.portfolio.Carlos.model.Experiencia;
import com.portfolio.Carlos.repository.ExperienciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExperienciaService {

    @Autowired
    private ExperienciaRepository repository;

    public List<Experiencia> listarPorPerfil(Long perfilId) {
        return repository.findByPerfilId(perfilId);
    }

    public Experiencia salvar(Experiencia experiencia) {
        return repository.save(experiencia);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}