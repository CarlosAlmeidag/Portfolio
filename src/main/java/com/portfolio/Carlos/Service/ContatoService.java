package com.portfolio.Carlos.Service;

import com.portfolio.Carlos.model.Contato;
import com.portfolio.Carlos.model.Perfil;
import com.portfolio.Carlos.repository.ContatoRepository;
import com.portfolio.Carlos.repository.PerfilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContatoService {

    @Autowired
    private ContatoRepository repository;

    @Autowired
    private PerfilRepository perfilRepository;

    public List<Contato> listarPorPerfil(Long perfilId) {
        return repository.findByPerfilId(perfilId);
    }

    public Contato buscarPorId(Long id) {
        return repository.findById(id).orElseThrow();
    }

    public Contato salvar(Contato contato, Long perfilId) {
        Perfil perfil = perfilRepository.findById(perfilId).orElseThrow();
        contato.setPerfil(perfil);
        return repository.save(contato);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}
