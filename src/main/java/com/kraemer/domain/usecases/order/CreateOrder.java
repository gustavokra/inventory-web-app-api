package com.kraemer.domain.usecases.order;

import com.kraemer.domain.entities.OrderBO;
import com.kraemer.domain.entities.dto.OrderDTO;
import com.kraemer.domain.entities.enums.EnumErrorCode;
import com.kraemer.domain.entities.mappers.OrderMapper;
import com.kraemer.domain.entities.repositories.IOrderRepository;
import com.kraemer.domain.utils.exception.InventoryAppException;

public class CreateOrder {

    private IOrderRepository repository;

    public CreateOrder(IOrderRepository repository) {
        this.repository = repository;
    }

    public OrderDTO execute(OrderDTO dto) {
        validateOrderDTO(dto);

        var orderToCreate = mapAndPrepareOrder(dto);

        var createdOrder = repository.create(orderToCreate);
        ensureOrderWasCreated(createdOrder);

        return OrderMapper.toDTO(createdOrder);
    }

    private void validateOrderDTO(OrderDTO dto) {
        if (dto == null) {
            throw new InventoryAppException(EnumErrorCode.CAMPO_OBRIGATORIO, "Pedido é obrigatório");
        }
    }

    private OrderBO mapAndPrepareOrder(OrderDTO dto) {
        var orderToCreate = OrderMapper.toBO(dto);
        
        orderToCreate.handleCreate();
        return orderToCreate;
    }

    private void ensureOrderWasCreated(OrderBO createdOrder) {
        if (createdOrder == null) {
            throw new InventoryAppException(EnumErrorCode.ERRO_AO_CRIAR, "Erro ao criar o pedido. Tente novamente");
        }
    }

}
