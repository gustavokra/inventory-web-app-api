package com.kraemer.domain.usecases.client;

import java.util.List;

import com.kraemer.domain.entities.ClientBO;
import com.kraemer.domain.entities.dto.ClientDTO;
import com.kraemer.domain.entities.enums.EnumErrorCode;
import com.kraemer.domain.entities.mappers.ClientMapper;
import com.kraemer.domain.entities.repositories.IClientRepository;
import com.kraemer.domain.utils.exception.InvetoryAppException;
import com.kraemer.domain.vo.QueryFieldInfoVO;

import jakarta.inject.Inject;

public class UpdateClientInfo {

    @Inject
    private IClientRepository repository;

    public UpdateClientInfo(IClientRepository repository) {
        this.repository = repository;
    }

    public ClientDTO execute(ClientDTO dto, Long id) {
        var existingClientBO = verifyExistingClientId(id);

        existingClientBO.handleUpdateInfo(
                dto.getName(),
                dto.getDocument(),
                dto.getContact(),
                dto.getAddress(),
                dto.isActive());

        repository.merge(existingClientBO);

        return ClientMapper.toDTO(existingClientBO);
    }

    private ClientBO verifyExistingClientId(Long id) {
        verifyId(id);

        var idField = new QueryFieldInfoVO("id", String.valueOf(id));

        var existingClientBO = repository.findFirstBy(List.of(idField));

        if (existingClientBO == null) {
            throw new InvetoryAppException(EnumErrorCode.ENTIDADE_NAO_ENCONTRADA, "cliente", "id", id);
        }

        return existingClientBO;
    }

    private void verifyId(Long id) {

        if (id == null) {
            throw new InvetoryAppException(EnumErrorCode.CAMPO_OBRIGATORIO, "id");
        }

    }

}
