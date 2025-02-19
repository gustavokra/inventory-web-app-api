package com.kraemer.infra.database.sqlite.mappers;


import com.kraemer.domain.entities.TituloBO;
import com.kraemer.domain.entities.vo.CreatedAtVO;
import com.kraemer.infra.database.sqlite.model.SqliteTitulo;

public class SqliteTituloMapper {

    public static TituloBO toDomain(SqliteTitulo entity) {
        if (entity == null) {
            return null;
        }
        return new TituloBO(
            entity.getId(),
            SqliteOrderMapper.toDomain(entity.getPedido()),
            SqliteFormaPagamentoMapper.toDomain(entity.getFormaPagamento()),
            new CreatedAtVO(entity.getDataCriacao())
        );
    }

    public static SqliteTitulo toEntity(TituloBO domain) {
        if (domain == null) {
            return null;
        }

        SqliteTitulo entity = new SqliteTitulo();
        
        entity.setId(domain.getId());
        entity.setPedido(SqliteOrderMapper.toEntity(domain.getPedido()));
        entity.setFormaPagamento(SqliteFormaPagamentoMapper.toEntity(domain.getFormaPagamento()));
        entity.setDataCriacao(domain.getDataCriacao().getValue());
        
        return entity;
    }
}