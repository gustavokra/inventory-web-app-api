package com.kraemer.domain.entities;

public class MarcaBO {
    
    private Long id;

    private String nome;

    public MarcaBO(Long id, String nome) {
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
