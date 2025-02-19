package com.kraemer.domain.entities;

import com.kraemer.domain.entities.enums.EnumErrorCode;
import com.kraemer.domain.entities.vo.CreatedAtVO;
import com.kraemer.domain.utils.exception.InventoryAppException;

public class TituloBO {

    private Long id;
    
    private OrderBO pedido;

    private FormaPagamentoBO formaPagamento;

    private CreatedAtVO dataCriacao;

    public TituloBO(Long id, OrderBO pedido, FormaPagamentoBO formaPagamento, CreatedAtVO dataCriacao) {
        this.id = id;
        this.pedido = pedido;
        this.formaPagamento = formaPagamento;
        this.dataCriacao = dataCriacao;

        validate();
    }

    private void validate() {
        if(pedido == null) {
            throw new InventoryAppException(EnumErrorCode.CAMPO_OBRIGATORIO, "Pedido");
        }

        if(formaPagamento == null) {
            throw new InventoryAppException(EnumErrorCode.CAMPO_OBRIGATORIO, "Forma de Pagamento");
        }
    }

    public void verificarAtualizacao(OrderBO pedido, FormaPagamentoBO formaPagamento) {
        if(pedido != null) {
            this.pedido = pedido;
        }

        if(formaPagamento != null) {
            this.formaPagamento = formaPagamento;
        }
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

    public CreatedAtVO getDataCriacao() {
        return dataCriacao;
    }

}
