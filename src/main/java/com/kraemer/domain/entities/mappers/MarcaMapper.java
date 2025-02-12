package com.kraemer.domain.entities.mappers;

import com.kraemer.domain.entities.MarcaBO;
import com.kraemer.domain.entities.dto.MarcaDTO;

public class MarcaMapper {
    
    public static MarcaDTO toDTO(MarcaBO bo) {
        MarcaDTO dto = new MarcaDTO();

        dto.setId(bo.getId());
        dto.setNome(bo.getNome());
        
        return dto;
    }

    public static MarcaBO toBO(MarcaDTO dto) {
        return new MarcaBO(dto.getId(), dto.getNome());
    }

}
