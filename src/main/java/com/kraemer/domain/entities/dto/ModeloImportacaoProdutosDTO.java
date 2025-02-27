package com.kraemer.domain.entities.dto;

import java.math.BigDecimal;

import jakarta.json.bind.annotation.JsonbProperty;

public class ModeloImportacaoProdutosDTO {

	@JsonbProperty("Nome")
    private String nome;

	@JsonbProperty("Categoria")
    private String categoria;
    
	@JsonbProperty("Estoque Atual")
	private Integer estoqueAtual;
    
	@JsonbProperty("Preço de Venda")
	private BigDecimal precoVenda;
    
	@JsonbProperty("Custo")
	private BigDecimal custo;
    
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public Integer getEstoqueAtual() {
		return estoqueAtual;
	}
	public void setEstoqueAtual(Integer estoqueAtual) {
		this.estoqueAtual = estoqueAtual;
	}
	public BigDecimal getPrecoVenda() {
		return precoVenda;
	}
	public void setPrecoVenda(BigDecimal precoVenda) {
		this.precoVenda = precoVenda;
	}
	public BigDecimal getCusto() {
		return custo;
	}
	public void setCusto(BigDecimal custo) {
		this.custo = custo;
	}

    
}
