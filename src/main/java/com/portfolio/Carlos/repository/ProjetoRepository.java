package com.portfolio.Carlos.repository;

import com.portfolio.Carlos.model.Projeto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjetoRepository extends JpaRepository<Projeto, Long>{
        List<Projeto> findByPerfilId(Long PerfilId);
    }

