package com.kraemer.infra.database.sqlite.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "titulo")
public class SqliteTitulo {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pedido_id", nullable = false)
    private SqliteOrder pedido;

    @ManyToOne
    @JoinColumn(name = "forma_pagamento_id", nullable = false)
    private SqliteFormaPagamento formaPagamento;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SqliteOrder getPedido() {
        return pedido;
    }

    public void setPedido(SqliteOrder pedido) {
        this.pedido = pedido;
    }

    public SqliteFormaPagamento getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(SqliteFormaPagamento formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    
}
