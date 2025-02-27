package com.kraemer.domain.usecases.product;

import java.util.List;

import com.kraemer.domain.entities.dto.ProductDTO;
import com.kraemer.domain.entities.enums.EnumErrorCode;
import com.kraemer.domain.entities.mappers.ProductMapper;
import com.kraemer.domain.entities.mappers.SupplierMapper;
import com.kraemer.domain.entities.repositories.IProductRepository;
import com.kraemer.domain.utils.exception.InventoryAppException;
import com.kraemer.domain.vo.QueryFieldInfoVO;

public class UpdateProduct {

    private IProductRepository repository;

    public UpdateProduct(IProductRepository repository) {
        this.repository = repository;
    }

    public ProductDTO execute(ProductDTO dto, Long id) {
        verifyId(id);

        var idField = new QueryFieldInfoVO("id", String.valueOf(id));

        var existingProduct = repository.findFirstBy(List.of(idField));

        if (existingProduct == null) {
            throw new InventoryAppException(EnumErrorCode.ENTIDADE_NAO_ENCONTRADA, "produto", "id", id);
        }

        verifyName(dto.getName(), id);

        existingProduct.handleUpdate(
                dto.getName(),
                dto.getDescription(),
                dto.getCostPrice(),
                dto.getPrice(),
                dto.getQuantity(),
                dto.getImage(),
                dto.getSupplier() != null ? SupplierMapper.toBO(dto.getSupplier()) : null,
                dto.getActive());

        return ProductMapper.toDTO(repository.merge(existingProduct));
    }

    private void verifyId(Long id) {

        if (id == null) {
            throw new InventoryAppException(EnumErrorCode.CAMPO_OBRIGATORIO, "id");
        }

    }

    private void verifyName(String name, Long id) {
        var nameField = new QueryFieldInfoVO("name", String.valueOf(name));

        var preExistingProductWithName = repository.findFirstBy(List.of(nameField));

        if (preExistingProductWithName != null && !preExistingProductWithName.getId().equals(id)) {
            throw new InventoryAppException(EnumErrorCode.ENTIDADE_CADASTRADA, "Produto com nome '" + name + "'");
        }
    }

}
