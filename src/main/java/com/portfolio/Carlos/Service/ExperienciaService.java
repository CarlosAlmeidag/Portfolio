package com.portfolio.Carlos.Service;

import com.portfolio.Carlos.model.Experiencia;
import com.portfolio.Carlos.model.Perfil;
import com.portfolio.Carlos.repository.ExperienciaRepository;
import com.portfolio.Carlos.repository.PerfilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExperienciaService {

    @Autowired
    private ExperienciaRepository repository;

    @Autowired
    private PerfilRepository perfilRepository;

    public List<Experiencia> listarPorPerfil(Long perfilId) {

        return repository.findByPerfilId(perfilId);
    }

    public Experiencia buscarPorId(Long id) {

        return repository.findById(id).orElseThrow();
    }

    public Experiencia salvar(Experiencia experiencia, Long perfilId) {
        Perfil perfil = perfilRepository.findById(perfilId).orElseThrow();
        experiencia.setPerfil(perfil);
        return repository.save(experiencia);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}
