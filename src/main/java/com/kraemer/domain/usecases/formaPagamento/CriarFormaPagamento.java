package com.kraemer.domain.usecases.formaPagamento;

import java.util.List;

import com.kraemer.domain.entities.dto.FormaPagamentoDTO;
import com.kraemer.domain.entities.enums.EnumErrorCode;
import com.kraemer.domain.entities.mappers.FormaPagamentoMapper;
import com.kraemer.domain.entities.repositories.IFormaPagamentoRepository;
import com.kraemer.domain.utils.exception.InventoryAppException;
import com.kraemer.domain.vo.QueryFieldInfoVO;

public class CriarFormaPagamento {
    
    private IFormaPagamentoRepository repository;

    public CriarFormaPagamento(IFormaPagamentoRepository repository) {
        this.repository = repository;
    }

    public FormaPagamentoDTO execute(FormaPagamentoDTO dto) {
        validarFormaPagamentoExistente(dto.getNome());

        repository.criar(FormaPagamentoMapper.toBO(dto));

        return dto;
    }

    private void validarFormaPagamentoExistente(String nome) {
        var fieldProductName = new QueryFieldInfoVO("nome", nome);
        var existentProduct = repository.encontrarPrimeiroPor(List.of(fieldProductName));
        
        if(existentProduct != null) {
            throw new InventoryAppException(EnumErrorCode.ENTIDADE_CADASTRADA, "Forma pagamento com nome '" + nome + "'");
        }
    }

}
