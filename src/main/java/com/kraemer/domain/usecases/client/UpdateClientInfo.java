package com.kraemer.domain.usecases.client;

import java.util.List;

import com.kraemer.domain.entities.dto.ClientDTO;
import com.kraemer.domain.entities.enums.EnumErrorCode;
import com.kraemer.domain.entities.mappers.ClientMapper;
import com.kraemer.domain.entities.repositories.IClientRepository;
import com.kraemer.domain.utils.exception.InventoryAppException;
import com.kraemer.domain.vo.QueryFieldInfoVO;

import jakarta.inject.Inject;

public class UpdateClientInfo {

    @Inject
    private IClientRepository repository;

    public UpdateClientInfo(IClientRepository repository) {
        this.repository = repository;
    }

    public ClientDTO execute(ClientDTO dto, Long id) {
        verifyId(id);

        var idField = new QueryFieldInfoVO("id", String.valueOf(id));
        var existingClientBO = repository.findFirstBy(List.of(idField));
        if (existingClientBO == null) {
            throw new InventoryAppException(EnumErrorCode.ENTIDADE_NAO_ENCONTRADA, "cliente", "id", id);
        }

        var nameField = new QueryFieldInfoVO("name", String.valueOf(dto.getName()));
        var existingClientWithName = repository.findFirstBy(List.of(nameField));
        if(existingClientWithName != null && !existingClientWithName.getId().equals(id)) {
            throw new InventoryAppException(EnumErrorCode.ENTIDADE_CADASTRADA, "Cliente com nome '" + dto.getName() + "'");
        }

        existingClientBO.handleUpdateInfo(
                dto.getName(),
                dto.getDocument(),
                dto.getContact(),
                dto.getAddress(),
                dto.getIdLoja(),
                dto.isActive());

        return ClientMapper.toDTO(repository.merge(existingClientBO));
    }

    private void verifyId(Long id) {

        if (id == null) {
            throw new InventoryAppException(EnumErrorCode.CAMPO_OBRIGATORIO, "id");
        }

    }

}
