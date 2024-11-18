package com.kraemer.domain.usecases.product;

import java.util.List;

import com.kraemer.domain.entities.dto.ProductDTO;
import com.kraemer.domain.entities.enums.EnumErrorCode;
import com.kraemer.domain.entities.mappers.ProductMapper;
import com.kraemer.domain.entities.repositories.IProductRepository;
import com.kraemer.domain.utils.exception.InvetoryAppException;
import com.kraemer.domain.vo.QueryFieldInfoVO;

public class CreateProduct {
    
    private IProductRepository repository;

    public CreateProduct(IProductRepository repository) {
        this.repository = repository;
    }

    public ProductDTO execute(ProductDTO dto) {
        validateExistentProduct(dto.getName());

        repository.create(ProductMapper.toBO(dto));

        return dto;
    }

    private void validateExistentProduct(String productName) {
        var fieldProductName = new QueryFieldInfoVO("name", productName);
        var existentProduct = repository.findFirstBy(List.of(fieldProductName));
        
        if(existentProduct != null) {
            throw new InvetoryAppException(EnumErrorCode.ENTIDADE_CADASTRADA, "Produto com esse nome");
        }
    }

}
