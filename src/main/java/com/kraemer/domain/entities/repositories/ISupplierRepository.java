package com.kraemer.domain.entities.repositories;

import java.util.List;

import com.kraemer.domain.entities.SupplierBO;
import com.kraemer.domain.entities.enums.EnumDBImpl;
import com.kraemer.domain.vo.QueryFieldInfoVO;

public interface ISupplierRepository {

    SupplierBO create(SupplierBO bo);

    SupplierBO merge(SupplierBO bo);

    SupplierBO findFirstBy(List<QueryFieldInfoVO> queryFields);

    List<SupplierBO> findAllBy(List<QueryFieldInfoVO> queryFieldsInfoVO);

    List<SupplierBO> findAll();

    boolean delete(Long id);

    EnumDBImpl getType();

}
