package com.kraemer.domain.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.kraemer.domain.entities.enums.EnumSituacaoCaixa;

public class OperacaoCaixaBO {
    private Long id;
    private LocalDateTime dataAbertura;
    private LocalDateTime dataFechamento;
    private BigDecimal saldoInicial;
    private BigDecimal saldoFinal;
    private BigDecimal totalMovimentado;
    private EnumSituacaoCaixa situacao;
    private String observacoes;

    public OperacaoCaixaBO(Long id, LocalDateTime dataAbertura, LocalDateTime dataFechamento, BigDecimal saldoInicial, BigDecimal saldoFinal, BigDecimal totalMovimentado, EnumSituacaoCaixa situacao, String observacoes) {
        this.id = id;
        this.dataAbertura = dataAbertura;
        this.dataFechamento = dataFechamento;
        this.saldoInicial = saldoInicial;
        this.saldoFinal = saldoFinal;
        this.totalMovimentado = totalMovimentado;
        this.situacao = situacao;
        this.observacoes = observacoes;

        validate();
    }

    private void validate() {

        if (dataAbertura == null) {
            throw new IllegalArgumentException("Data de abertura é obrigatória");
        }


        if(saldoInicial == null) {
            throw new IllegalArgumentException("Saldo inicial é obrigatório");
        }

    }


    public void handleUpdate(LocalDateTime dataFechamento, BigDecimal saldoInicial, BigDecimal saldoFinal, BigDecimal totalMovimentado, EnumSituacaoCaixa situacao, String observacoes) {
        if (dataFechamento != null) {
            this.dataFechamento = dataFechamento;
        }

        if(saldoInicial != null) {
            this.saldoInicial = saldoInicial;
        }

        if (saldoFinal != null) {
            this.saldoFinal = saldoFinal;
        }

        if (totalMovimentado != null) {
            this.totalMovimentado = totalMovimentado;
        }

        if (situacao != null) {
            this.situacao = situacao;
        }

        if (observacoes != null) {
            this.observacoes = observacoes;
        }

    }       
    
    public Long getId() {
        return id;
    }


    public LocalDateTime getDataAbertura() {
        return dataAbertura;
    }

    public LocalDateTime getDataFechamento() {
        return dataFechamento;
    }

    public BigDecimal getSaldoInicial() {
        return saldoInicial;
    }

    public BigDecimal getSaldoFinal() {
        return saldoFinal;
    }

    public BigDecimal getTotalMovimentado() {
        return totalMovimentado;
    }

    public EnumSituacaoCaixa getSituacao() {
        return situacao;
    }

    public String getObservacoes() {
        return observacoes;
    }

} 