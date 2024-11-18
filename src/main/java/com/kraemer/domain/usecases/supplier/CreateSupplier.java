package com.kraemer.domain.usecases.supplier;

import java.util.List;

import com.kraemer.domain.entities.dto.SupplierDTO;
import com.kraemer.domain.entities.enums.EnumErrorCode;
import com.kraemer.domain.entities.mappers.SupplierMapper;
import com.kraemer.domain.entities.repositories.ISupplierRepository;
import com.kraemer.domain.utils.exception.InvetoryAppException;
import com.kraemer.domain.vo.QueryFieldInfoVO;

public class CreateSupplier {
    
    private ISupplierRepository repository;

    public CreateSupplier(ISupplierRepository repository) {
        this.repository = repository;
    }

    public SupplierDTO execute(SupplierDTO dto) {
        var bo = SupplierMapper.toBO(dto);

        verifyExisting(bo.getDocument());

        var createdSupplier = repository.create(bo);

        return SupplierMapper.toDTO(createdSupplier);
    }

    private void verifyExisting(String document) {
        QueryFieldInfoVO documentField = new QueryFieldInfoVO("document", document);
        var existingSupplierBO = repository.findFirstBy(List.of(documentField));
        
        if(existingSupplierBO != null) {
            throw new InvetoryAppException(EnumErrorCode.ENTIDADE_CADASTRADA, "Fornecedor com documento " + document);
        }
    }
}
