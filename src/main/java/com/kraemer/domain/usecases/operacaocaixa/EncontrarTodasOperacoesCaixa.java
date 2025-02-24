package com.kraemer.domain.usecases.operacaocaixa;

import java.util.List;

import com.kraemer.domain.entities.dto.OperacaoCaixaDTO;
import com.kraemer.domain.entities.mappers.OperacaoCaixaMapper;
import com.kraemer.domain.entities.repositories.IOperacaoCaixaRepository;

public class EncontrarTodasOperacoesCaixa {
    
    private IOperacaoCaixaRepository repository;

    public EncontrarTodasOperacoesCaixa(IOperacaoCaixaRepository repository) {
        this.repository = repository;
    }

    public List<OperacaoCaixaDTO> execute() {
        var list = repository.encontrarTodos();


        return list.stream().map(OperacaoCaixaMapper::toDTO).toList();
    }
}
