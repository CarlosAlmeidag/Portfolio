package com.portfolio.Carlos.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;
import java.util.Optional;

@Data
@Entity
@Table(name = "perfis")
public class Perfil {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nome;
    private String bio;
    private String email;
    private String fotoPerfil;
    private String titulo;

    @OneToMany(mappedBy = "perfil", cascade = CascadeType.ALL)
    private List<Projeto> projetos;

    @OneToMany(mappedBy = "perfil", cascade = CascadeType.ALL)
    private List<Habilidade> habilidades;

    @OneToMany(mappedBy = "perfil", cascade = CascadeType.ALL)
    private List<Experiencia> experiencia;

    @OneToMany(mappedBy = "perfil", cascade = CascadeType.ALL)
    private List<Contato> contato;



}
