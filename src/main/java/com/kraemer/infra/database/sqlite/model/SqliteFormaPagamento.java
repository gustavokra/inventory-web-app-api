package com.kraemer.infra.database.sqlite.model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "forma_pagamento")
public class SqliteFormaPagamento extends PanacheEntityBase {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150, unique = true)
    private String nome;

    @Column(nullable = true)
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
