package com.portfolio.Carlos.Service;

import com.portfolio.Carlos.model.Projeto;
import com.portfolio.Carlos.repository.ProjetoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;



@Service
public class ProjetoService {
    @Autowired
    private ProjetoRepository repository;

    public List<Projeto> listarporPerfil(long perfilId){
        return repository.findByPerfilId(perfilId);
    }

    public Projeto salvar(Projeto projeto){

        return repository.save(projeto);
    }

    public void deletar(Long id){

        repository.deleteById(id);
    }
}
