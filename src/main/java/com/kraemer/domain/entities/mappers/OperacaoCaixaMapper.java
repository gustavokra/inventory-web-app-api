package com.kraemer.domain.entities.mappers;

import com.kraemer.domain.entities.dto.OperacaoCaixaDTO;
import com.kraemer.domain.entities.OperacaoCaixaBO;

public class OperacaoCaixaMapper {
    
    public static OperacaoCaixaBO toBO(OperacaoCaixaDTO dto) {
        if (dto == null) return null;
        
        
        return new OperacaoCaixaBO(
            dto.getId(),
            dto.getDataAbertura(),
            dto.getDataFechamento(),
            dto.getSaldoInicial(),
            dto.getSaldoFinal(),
            dto.getTotalMovimentado(),
            dto.getSituacao(),
            dto.getObservacoes()
        );
    }
    
    public static OperacaoCaixaDTO toDTO(OperacaoCaixaBO bo) {
        if (bo == null) return null;
        
        var dto = new OperacaoCaixaDTO();
        dto.setId(bo.getId());
        dto.setDataAbertura(bo.getDataAbertura());
        dto.setDataFechamento(bo.getDataFechamento());
        dto.setSaldoInicial(bo.getSaldoInicial());
        dto.setSaldoFinal(bo.getSaldoFinal() );
        dto.setTotalMovimentado(bo.getTotalMovimentado());
        dto.setSituacao(bo.getSituacao());
        dto.setObservacoes(bo.getObservacoes());
        
        return dto;
    }
}