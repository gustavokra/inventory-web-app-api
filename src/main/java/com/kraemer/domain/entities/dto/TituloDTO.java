package com.kraemer.domain.entities.dto;

public class TituloDTO {

    private Long id;

    private OrderDTO pedido;

    private FormaPagamentoDTO formaPagamento;

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

}
