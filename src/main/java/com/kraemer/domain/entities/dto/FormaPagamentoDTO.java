package com.kraemer.domain.entities.dto;

public class FormaPagamentoDTO {
    private Long id;
    private String nome;
    private Integer numeroMaxParcelas;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getnumeroMaxParcelas() {
        return numeroMaxParcelas;
    }

    public void setnumeroMaxParcelas(Integer numeroMaxParcelas) {
        this.numeroMaxParcelas = numeroMaxParcelas;
    }

}
