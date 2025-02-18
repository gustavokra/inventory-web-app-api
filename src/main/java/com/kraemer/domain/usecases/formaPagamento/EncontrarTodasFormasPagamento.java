package com.kraemer.domain.usecases.formaPagamento;

import java.util.List;

import com.kraemer.domain.entities.dto.FormaPagamentoDTO;
import com.kraemer.domain.entities.mappers.FormaPagamentoMapper;
import com.kraemer.domain.entities.repositories.IFormaPagamentoRepository;

public class EncontrarTodasFormasPagamento {
    
    private IFormaPagamentoRepository repository;

    public EncontrarTodasFormasPagamento(IFormaPagamentoRepository repository) {
        this.repository = repository;
    }

    public List<FormaPagamentoDTO> execute() {
        return repository.encontrarTodos().stream().map(FormaPagamentoMapper::toDTO).toList();
    }

}
