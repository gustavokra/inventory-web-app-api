package com.kraemer.infra.database.sqlite.mappers;

import com.kraemer.domain.entities.ProductBO;
import com.kraemer.infra.database.sqlite.model.SqliteProduct;

public class SqliteProductMapper {
     public static SqliteProduct toEntity(ProductBO domain) {
        if (domain == null) {
            return null;
        }

        var entity = new SqliteProduct();
        entity.setId(domain.getId());
        entity.setName(domain.getName());
        entity.setDescription(domain.getDescription());
        entity.setPrice(domain.getPrice());
        entity.setQuantity(domain.getQuantity());
        entity.setImage(domain.getImage());

        if (domain.getSupplier() != null) {
            entity.setSupplier(SqliteSupplierMapper.toEntity(domain.getSupplier()));
        }

        entity.setActive(domain.getActive());

        return entity;
    }

    public static ProductBO toDomain(SqliteProduct entity) {
        if (entity == null) {
            return null;
        }

        return new ProductBO(
            entity.getId(),
            entity.getName(),
            entity.getDescription(),
            entity.getPrice(),
            entity.getQuantity(),
            entity.getImage(),
            entity.getSupplier() != null ? SqliteSupplierMapper.toDomain(entity.getSupplier()) : null,
            entity.getActive()
        );
    }
}
