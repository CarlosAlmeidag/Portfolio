package com.portfolio.Carlos.Service;

import com.portfolio.Carlos.model.Contato;
import com.portfolio.Carlos.repository.ContatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContatoService {

    @Autowired
    private ContatoRepository repository;

    public List<Contato> listarPorPerfil(Long perfilId) {
        return repository.findByPerfilId(perfilId);
    }

    public Contato salvar(Contato contato) {
        return repository.save(contato);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}