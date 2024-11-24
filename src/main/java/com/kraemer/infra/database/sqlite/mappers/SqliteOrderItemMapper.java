package com.kraemer.infra.database.sqlite.mappers;

import com.kraemer.domain.entities.OrderItemBO;
import com.kraemer.infra.database.sqlite.model.SqliteOrderItem;

public class SqliteOrderItemMapper {
    public static SqliteOrderItem toEntity(OrderItemBO domain) {
        if (domain == null) {
            return null;
        }

        var entity = new SqliteOrderItem();
        entity.setId(domain.getId());
        entity.setQuantity(domain.getQuantity());
        entity.setUnitPrice(domain.getUnitPrice());

        if (domain.getOrder() != null) {
            entity.setOrder(SqliteOrderMapper.toEntity(domain.getOrder()));
        }

        if (domain.getProduct() != null) {
            entity.setProduct(SqliteProductMapper.toEntity(domain.getProduct()));
        }

        return entity;
    }

    public static OrderItemBO toDomain(SqliteOrderItem entity) {
        if (entity == null) {
            return null;
        }

        return new OrderItemBO(
            entity.getId(),
            entity.getOrder() != null ? SqliteOrderMapper.toDomain(entity.getOrder()) : null,
            entity.getProduct() != null ? SqliteProductMapper.toDomain(entity.getProduct()) : null,
            entity.getQuantity(),
            entity.getUnitPrice()
        );
    }
}
