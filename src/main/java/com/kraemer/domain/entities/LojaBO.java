package com.kraemer.domain.entities;

import com.kraemer.domain.entities.vo.CreatedAtVO;

public class LojaBO {
    
    private Long id;

    private String nome;

    private CreatedAtVO dataCriacao;

    public LojaBO(Long id, String nome, CreatedAtVO dataCriacao) {
        this.id = id;
        this.nome = nome;
        this.dataCriacao = dataCriacao;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public CreatedAtVO getDataCriacao() {
        return dataCriacao;
    }

}
