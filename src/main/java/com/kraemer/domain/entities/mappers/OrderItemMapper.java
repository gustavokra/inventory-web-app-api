package com.kraemer.domain.entities.mappers;

import com.kraemer.domain.entities.OrderItemBO;
import com.kraemer.domain.entities.dto.OrderItemDTO;

public class OrderItemMapper {
    public static OrderItemBO toBO(OrderItemDTO dto) {
        if (dto == null) {
            return null;
        }

        return new OrderItemBO(
                dto.getId(),
                dto.getOrder() != null ? OrderMapper.toBO(dto.getOrder()) : null,
                dto.getProduct() != null ? ProductMapper.toBO(dto.getProduct()) : null,
                dto.getQuantity(),
                dto.getUnitPrice());
    }

    public static OrderItemDTO toDTO(OrderItemBO bo) {
        if (bo == null) {
            return null;
        }

        var dto = new OrderItemDTO();
        dto.setId(bo.getId());
        dto.setOrder(bo.getOrder() != null ? OrderMapper.toDTO(bo.getOrder()) : null);
        dto.setProduct(bo.getProduct() != null ? ProductMapper.toDTO(bo.getProduct()) : null);
        dto.setQuantity(bo.getQuantity());
        dto.setUnitPrice(bo.getUnitPrice());

        return dto;
    }
}
