package com.kraemer.infra.database.sqlite.mappers;

import java.util.stream.Collectors;

import com.kraemer.domain.entities.OrderBO;
import com.kraemer.domain.entities.vo.CreatedAtVO;
import com.kraemer.infra.database.sqlite.model.SqliteOrder;

public class SqliteOrderMapper {
    public static SqliteOrder toEntity(OrderBO domain) {
        if (domain == null) {
            return null;
        }

        var entity = new SqliteOrder();
        entity.setId(domain.getId());
        entity.setCreatedAt(domain.getCreatedAt().getValue());
        entity.setStatus(domain.getEnumStatus());
        entity.setTotalValue(domain.getTotalValue());

        if (domain.getClient() != null) {
            entity.setClient(SqliteClientMapper.toEntity(domain.getClient()));
        }

        if (domain.getItemsBO() != null && !domain.getItemsBO().isEmpty()) {
            entity.setItems(domain.getItemsBO().stream()
                    .map(SqliteOrderItemMapper::toEntity)
                    .collect(Collectors.toList()));
        }

        return entity;
    }

    public static OrderBO toDomain(SqliteOrder entity) {
        if (entity == null) {
            return null;
        }

        return new OrderBO(
                entity.getId(),
                new CreatedAtVO(entity.getCreatedAt()),
                SqliteClientMapper.toDomain(entity.getClient()),
                entity.getStatus(),
                entity.getTotalValue(),
                entity.getItems() != null ? entity.getItems().stream()
                        .map(SqliteOrderItemMapper::toDomain)
                        .collect(Collectors.toList()) : null);
    }
}
