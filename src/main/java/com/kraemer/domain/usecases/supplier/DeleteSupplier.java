package com.kraemer.domain.usecases.supplier;

import java.util.List;

import com.kraemer.domain.entities.enums.EnumErrorCode;
import com.kraemer.domain.entities.repositories.ISupplierRepository;
import com.kraemer.domain.utils.exception.InventoryAppException;
import com.kraemer.domain.vo.QueryFieldInfoVO;

public class DeleteSupplier {

    private ISupplierRepository repository;

    public DeleteSupplier(ISupplierRepository repository) {
        this.repository = repository;
    }

    public void execute(Long id) {
        if (id == null) {
            throw new InventoryAppException(EnumErrorCode.CAMPO_OBRIGATORIO, "id");
        }

        var fieldId = new QueryFieldInfoVO("id", String.valueOf(id));

        var supplierToDelete = repository.findFirstBy(List.of(fieldId));

        if (supplierToDelete == null) {
            throw new InventoryAppException(EnumErrorCode.ENTIDADE_NAO_ENCONTRADA, "Fornecedor", "id", id);
        }

        if (!repository.delete(id)) {
            throw new InventoryAppException(EnumErrorCode.ERRO_AO_EXCLUIR, "Fornecedor", "id", id);
        }
    }

}
