package com.kraemer.domain.entities.repositories;

import java.util.List;

import com.kraemer.domain.entities.ProductBO;
import com.kraemer.domain.entities.enums.EnumDBImpl;
import com.kraemer.domain.vo.QueryFieldInfoVO;

public interface IProductRepository {

    ProductBO create(ProductBO bo);

    ProductBO merge(ProductBO bo);

    ProductBO findFirstBy(List<QueryFieldInfoVO> queryFields);

    List<ProductBO> findAllBy(List<QueryFieldInfoVO> queryFieldsInfoVO);

    List<ProductBO> findAll();

    boolean delete(Long id);

    EnumDBImpl getType();

}
