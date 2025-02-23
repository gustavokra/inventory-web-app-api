package com.kraemer.infra.database.sqlite.mappers;


import com.kraemer.domain.entities.TituloBO;
import com.kraemer.domain.entities.vo.CreatedAtVO;
import com.kraemer.infra.database.sqlite.model.SqliteOrder;
import com.kraemer.infra.database.sqlite.model.SqliteTitulo;

public class SqliteTituloMapper {

    public static TituloBO toDomain(SqliteTitulo entity) {
        if (entity == null) {
            return null;
        }
        return new TituloBO(
            entity.getId(),
            entity.getPedido().getId(),
            SqliteFormaPagamentoMapper.toDomain(entity.getFormaPagamento()),
            entity.getNumeroParcelas(),
            entity.getValorParcelas(),
            new CreatedAtVO(entity.getDataCriacao())
        );
    }

    public static SqliteTitulo toEntity(TituloBO domain) {
        if (domain == null) {
            return null;
        }

        SqliteTitulo entity = new SqliteTitulo();
        
        entity.setId(domain.getId());
        entity.setPedido(SqliteOrder.findById(domain.getIdPedido()));
        entity.setFormaPagamento(SqliteFormaPagamentoMapper.toEntity(domain.getFormaPagamento()));
        entity.setNumeroParcelas(domain.getNumeroParcelas());
        entity.setValorParcelas(domain.getValorParcelas());
        entity.setDataCriacao(domain.getDataCriacao().getValue());
        
        return entity;
    }
}