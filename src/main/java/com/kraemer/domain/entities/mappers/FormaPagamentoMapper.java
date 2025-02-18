package com.kraemer.domain.entities.mappers;

import com.kraemer.domain.entities.FormaPagamentoBO;
import com.kraemer.domain.entities.dto.FormaPagamentoDTO;

public class FormaPagamentoMapper {

    public static FormaPagamentoBO toBO(FormaPagamentoDTO dto) {
        if (dto == null) {
            return null;
        }
        return new FormaPagamentoBO(
            dto.id(),
            dto.descricao(),
            dto.maxParcelas()
        );
    }

    public static FormaPagamentoDTO toDTO(FormaPagamentoBO bo) {
        if (bo == null) {
            return null;
        }
        return new FormaPagamentoDTO(
            bo.getId(),
            bo.getDescricao(),
            bo.getMaxParcelas()
        );
    }
}
