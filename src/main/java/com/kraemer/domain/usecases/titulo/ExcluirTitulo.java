package com.kraemer.domain.usecases.titulo;

import java.util.List;

import com.kraemer.domain.entities.enums.EnumErrorCode;
import com.kraemer.domain.entities.repositories.ITituloRepository;
import com.kraemer.domain.utils.exception.InventoryAppException;
import com.kraemer.domain.vo.QueryFieldInfoVO;

public class ExcluirTitulo {

    private ITituloRepository repository;

    public ExcluirTitulo(ITituloRepository repository) {
        this.repository = repository;
    }

    public void execute(Long id) {
        if (id == null) {
            throw new InventoryAppException(EnumErrorCode.CAMPO_OBRIGATORIO, "id");
        }

        var fieldId = new QueryFieldInfoVO("id", String.valueOf(id));

        var formaPgtoExcluir = repository.encontrarPrimeiroPor(List.of(fieldId));

        if (formaPgtoExcluir == null) {
            throw new InventoryAppException(EnumErrorCode.ENTIDADE_NAO_ENCONTRADA, "Titulo", "id", id);
        }

        if (!repository.excluir(id)) {
            throw new InventoryAppException(EnumErrorCode.ERRO_AO_EXCLUIR, "Titulo", "id", id);
        }
    }

}
