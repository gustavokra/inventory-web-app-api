package com.kraemer.domain.entities.mappers;

import com.kraemer.domain.entities.GrupoBO;
import com.kraemer.domain.entities.dto.GrupoDTO;

public class GrupoMapper {
    public static GrupoDTO toDTO(GrupoBO bo) {
        GrupoDTO dto = new GrupoDTO();

        dto.setId(bo.getId());
        dto.setNome(bo.getNome());
        
        return dto;
    }

    public static GrupoBO toBO(GrupoDTO dto) {
        return new GrupoBO(dto.getId(), dto.getNome());
    }

}
