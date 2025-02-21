package com.kraemer.domain.entities;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.kraemer.domain.entities.enums.EnumErrorCode;
import com.kraemer.domain.entities.vo.CreatedAtVO;
import com.kraemer.domain.utils.NumericUtil;
import com.kraemer.domain.utils.exception.InventoryAppException;

public class TituloBO {

    private Long id;
    
    private OrderBO pedido;

    private FormaPagamentoBO formaPagamento;

    private Integer numeroParcelas;

    private BigDecimal valorParcelas;

    private CreatedAtVO dataCriacao;

    public TituloBO(Long id, OrderBO pedido, FormaPagamentoBO formaPagamento, Integer numeroParcelas, BigDecimal valorParcelas, CreatedAtVO dataCriacao) {
        this.id = id;
        this.pedido = pedido;
        this.formaPagamento = formaPagamento;
        this.numeroParcelas = numeroParcelas;
        this.valorParcelas = valorParcelas;
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

        valorParcelas = calcularValorParcela(pedido.getTotalValue(), numeroParcelas);
    }

    public static BigDecimal calcularValorParcela(BigDecimal valorTotal, int numeroParcelas) {
        return valorTotal.divide(NumericUtil.toBigDecimal(numeroParcelas), RoundingMode.HALF_UP);
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

    public Integer getNumeroParcelas() {
        return numeroParcelas;
    }

    public BigDecimal getValorParcelas() {
        return valorParcelas;
    }
    
}
