package com.portfolio.Carlos.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "habilidades")
public class Habilidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String nivel;


    @ManyToOne
    @JoinColumn(name = "perfil_id")
    private Perfil perfil;
}
