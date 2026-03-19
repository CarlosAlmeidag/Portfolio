package com.portfolio.Carlos.Service;


import com.portfolio.Carlos.model.Habilidade;
import com.portfolio.Carlos.repository.HabilidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class HabilidadeService {

    @Autowired
    private HabilidadeRepository repository;

    public List<Habilidade> listarPorPerfil(Long perfilId){
        return repository.findByPerfilId(perfilId);
    }

    public Habilidade salvar(Habilidade habilidade){
        return repository.save(habilidade);
    }

    public void deletar(Long id){
        repository.deleteById(id);
    }
}
