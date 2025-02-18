package com.kraemer.infra.database.sqlite.mappers;

import java.util.stream.Collectors;

import com.kraemer.domain.entities.OrderBO;
import com.kraemer.domain.entities.enums.EnumErrorCode;
import com.kraemer.domain.entities.vo.CreatedAtVO;
import com.kraemer.domain.utils.exception.InventoryAppException;
import com.kraemer.infra.database.sqlite.model.SqliteClient;
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
            SqliteClient managedClient = SqliteClient.findById(domain.getClient().getId());
            if (managedClient == null) {
                throw new InventoryAppException(EnumErrorCode.CAMPO_INVALIDO, "cliente id " + domain.getClient().getId());
            }

            entity.setClient(managedClient);
        }

        if (domain.getItemsBO() != null && !domain.getItemsBO().isEmpty()) {
            entity.setItems(domain.getItemsBO().stream()
                    .map(SqliteOrderItemMapper::toEntity)
                    .toList());
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
                entity.getItems() != null ? entity.getItems().stream().map(item -> SqliteOrderItemMapper.toDomain(item, false)).toList() : null,
                entity.getFormasPagamento() != null ? entity.getFormasPagamento().stream().map(SqliteFormaPagamentoMapper::toDomain).toList() : null
                );
    }
}
