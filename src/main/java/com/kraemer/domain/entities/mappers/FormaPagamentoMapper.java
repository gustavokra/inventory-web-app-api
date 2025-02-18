package com.kraemer.domain.entities.mappers;

import com.kraemer.domain.entities.FormaPagamentoBO;
import com.kraemer.domain.entities.dto.FormaPagamentoDTO;

public class FormaPagamentoMapper {

    public static FormaPagamentoBO toBO(FormaPagamentoDTO dto) {
        if (dto == null) {
            return null;
        }
        return new FormaPagamentoBO(
            dto.getId(),
            dto.getNome(),
            dto.getnumeroMaxParcelas()
        );
    }

    public static FormaPagamentoDTO toDTO(FormaPagamentoBO bo) {
        if (bo == null) {
            return null;
        }
        FormaPagamentoDTO dto = new FormaPagamentoDTO();
        dto.setId(bo.getId());
        dto.setNome(bo.getNome());
        dto.setnumeroMaxParcelas(bo.getnumeroMaxParcelas());

        return dto;
    }
}
