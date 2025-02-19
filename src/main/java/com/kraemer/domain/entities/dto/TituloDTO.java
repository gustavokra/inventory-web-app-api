package com.kraemer.domain.entities.dto;

import java.time.LocalDateTime;

public class TituloDTO {

    private Long id;

    private OrderDTO pedido;

    private FormaPagamentoDTO formaPagamento;

    private LocalDateTime dataCriacao;

    public OrderDTO getPedido() {
        return pedido;
    }

    public void setPedido(OrderDTO pedido) {
        this.pedido = pedido;
    }

    public FormaPagamentoDTO getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(FormaPagamentoDTO formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

}
