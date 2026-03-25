package com.portfolio.Carlos.Service;

import com.portfolio.Carlos.model.Perfil;
import com.portfolio.Carlos.model.Projeto;
import com.portfolio.Carlos.repository.PerfilRepository;
import com.portfolio.Carlos.repository.ProjetoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjetoService {

    @Autowired
    private ProjetoRepository repository;

    @Autowired
    private PerfilRepository perfilRepository;

    public List<Projeto> listarporPerfil(long perfilId) {
        return repository.findByPerfilId(perfilId);
    }

    public Projeto buscarPorId(Long id) {
        return repository.findById(id).orElseThrow();
    }

    public Projeto salvar(Projeto projeto, Long perfilId) {
        Perfil perfil = perfilRepository.findById(perfilId).orElseThrow();
        projeto.setPerfil(perfil);
        return repository.save(projeto);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}
