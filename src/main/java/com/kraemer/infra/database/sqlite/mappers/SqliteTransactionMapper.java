package com.kraemer.infra.database.sqlite.mappers;

import com.kraemer.domain.entities.TransactionBO;
import com.kraemer.domain.entities.vo.CreatedAtVO;
import com.kraemer.infra.database.sqlite.model.SqliteOrder;
import com.kraemer.infra.database.sqlite.model.SqliteProduct;
import com.kraemer.infra.database.sqlite.model.SqliteTransaction;

public class SqliteTransactionMapper {

    public static SqliteTransaction toEntity(TransactionBO domain) {
        if (domain == null) {
            return null;
        }

        SqliteTransaction entity = new SqliteTransaction();
        entity.setId(domain.getId());
        entity.setCreatedAt(domain.getCreatedAt().getValue());
        entity.setTransactionType(domain.getTransactionType());
        entity.setValue(domain.getValue());

        SqliteProduct product = SqliteProduct.findById(entity.getProduct().getId());
        entity.setProduct(product);

        SqliteOrder order = SqliteOrder.findById(entity.getOrder().getId());
        entity.setOrder(order);

        return entity;
    }

    public static TransactionBO toDomain(SqliteTransaction entity) {
        if (entity == null) {
            return null;
        }

        return new TransactionBO(
                entity.getId(),
                new CreatedAtVO(entity.getCreatedAt()),
                entity.getTransactionType(),
                entity.getValue(),
                SqliteProductMapper.toDomain(entity.getProduct()),
                SqliteOrderMapper.toDomain(entity.getOrder()));
    }

}
