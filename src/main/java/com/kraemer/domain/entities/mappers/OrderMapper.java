package com.kraemer.domain.entities.mappers;

import com.kraemer.domain.entities.OrderBO;
import com.kraemer.domain.entities.dto.OrderDTO;

public class OrderMapper {
    
    public static OrderBO toBO(OrderDTO dto) {
        if (dto == null) {
            return null;
        }

        return new OrderBO(
                dto.getId(),
                dto.getDate(),
                dto.getClient() != null ? ClientMapper.toBO(dto.getClient()) : null,
                dto.getStatus(),
                dto.getTotalValue());
    }

    public static OrderDTO toDTO(OrderBO bo) {
        if (bo == null) {
            return null;
        }

        var dto = new OrderDTO();
        dto.setId(bo.getId());
        dto.setDate(bo.getDate());
        dto.setClient(bo.getClient() != null ? ClientMapper.toDTO(bo.getClient()) : null);
        dto.setStatus(bo.getStatus());
        dto.setTotalValue(bo.getTotalValue());

        return dto;
    }

}
