package com.kraemer.domain.entities;

import java.math.BigDecimal;

public class TituloPagamentoBO {
    
    private TituloBO titulo;

    private Integer numeroParcelas;

    private BigDecimal valorParcelas;

    public TituloBO getTitulo() {
        return titulo;
    }

    public Integer getNumeroParcelas() {
        return numeroParcelas;
    }

    public BigDecimal getValorParcelas() {
        return valorParcelas;
    }
    
}
