package com.kraemer.domain.entities.dto;

import java.math.BigDecimal;

public class TituloPagamentoDTO {

    private TituloDTO titulo;

    private Integer numeroParcelas;

    private BigDecimal valorParcelas;

    public TituloDTO getTitulo() {
        return titulo;
    }

    public void setTitulo(TituloDTO titulo) {
        this.titulo = titulo;
    }

    public Integer getNumeroParcelas() {
        return numeroParcelas;
    }

    public void setNumeroParcelas(Integer numeroParcelas) {
        this.numeroParcelas = numeroParcelas;
    }

    public BigDecimal getValorParcelas() {
        return valorParcelas;
    }

    public void setValorParcelas(BigDecimal valorParcelas) {
        this.valorParcelas = valorParcelas;
    }

    
}
