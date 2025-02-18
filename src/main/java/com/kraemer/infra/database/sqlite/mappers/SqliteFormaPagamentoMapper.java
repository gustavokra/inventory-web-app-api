package com.kraemer.infra.database.sqlite.mappers;

import com.kraemer.domain.entities.FormaPagamentoBO;
import com.kraemer.infra.database.sqlite.model.SqliteFormaPagamento;

public class SqliteFormaPagamentoMapper {

    public static FormaPagamentoBO toDomain(SqliteFormaPagamento entity) {
        if (entity == null) {
            return null;
        }
        return new FormaPagamentoBO(
            entity.getId(),
            entity.getNome(),
            entity.getnumeroMaxParcelas()
        );
    }

    public static SqliteFormaPagamento toEntity(FormaPagamentoBO domain) {
        if (domain == null) {
            return null;
        }
        SqliteFormaPagamento entity = new SqliteFormaPagamento();
        entity.setId(domain.getId());
        entity.setNome(domain.getNome());
        entity.setnumeroMaxParcelas(domain.getnumeroMaxParcelas());
        return entity;
    }
}
