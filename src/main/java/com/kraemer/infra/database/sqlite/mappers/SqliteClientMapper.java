package com.kraemer.infra.database.sqlite.mappers;

import com.kraemer.domain.entities.ClientBO;
import com.kraemer.infra.database.sqlite.model.SqliteClient;

public class SqliteClientMapper {

    public static SqliteClient toEntity(ClientBO domain) {
        var entity = new SqliteClient();
        entity.setId(domain.getId());
        entity.setName(domain.getName());
        entity.setDocument(domain.getDocument());
        entity.setContact(domain.getContact());
        entity.setAddress(domain.getAddress());
        entity.setActive(domain.isActive());

        return entity;
    }

    public static ClientBO toDomain(SqliteClient entity) {

        return new ClientBO(
                entity.getId(),
                entity.getName(),
                entity.getDocument(),
                entity.getContact(),
                entity.getAddress(),
                entity.isActive());
    }
}
