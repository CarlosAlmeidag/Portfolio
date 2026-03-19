package com.portfolio.Carlos.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "projetos")
public class Projeto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String titulo;
    private String descricao;
    private String linkGitHub;
    private String linkDeploy;
    private String tecnologias;

    @ManyToOne
    @JoinColumn(name = "perfil_id")
    private Perfil perfil;


}
