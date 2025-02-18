package com.kraemer.domain.entities.mappers;

import com.kraemer.domain.entities.FormaPagamentoBO;
import com.kraemer.domain.entities.OrderBO;
import com.kraemer.domain.entities.TituloBO;
import com.kraemer.domain.entities.dto.FormaPagamentoDTO;
import com.kraemer.domain.entities.dto.OrderDTO;
import com.kraemer.domain.entities.dto.TituloDTO;

public class TituloMapper {
    public static TituloBO toBO(TituloDTO dto) {
        if (dto == null) {
            return null;
        }

        OrderBO pedidoBO = dto.getPedido() != null ? OrderMapper.toBO(dto.getPedido()) : null;
        FormaPagamentoBO formaPagamentoBO = dto.getFormaPagamento() != null
                ? FormaPagamentoMapper.toBO(dto.getFormaPagamento())
                : null;

        return new TituloBO(dto.getId(), pedidoBO, formaPagamentoBO);
    }

    public static TituloDTO toDTO(TituloBO bo) {
        if (bo == null) {
            return null;
        }

        OrderDTO pedidoDTO = bo.getPedido() != null ? OrderMapper.toDTO(bo.getPedido()) : null;
        FormaPagamentoDTO formaPagamentoDTO = bo.getFormaPagamento() != null ? FormaPagamentoMapper.toDTO(bo.getFormaPagamento()) : null;

        TituloDTO tituloDTO = new TituloDTO();
        tituloDTO.setId(bo.getId());
        tituloDTO.setPedido(pedidoDTO);
        tituloDTO.setFormaPagamento(formaPagamentoDTO);

        return tituloDTO;
    }
}
