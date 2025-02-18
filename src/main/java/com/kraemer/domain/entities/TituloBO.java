package com.kraemer.domain.entities;

public class TituloBO {

    private Long id;
    
    private OrderBO pedido;

    private FormaPagamentoBO formaPagamento;

    public TituloBO(Long id, OrderBO pedido, FormaPagamentoBO formaPagamento) {
        this.id = id;
        this.pedido = pedido;
        this.formaPagamento = formaPagamento;
    }

    
    public Long getId() {
        return id;
    }

    public OrderBO getPedido() {
        return pedido;
    }

    public FormaPagamentoBO getFormaPagamento() {
        return formaPagamento;
    }

}
