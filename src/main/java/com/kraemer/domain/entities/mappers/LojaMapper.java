package com.kraemer.domain.entities.mappers;

import com.kraemer.domain.entities.LojaBO;
import com.kraemer.domain.entities.dto.LojaDTO;
import com.kraemer.domain.entities.vo.CreatedAtVO;

public class LojaMapper {
    
    public static LojaBO toBO(LojaDTO dto) {
        return new LojaBO(dto.getId(), dto.getNome(), new CreatedAtVO(dto.getDataCriacao()));
    }

    public static LojaDTO toDTO(LojaBO bo) {
        LojaDTO dto = new LojaDTO();
        dto.setId(bo.getId());
        dto.setNome(bo.getNome());
        dto.setDataCriacao(bo.getDataCriacao().getValue());
        return dto;
    }
}
