package com.portfolio.Carlos.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "contatos")
public class Contato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tipo;  // ex: "GitHub", "LinkedIn", "WhatsApp"
    private String url;   // ex: "https://github.com/seunome"

    @ManyToOne
    @JoinColumn(name = "perfil_id")
    private Perfil perfil;
}
