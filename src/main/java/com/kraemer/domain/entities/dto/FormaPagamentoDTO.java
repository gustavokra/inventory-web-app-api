package com.kraemer.domain.entities.dto;

public record FormaPagamentoDTO (
    Long id,
    String descricao,
    Integer maxParcelas
) {}
