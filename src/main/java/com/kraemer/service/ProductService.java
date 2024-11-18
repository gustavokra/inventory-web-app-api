package com.kraemer.service;

import java.util.List;

import com.kraemer.domain.entities.dto.ProductDTO;
import com.kraemer.domain.entities.enums.EnumDBImpl;
import com.kraemer.domain.usecases.product.CreateProduct;
import com.kraemer.domain.usecases.product.FindAllProducts;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class ProductService extends AbstractService {

    @Transactional
    public ProductDTO create(ProductDTO dto, EnumDBImpl dbImpl) {
        var repository = dbFactory.getProductImpl(dbImpl);

        var createProduct = new CreateProduct(repository);

        return createProduct.execute(dto);
    }

    public List<ProductDTO> findAll(EnumDBImpl dbImpl) {
        var repository = dbFactory.getProductImpl(dbImpl);

        var findAllProducts = new FindAllProducts(repository);

        return findAllProducts.execute();
    }
    
}
