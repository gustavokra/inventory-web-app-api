package com.kraemer.domain.usecases.product;

import java.util.List;

import com.kraemer.domain.entities.dto.ProductDTO;
import com.kraemer.domain.entities.mappers.ProductMapper;
import com.kraemer.domain.entities.repositories.IProductRepository;

public class FindAllProducts {
    
    private IProductRepository repository;

    public FindAllProducts(IProductRepository repository) {
        this.repository = repository;
    }

    public List<ProductDTO> execute() {
        return repository.findAll().stream().map(prod -> ProductMapper.toDTO(prod)).toList();
    }
}
