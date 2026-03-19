package com.portfolio.Carlos.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "experiencias")
public class Experiencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String empresa;
    private String cargo;
    private String descricao;
    private String dataInicio;
    private String dataFim;

    @ManyToOne
    @JoinColumn(name = "perfil_id")
    private Perfil perfil;
}