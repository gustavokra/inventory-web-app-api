package com.kraemer.domain.usecases.product;

import java.util.List;

import com.kraemer.domain.entities.dto.ProductDTO;
import com.kraemer.domain.entities.enums.EnumErrorCode;
import com.kraemer.domain.entities.mappers.ProductMapper;
import com.kraemer.domain.entities.repositories.IProductRepository;
import com.kraemer.domain.utils.exception.InvetoryAppException;
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
            throw new InvetoryAppException(EnumErrorCode.ENTIDADE_NAO_ENCONTRADA, "produto", "id", id);
        }

        existingProduct.handleUpdate(
                dto.getName(),
                dto.getDescription(),
                dto.getPrice(),
                dto.getQuantity(),
                dto.getImage());

        var supplierDTO = existingProduct.getSupplier();

        existingProduct.getSupplier()
                .handleUpdateInfo(supplierDTO.getName(),
                        supplierDTO.getDocument(),
                        supplierDTO.getContact(),
                        supplierDTO.getAddress(),
                        supplierDTO.isActive());

        return ProductMapper.toDTO(repository.merge(existingProduct));
    }

    private void verifyId(Long id) {

        if (id == null) {
            throw new InvetoryAppException(EnumErrorCode.CAMPO_OBRIGATORIO, "id");
        }

    }

}
