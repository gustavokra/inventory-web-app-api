package com.kraemer.domain.usecases.order;

import java.util.List;

import com.kraemer.domain.entities.dto.OrderDTO;
import com.kraemer.domain.entities.enums.EnumErrorCode;
import com.kraemer.domain.entities.mappers.ClientMapper;
import com.kraemer.domain.entities.mappers.OrderItemMapper;
import com.kraemer.domain.entities.mappers.OrderMapper;
import com.kraemer.domain.entities.repositories.IOrderRepository;
import com.kraemer.domain.utils.exception.InventoryAppException;
import com.kraemer.domain.vo.QueryFieldInfoVO;;

public class UpdateOrder {

    private IOrderRepository repository;

    public UpdateOrder(IOrderRepository repository) {
        this.repository = repository;
    }

    public OrderDTO execute(OrderDTO dto, Long id) {
        validateId(id);

        var idField = new QueryFieldInfoVO("id", String.valueOf(id));
        var existingOrder = repository.findFirstBy(List.of(idField));

        if (existingOrder == null) {
            throw new InventoryAppException(EnumErrorCode.ENTIDADE_NAO_ENCONTRADA, "pedido", "id", id);
        }

        existingOrder.handleUpdate(
                dto.getClient() != null ? ClientMapper.toBO(dto.getClient()) : null,
                dto.getEnumStatus(),
                dto.getItems() != null ? dto.getItems().stream()
                        .map(OrderItemMapper::toBO).toList() : null);

        var orderUpdated = repository.merge(existingOrder);

        if (orderUpdated == null) {
            throw new InventoryAppException(EnumErrorCode.ERRO_AO_EDITAR, "pedido");
        }

        return OrderMapper.toDTO(orderUpdated);
    }

    private void validateId(Long id) {
        if (id == null) {
            throw new InventoryAppException(EnumErrorCode.CAMPO_OBRIGATORIO, "id");
        }
    }
}
