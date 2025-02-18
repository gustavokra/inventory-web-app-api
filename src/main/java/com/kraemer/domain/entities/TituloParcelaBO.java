package com.kraemer.domain.entities;

import java.math.BigDecimal;

public class TituloParcelaBO {
    
    private TituloBO tituloBO;

    private Integer numero;

    private BigDecimal valor;

    public TituloBO getTituloBO() {
        return tituloBO;
    }

    public Integer getNumero() {
        return numero;
    }

    public BigDecimal getValor() {
        return valor;
    }

}
