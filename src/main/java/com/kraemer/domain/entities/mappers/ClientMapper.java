package com.kraemer.domain.entities.mappers;

import com.kraemer.domain.entities.ClientBO;
import com.kraemer.domain.entities.dto.ClientDTO;

public class ClientMapper {

    public static ClientBO toBO(ClientDTO dto) {
        if(dto.isActive() == null) {
            dto.setActive(true);
        }

        return new ClientBO(
                dto.getId(),
                dto.getName(),
                dto.getDocument(),
                dto.getContact(),
                dto.getAddress(),
                dto.isActive());
    }

    public static ClientDTO toDTO(ClientBO bo) {
        var dto = new ClientDTO();
        dto.setId(bo.getId());
        dto.setName(bo.getName());
        dto.setDocument(bo.getDocument());
        dto.setContact(bo.getContact());
        dto.setAddress(bo.getAddress());
        dto.setActive(bo.isActive());

        return dto;
    }
}
