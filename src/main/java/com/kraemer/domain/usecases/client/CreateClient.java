package com.kraemer.domain.usecases.client;

import java.util.List;

import com.kraemer.domain.entities.dto.ClientDTO;
import com.kraemer.domain.entities.enums.EnumErrorCode;
import com.kraemer.domain.entities.mappers.ClientMapper;
import com.kraemer.domain.entities.repositories.IClientRepository;
import com.kraemer.domain.utils.exception.InventoryAppException;
import com.kraemer.domain.vo.QueryFieldInfoVO;

public class CreateClient {
    
    private IClientRepository repository;

    public CreateClient(IClientRepository repository) {
        this.repository = repository;
    }

    public ClientDTO execute(ClientDTO dto) {
        var bo = ClientMapper.toBO(dto);

        verifyExistingClient(bo.getDocument());

        var createdClient = repository.create(bo);

        return ClientMapper.toDTO(createdClient);
    }

    private void verifyExistingClient(String document) {
        QueryFieldInfoVO documentField = new QueryFieldInfoVO("document", document);
        var existingClientBO = repository.findFirstBy(List.of(documentField));
        
        if(existingClientBO != null) {
            throw new InventoryAppException(EnumErrorCode.ENTIDADE_CADASTRADA, "Cliente com documento " + document);
        }
    }
}
