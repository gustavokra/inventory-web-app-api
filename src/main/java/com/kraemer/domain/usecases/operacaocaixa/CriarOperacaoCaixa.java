package com.kraemer.domain.usecases.operacaocaixa;

import com.kraemer.domain.entities.dto.OperacaoCaixaDTO;
import com.kraemer.domain.entities.enums.EnumErrorCode;
import com.kraemer.domain.entities.mappers.OperacaoCaixaMapper;
import com.kraemer.domain.entities.repositories.IOperacaoCaixaRepository;
import com.kraemer.domain.utils.exception.InventoryAppException;

public class CriarOperacaoCaixa {
    
    private IOperacaoCaixaRepository repository;
    
    public CriarOperacaoCaixa(IOperacaoCaixaRepository repository) {
        this.repository = repository;
    }
    
    public OperacaoCaixaDTO execute(OperacaoCaixaDTO dto) {
        var bo = OperacaoCaixaMapper.toBO(dto);
        var created = repository.criar(bo);

        if (created == null) {
            throw new InventoryAppException(EnumErrorCode.ERRO_AO_CRIAR, "Erro ao criar operação de caixa");
        }

        return OperacaoCaixaMapper.toDTO(created);
    }
    
} 