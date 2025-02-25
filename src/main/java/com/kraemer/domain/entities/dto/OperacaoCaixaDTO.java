package com.kraemer.domain.entities.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.kraemer.domain.entities.enums.EnumSituacaoCaixa;

public class OperacaoCaixaDTO {
	private Long id;
	private LocalDateTime dataAbertura;
	private LocalDateTime dataFechamento;
	private BigDecimal saldoInicial;
	private BigDecimal saldoFinal;
	private BigDecimal totalMovimentado;
	private EnumSituacaoCaixa situacao;
	private String observacoes;
	private Long usuarioId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getDataAbertura() {
		return dataAbertura;
	}

	public void setDataAbertura(LocalDateTime dataAbertura) {
		this.dataAbertura = dataAbertura;
	}

	public LocalDateTime getDataFechamento() {
		return dataFechamento;
	}

	public void setDataFechamento(LocalDateTime dataFechamento) {
		this.dataFechamento = dataFechamento;
	}

	public BigDecimal getSaldoInicial() {
		return saldoInicial;
	}

	public void setSaldoInicial(BigDecimal saldoInicial) {
		this.saldoInicial = saldoInicial;
	}

	public BigDecimal getSaldoFinal() {
		return saldoFinal;
	}

	public void setSaldoFinal(BigDecimal saldoFinal) {
		this.saldoFinal = saldoFinal;
	}

	public BigDecimal getTotalMovimentado() {
		return totalMovimentado;
	}

	public void setTotalMovimentado(BigDecimal totalMovimentado) {
		this.totalMovimentado = totalMovimentado;
	}

	public EnumSituacaoCaixa getSituacao() {
		return situacao;
	}

	public void setSituacao(EnumSituacaoCaixa situacao) {
		this.situacao = situacao;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	public Long getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(Long usuarioId) {
		this.usuarioId = usuarioId;
	}

}