package com.kraemer.infra.database.sqlite.mappers;

import com.kraemer.domain.entities.OperacaoCaixaBO;
import com.kraemer.infra.database.sqlite.model.SqliteOperacaoCaixa;

public class SqliteOperacaoCaixaMapper {
    
    public static SqliteOperacaoCaixa toEntity(OperacaoCaixaBO bo) {
        if (bo == null) return null;
        
        var entity = new SqliteOperacaoCaixa();
        entity.setId(bo.getId());
        entity.setDataAbertura(bo.getDataAbertura());
        entity.setDataFechamento(bo.getDataFechamento());
        entity.setSaldoInicial(bo.getSaldoInicial());
        entity.setSaldoFinal(bo.getSaldoFinal());
        entity.setTotalVendas(bo.getTotalMovimentado());
        entity.setSituacao(bo.getSituacao());
        entity.setObservacoes(bo.getObservacoes());
        
        return entity;
    }
    
    public static OperacaoCaixaBO toDomain(SqliteOperacaoCaixa entity) {
        if (entity == null) return null;
        
        return new OperacaoCaixaBO(
            entity.getId(),
            entity.getDataAbertura(),
            entity.getDataFechamento(),
            entity.getSaldoInicial(),
            entity.getSaldoFinal(),
            entity.getTotalMovimentado(),
            entity.getSituacao(),
            entity.getObservacoes()
        );
    }
} 