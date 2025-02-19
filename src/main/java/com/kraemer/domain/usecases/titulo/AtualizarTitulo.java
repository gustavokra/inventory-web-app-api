package com.kraemer.domain.usecases.titulo;

import java.util.List;

import com.kraemer.domain.entities.TituloBO;
import com.kraemer.domain.entities.dto.TituloDTO;
import com.kraemer.domain.entities.enums.EnumErrorCode;
import com.kraemer.domain.entities.mappers.FormaPagamentoMapper;
import com.kraemer.domain.entities.mappers.OrderMapper;
import com.kraemer.domain.entities.mappers.TituloMapper;
import com.kraemer.domain.entities.repositories.ITituloRepository;
import com.kraemer.domain.utils.exception.InventoryAppException;
import com.kraemer.domain.vo.QueryFieldInfoVO;

import jakarta.inject.Inject;

public class AtualizarTitulo {

    @Inject
    private ITituloRepository repository;

    public AtualizarTitulo(ITituloRepository repository) {
        this.repository = repository;
    }

    public TituloDTO execute(TituloDTO dto, Long id) {
        var tituloAtualizar = verifyExistingTitulo(id);

        tituloAtualizar.verificarAtualizacao(
                OrderMapper.toBO(dto.getPedido()),
                FormaPagamentoMapper.toBO(dto.getFormaPagamento()));

        repository.atualizar(tituloAtualizar);

        return TituloMapper.toDTO(tituloAtualizar);
    }

    private TituloBO verifyExistingTitulo(Long id) {
        verifyId(id);

        var idField = new QueryFieldInfoVO("id", String.valueOf(id));

        var tituloBO = repository.encontrarPrimeiroPor(List.of(idField));

        if (tituloBO == null) {
            throw new InventoryAppException(EnumErrorCode.ENTIDADE_NAO_ENCONTRADA, "Titulo", "id", id);
        }

        return tituloBO;
    }

    private void verifyId(Long id) {

        if (id == null) {
            throw new InventoryAppException(EnumErrorCode.CAMPO_OBRIGATORIO, "id");
        }

    }

}
