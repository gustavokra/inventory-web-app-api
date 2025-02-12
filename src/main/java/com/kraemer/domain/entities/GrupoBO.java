package com.kraemer.domain.entities;

public class GrupoBO {
    
    private Long id;

    private String nome;

    public GrupoBO(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

}
