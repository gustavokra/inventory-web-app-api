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
    private BigDecimal totalVendas;
    private EnumSituacaoCaixa situacao;
    private String observacoes;
    private Long usuarioId;

    public OperacaoCaixaBO(Long id, LocalDateTime dataAbertura, LocalDateTime dataFechamento, BigDecimal saldoInicial, BigDecimal saldoFinal, BigDecimal totalVendas, EnumSituacaoCaixa situacao, String observacoes, Long usuarioId) {
        this.id = id;
        this.dataAbertura = dataAbertura;
        this.dataFechamento = dataFechamento;
        this.saldoInicial = saldoInicial;
        this.saldoFinal = saldoFinal;
        this.totalVendas = totalVendas;
        this.situacao = situacao;
        this.observacoes = observacoes;
        this.usuarioId = usuarioId;

        validate();
    }

    private void validate() {
        if (id == null) {
            throw new IllegalArgumentException("Id is required");
        }

        if (dataAbertura == null) {
            throw new IllegalArgumentException("Data de abertura é obrigatória");
        }

        if (dataFechamento == null) {
            throw new IllegalArgumentException("Data de fechamento é obrigatória");
        }

        if(usuarioId == null) {
            throw new IllegalArgumentException("Usuário é obrigatório");
        }

        if(saldoInicial == null) {
            throw new IllegalArgumentException("Saldo inicial é obrigatório");
        }

    }


    public void handleUpdate(LocalDateTime dataFechamento, BigDecimal saldoFinal, BigDecimal totalVendas, EnumSituacaoCaixa situacao, String observacoes, Long usuarioId) {
        if (dataFechamento != null) {
            this.dataFechamento = dataFechamento;
        }

        if (saldoFinal != null) {
            this.saldoFinal = saldoFinal;
        }

        if (totalVendas != null) {
            this.totalVendas = totalVendas;
        }

        if (situacao != null) {
            this.situacao = situacao;
        }

        if (observacoes != null) {
            this.observacoes = observacoes;
        }

        if (usuarioId != null) {
            this.usuarioId = usuarioId;
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

    public BigDecimal getTotalVendas() {
        return totalVendas;
    }

    public EnumSituacaoCaixa getSituacao() {
        return situacao;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

} 