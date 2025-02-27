package com.kraemer.domain.usecases.supplier;

import java.util.List;

import com.kraemer.domain.entities.SupplierBO;
import com.kraemer.domain.entities.dto.SupplierDTO;
import com.kraemer.domain.entities.enums.EnumErrorCode;
import com.kraemer.domain.entities.mappers.SupplierMapper;
import com.kraemer.domain.entities.repositories.ISupplierRepository;
import com.kraemer.domain.utils.exception.InventoryAppException;
import com.kraemer.domain.vo.QueryFieldInfoVO;

import jakarta.inject.Inject;

public class UpdateSupplierInfo {

    @Inject
    private ISupplierRepository repository;

    public UpdateSupplierInfo(ISupplierRepository repository) {
        this.repository = repository;
    }

    public SupplierDTO execute(SupplierDTO dto, Long id) {
        var existingSupplierBO = verifyExistingSupplier(id);
        validateSupplier(dto, id);

        updateSupplierInfo(existingSupplierBO, dto);

        repository.merge(existingSupplierBO);

        return SupplierMapper.toDTO(existingSupplierBO);
    }

    private SupplierBO verifyExistingSupplier(Long id) {
        verifyId(id);

        var idField = new QueryFieldInfoVO("id", String.valueOf(id));
        var existingSupplierBO = repository.findFirstBy(List.of(idField));
        if (existingSupplierBO == null) {
            throw new InventoryAppException(EnumErrorCode.ENTIDADE_NAO_ENCONTRADA, "Fornecedor", "id", id);
        }

        return existingSupplierBO;
    }

    private void verifyId(Long id) {
        if (id == null) {
            throw new InventoryAppException(EnumErrorCode.CAMPO_OBRIGATORIO, "id");
        }
    }

    private void validateSupplier(SupplierDTO dto, Long id) {
        validateField("name", dto.getName(), id, "nome");
        validateField("document", dto.getDocument(), id, "documento");
    }

    private void validateField(String fieldName, String fieldValue, Long id, String errorMessage) {
        var field = new QueryFieldInfoVO(fieldName, fieldValue);
        var existingWithField = repository.findFirstBy(List.of(field));
        if (existingWithField != null && !existingWithField.getId().equals(id)) {
            throw new InventoryAppException(EnumErrorCode.ENTIDADE_CADASTRADA, "Fornecedor com " + errorMessage + " '" + fieldValue + "'");
        }
    }

    private void updateSupplierInfo(SupplierBO supplierBO, SupplierDTO dto) {
        supplierBO.handleUpdateInfo(
                dto.getName(),
                dto.getDocument(),
                dto.getContact(),
                dto.getAddress(),
                dto.isActive());
    }
}
