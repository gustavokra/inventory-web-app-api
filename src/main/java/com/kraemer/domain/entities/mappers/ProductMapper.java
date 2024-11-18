package com.kraemer.domain.entities.mappers;

import com.kraemer.domain.entities.ProductBO;
import com.kraemer.domain.entities.dto.ProductDTO;

public class ProductMapper {

    public static ProductBO toBO(ProductDTO dto) {
        if (dto == null) {
            return null;
        }

        return new ProductBO(
                dto.getId(),
                dto.getName(),
                dto.getDescription(),
                dto.getPrice(),
                dto.getQuantity(),
                dto.getImage(),
                SupplierMapper.idToBO(dto.getSupplier()));
    }

    public static ProductDTO toDTO(ProductBO bo) {
        if (bo == null) {
            return null;
        }

        var dto = new ProductDTO();
        dto.setId(bo.getId());
        dto.setName(bo.getName());
        dto.setDescription(bo.getDescription());
        dto.setPrice(bo.getPrice());
        dto.setQuantity(bo.getQuantity());
        dto.setImage(bo.getImage());
        dto.setSupplier(SupplierMapper.toDTO(bo.getSupplier()));

        return dto;
    }
}
