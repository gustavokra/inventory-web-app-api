package com.kraemer.infra.database.sqlite.mappers;

import com.kraemer.domain.entities.SupplierBO;
import com.kraemer.infra.database.sqlite.model.SqliteSupplier;

public class SqliteSupplierMapper {
        public static SqliteSupplier toEntity(SupplierBO domain) {
        var entity = new SqliteSupplier();
        entity.setId(domain.getId());
        entity.setName(domain.getName());
        entity.setDocument(domain.getDocument());
        entity.setContact(domain.getContact());
        entity.setAddress(domain.getAddress());
        entity.setActive(domain.isActive());

        return entity;
    }

    public static SupplierBO toDomain(SqliteSupplier entity) {

        return new SupplierBO(
                entity.getId(),
                entity.getName(),
                entity.getDocument(),
                entity.getContact(),
                entity.getAddress(),
                entity.isActive());
    }
}
