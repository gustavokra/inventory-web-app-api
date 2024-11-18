package com.kraemer.domain.entities.mappers;

import com.kraemer.domain.entities.SupplierBO;
import com.kraemer.domain.entities.dto.SupplierDTO;

public class SupplierMapper {

    public static SupplierBO toBO(SupplierDTO dto) {
        if (dto.isActive() == null) {
            dto.setActive(true);
        }

        return new SupplierBO(
                dto.getId(),
                dto.getName(),
                dto.getDocument(),
                dto.getContact(),
                dto.getAddress(),
                dto.isActive());
    }

    public static SupplierBO idToBO(SupplierDTO dto) {
        return new SupplierBO(
                dto.getId());
    }

    public static SupplierDTO toDTO(SupplierBO bo) {
        var dto = new SupplierDTO();
        dto.setId(bo.getId());
        dto.setName(bo.getName());
        dto.setDocument(bo.getDocument());
        dto.setContact(bo.getContact());
        dto.setAddress(bo.getAddress());
        dto.setActive(bo.isActive());

        return dto;
    }

}
