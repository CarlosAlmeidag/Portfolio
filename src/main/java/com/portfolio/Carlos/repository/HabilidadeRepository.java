package com.portfolio.Carlos.repository;

import com.portfolio.Carlos.model.Habilidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HabilidadeRepository extends JpaRepository<Habilidade, Long>{
    List<Habilidade> findByPerfilId(Long perfilId);

}
