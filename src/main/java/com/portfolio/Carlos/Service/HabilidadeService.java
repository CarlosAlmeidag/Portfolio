package com.portfolio.Carlos.Service;

import com.portfolio.Carlos.model.Habilidade;
import com.portfolio.Carlos.model.Perfil;
import com.portfolio.Carlos.repository.HabilidadeRepository;
import com.portfolio.Carlos.repository.PerfilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HabilidadeService {

    @Autowired
    private HabilidadeRepository repository;

    @Autowired
    private PerfilRepository perfilRepository;

    public List<Habilidade> listarPorPerfil(Long perfilId) {
        return repository.findByPerfilId(perfilId);
    }

    public Habilidade buscarPorId(Long id) {
        return repository.findById(id).orElseThrow();
    }

    public Habilidade salvar(Habilidade habilidade, Long perfilId) {
        Perfil perfil = perfilRepository.findById(perfilId).orElseThrow();
        habilidade.setPerfil(perfil);
        return repository.save(habilidade);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}
