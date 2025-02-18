package com.kraemer.domain.entities;

public class FormaPagamentoBO {

    private Long id;
    
    private String descricao;

    private Integer maxParcelas;

    public FormaPagamentoBO(Long id, String descricao, Integer maxParcelas) {
        this.id = id;
        this.descricao = descricao;
        this.maxParcelas = maxParcelas;
    }

    public Long getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public Integer getMaxParcelas() {
        return maxParcelas;
    }

    
}

