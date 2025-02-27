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
                dto.getMarca() != null ? MarcaMapper.toBO(dto.getMarca()) : null,
                dto.getGrupo() != null ? GrupoMapper.toBO(dto.getGrupo()) : null,
                dto.getCostPrice(),
                dto.getPrice(),
                dto.getQuantity(),
                dto.getImage(),
                dto.getSupplier() != null ? SupplierMapper.toBO(dto.getSupplier()) : null,
                dto.getActive());
    }

    public static ProductDTO toDTO(ProductBO bo) {
        if (bo == null) {
            return null;
        }

        var dto = new ProductDTO();
        dto.setId(bo.getId());
        dto.setName(bo.getName());
        dto.setDescription(bo.getDescription());
        dto.setMarca(bo.getMarcaBO() != null ? MarcaMapper.toDTO(bo.getMarcaBO()) : null);
        dto.setGrupo(bo.getGrupoBO() != null ? GrupoMapper.toDTO(bo.getGrupoBO()) : null);
        dto.setCostPrice(bo.getCostPrice());
        dto.setPrice(bo.getPrice());
        dto.setQuantity(bo.getQuantity());
        dto.setImage(bo.getImage());
        dto.setSupplier(bo.getSupplier() != null ? SupplierMapper.toDTO(bo.getSupplier()) : null);
        dto.setActive(bo.getActive());

        return dto;
    }
}
