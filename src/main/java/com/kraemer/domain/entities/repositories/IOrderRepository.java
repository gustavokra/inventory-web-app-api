package com.kraemer.domain.entities.repositories;

import java.util.List;

import com.kraemer.domain.entities.OrderBO;
import com.kraemer.domain.entities.enums.EnumDBImpl;
import com.kraemer.domain.vo.QueryFieldInfoVO;

public interface IOrderRepository {

    OrderBO create(OrderBO bo);

    OrderBO merge(OrderBO bo);

    OrderBO findFirstBy(List<QueryFieldInfoVO> queryFields);

    List<OrderBO> findAllBy(List<QueryFieldInfoVO> queryFieldsInfoVO);

    List<OrderBO> findAll();

    boolean delete(Long id);

    EnumDBImpl getType();

}
