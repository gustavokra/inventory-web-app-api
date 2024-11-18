package com.kraemer.domain.usecases.client;

import java.util.List;

import com.kraemer.domain.entities.enums.EnumErrorCode;
import com.kraemer.domain.entities.repositories.IClientRepository;
import com.kraemer.domain.utils.exception.InvetoryAppException;
import com.kraemer.domain.vo.QueryFieldInfoVO;

public class DeleteClient {

    private IClientRepository repository;

    public DeleteClient(IClientRepository repository) {
        this.repository = repository;
    }

    public void execute(Long id) {
        if (id == null) {
            throw new InvetoryAppException(EnumErrorCode.CAMPO_OBRIGATORIO, "id");
        }

        var fieldId = new QueryFieldInfoVO("id", String.valueOf(id));

        var clientToDelete = repository.findFirstBy(List.of(fieldId));

        if (clientToDelete == null) {
            throw new InvetoryAppException(EnumErrorCode.ENTIDADE_NAO_ENCONTRADA, "cliente", "id", id);
        }

        if (!repository.delete(id)) {
            throw new InvetoryAppException(EnumErrorCode.ERRO_AO_EXCLUIR, "cliente", "id", id);
        }
    }

}
