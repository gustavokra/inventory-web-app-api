package com.kraemer.infra.database.sqlite.mappers;

import com.kraemer.domain.entities.OrderItemBO;
import com.kraemer.domain.entities.enums.EnumErrorCode;
import com.kraemer.domain.utils.exception.InventoryAppException;
import com.kraemer.infra.database.sqlite.model.SqliteOrderItem;
import com.kraemer.infra.database.sqlite.model.SqliteProduct;

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
            SqliteProduct managedProduct = SqliteProduct.findById(domain.getProduct().getId());
            if(managedProduct == null) {
                throw new InventoryAppException(EnumErrorCode.CAMPO_INVALIDO, "produto id " + domain.getProduct().getId());
            }

            entity.setProduct(managedProduct);
        }

        return entity;
    }

    public static OrderItemBO toDomain(SqliteOrderItem entity, boolean mapOrder) {
        if (entity == null) {
            return null;
        }

        return new OrderItemBO(
            entity.getId(),
            entity.getOrder() != null && mapOrder ? SqliteOrderMapper.toDomain(entity.getOrder()) : null,
            entity.getProduct() != null ? SqliteProductMapper.toDomain(entity.getProduct()) : null,
            entity.getQuantity(),
            entity.getUnitPrice()
        );
    }
}
