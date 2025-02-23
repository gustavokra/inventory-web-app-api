package com.kraemer.domain.entities;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;

import com.kraemer.domain.entities.enums.EnumErrorCode;
import com.kraemer.domain.entities.vo.CreatedAtVO;
import com.kraemer.domain.utils.NumericUtil;
import com.kraemer.domain.utils.exception.InventoryAppException;

public class TituloBO {

    private Long id;
    
    private Long idPedido;

    private FormaPagamentoBO formaPagamento;

    private Integer numeroParcelas;

    private BigDecimal valorParcelas;

    private CreatedAtVO dataCriacao;

    public TituloBO(Long id, Long idPedido, FormaPagamentoBO formaPagamento, Integer numeroParcelas, BigDecimal valorParcelas, CreatedAtVO dataCriacao) {
        this.id = id;
        this.idPedido = idPedido;
        this.formaPagamento = formaPagamento;
        this.numeroParcelas = numeroParcelas;
        this.valorParcelas = valorParcelas;
        this.dataCriacao = dataCriacao;

        validate();
    }

    private void validate() {
        if(idPedido == null) {
            throw new InventoryAppException(EnumErrorCode.CAMPO_OBRIGATORIO, "Pedido");
        }

        if(formaPagamento == null) {
            throw new InventoryAppException(EnumErrorCode.CAMPO_OBRIGATORIO, "Forma de Pagamento");
        }

                if(formaPagamento == null) {
            throw new InventoryAppException(EnumErrorCode.CAMPO_OBRIGATORIO, "Forma de Pagamento");
        }

        if(numeroParcelas == null) {
            throw new InventoryAppException(EnumErrorCode.CAMPO_OBRIGATORIO, "Número de Parcelas");
        }

        if(valorParcelas == null) {
            throw new InventoryAppException(EnumErrorCode.CAMPO_OBRIGATORIO, "Valor das Parcelas");
        }
    }

    public void handleCreate() {
        if(id == null) {
            id = 0l;
        }

        dataCriacao = new CreatedAtVO(LocalDateTime.now());

    }

    public BigDecimal calcularValorParcela(BigDecimal valorTotal) {
        return valorTotal.divide(NumericUtil.toBigDecimal(numeroParcelas), RoundingMode.HALF_UP);
    }

    public void atualizarAtributos(Long idPedido, FormaPagamentoBO formaPagamento, Integer numeroParcelas, BigDecimal valorParcelas) {
        if(idPedido != null) {
            this.idPedido = idPedido;
        }

        if(formaPagamento != null) {
            this.formaPagamento = formaPagamento;
        }

        if(numeroParcelas != null) {
            this.numeroParcelas = numeroParcelas;
        }

        if(valorParcelas != null) {
            this.valorParcelas = valorParcelas;
        }
    }

    public Long getId() {
        return id;
    }

    public Long getIdPedido() {
        return idPedido;
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
