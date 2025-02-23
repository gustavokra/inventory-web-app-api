package com.kraemer.domain.entities.mappers;

import com.kraemer.domain.entities.FormaPagamentoBO;
import com.kraemer.domain.entities.TituloBO;
import com.kraemer.domain.entities.dto.FormaPagamentoDTO;
import com.kraemer.domain.entities.dto.TituloDTO;
import com.kraemer.domain.entities.vo.CreatedAtVO;

public class TituloMapper {
    public static TituloBO toBO(TituloDTO dto) {
        if (dto == null) {
            return null;
        }

        FormaPagamentoBO formaPagamentoBO = dto.getFormaPagamento() != null
                ? FormaPagamentoMapper.toBO(dto.getFormaPagamento())
                : null;

        return new TituloBO(dto.getId(), dto.getIdPedido(), formaPagamentoBO, dto.getNumeroParcelas(), dto.getValorParcelas() , new CreatedAtVO(dto.getDataCriacao()));
    }

    public static TituloDTO toDTO(TituloBO bo) {
        if (bo == null) {
            return null;
        }

        FormaPagamentoDTO formaPagamentoDTO = bo.getFormaPagamento() != null ? FormaPagamentoMapper.toDTO(bo.getFormaPagamento()) : null;

        TituloDTO tituloDTO = new TituloDTO();
        tituloDTO.setId(bo.getId());
        tituloDTO.setIdPedido(bo.getIdPedido());
        tituloDTO.setFormaPagamento(formaPagamentoDTO);
        tituloDTO.setNumeroParcelas(bo.getNumeroParcelas());
        tituloDTO.setValorParcelas(bo.getValorParcelas());
        tituloDTO.setDataCriacao(bo.getDataCriacao().getValue());

        return tituloDTO;
    }
}
