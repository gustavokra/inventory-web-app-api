package com.kraemer.domain.usecases.supplier;

import java.util.List;

import com.kraemer.domain.entities.dto.SupplierDTO;
import com.kraemer.domain.entities.enums.EnumErrorCode;
import com.kraemer.domain.entities.mappers.SupplierMapper;
import com.kraemer.domain.entities.repositories.ISupplierRepository;
import com.kraemer.domain.utils.StringUtil;
import com.kraemer.domain.utils.exception.InventoryAppException;
import com.kraemer.domain.vo.QueryFieldInfoVO;

public class CreateSupplier {

    private ISupplierRepository repository;

    public CreateSupplier(ISupplierRepository repository) {
        this.repository = repository;
    }

    public SupplierDTO execute(SupplierDTO dto) {
        var bo = SupplierMapper.toBO(dto);

        verifyExisting(bo.getDocument(), bo.getName());

        var createdSupplier = repository.create(bo);

        return SupplierMapper.toDTO(createdSupplier);
    }

    private void verifyExisting(String document, String name) {
        if (StringUtil.isNotNullOrEmpty(document)) {
            checkExistingSupplier("document", document, "documento " + document);
        }
        if (StringUtil.isNotNullOrEmpty(name)) {
            checkExistingSupplier("name", name, "nome " + name);
        }
    }

    private void checkExistingSupplier(String fieldName, String fieldValue, String errorMessage) {
        QueryFieldInfoVO field = new QueryFieldInfoVO(fieldName, fieldValue);
        var existingSupplierBO = repository.findFirstBy(List.of(field));

        if (existingSupplierBO != null) {
            throw new InventoryAppException(EnumErrorCode.ENTIDADE_CADASTRADA, "Fornecedor com " + errorMessage);
        }
    }
}
